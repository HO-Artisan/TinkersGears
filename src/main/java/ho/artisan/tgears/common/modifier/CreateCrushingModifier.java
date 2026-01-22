package ho.artisan.tgears.common.modifier;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import ho.artisan.tgears.common.block.module.CrushingItemModule;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import slimeknights.tconstruct.common.recipe.RecipeCacheInvalidator;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.behavior.ProcessLootModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.build.ToolStatsModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class CreateCrushingModifier extends NoLevelsModifier implements ProcessLootModifierHook, ToolStatsModifierHook {
    private final Cache<Item, Optional<CrushingRecipe>> recipeCache = CacheBuilder
            .newBuilder()
            .maximumSize(64)
            .build();

    private final CrushingItemModule inventory = new CrushingItemModule();

    public CreateCrushingModifier() {
        RecipeCacheInvalidator.addReloadListener(client -> {
            if (!client) {
                recipeCache.invalidateAll();
            }
        });
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.PROCESS_LOOT, ModifierHooks.TOOL_STATS);
    }

    private Optional<CrushingRecipe> findRecipe(ItemStack stack, Level world) {
        inventory.setStackInSlot(0, stack);
        return world.getRecipeManager().getRecipeFor(AllRecipeTypes.CRUSHING.getType(), new RecipeWrapper(inventory), world);
    }

    @Nullable
    private CrushingRecipe findCachedRecipe(ItemStack stack, Level world) {
        // don't use the cache if there is a tag, prevent breaking NBT sensitive recipes
        if (stack.hasTag()) {
            return findRecipe(stack, world).orElse(null);
        }
        try {
            return recipeCache.get(stack.getItem(), () -> findRecipe(stack, world)).orElse(null);
        } catch (ExecutionException e) {
            return null;
        }
    }

    private List<ItemStack> crushingItem(ItemStack stack, Level world) {
        if (stack.is(TGTagKeys.Items.CRUSHING_BLACKLIST)) {
            return List.of(stack);
        }
        CrushingRecipe recipe = findCachedRecipe(stack, world);
        if (recipe != null) {
            inventory.setStackInSlot(0, stack);

            List<ItemStack> list = recipe.rollResults();

            list.removeIf(ItemStack::isEmpty);
            list.forEach(item -> item.setCount(item.getCount() * stack.getCount()));

            return list;
        }
        return List.of(stack);
    }

    @Override
    public void processLoot(IToolStackView tool, ModifierEntry modifier, List<ItemStack> generatedLoot, LootContext context) {
        Level world = context.getLevel();
        if (!generatedLoot.isEmpty()) {
            ToolDamageUtil.damage(tool, 5 * generatedLoot.size(), null, null);

            List<ItemStack> crushedLoot = new ArrayList<>();

            for (ItemStack originalStack : generatedLoot) {
                List<ItemStack> crushed = crushingItem(originalStack.copy(), world);
                crushedLoot.addAll(crushed);
            }

            generatedLoot.clear();
            generatedLoot.addAll(crushedLoot);
        }
    }

    @Override
    public void addToolStats(IToolContext context, ModifierEntry modifier, ModifierStatsBuilder builder) {
        builder.multiplier(ToolStats.MINING_SPEED, 0.75F);
        builder.multiplier(ToolStats.USE_ITEM_SPEED, 0.75F);
    }
}
