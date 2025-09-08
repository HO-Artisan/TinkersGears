package ho.artisan.tgears.common.modifier;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import ho.artisan.tgears.common.block.entity.module.CrushingModule;
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
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.Nullable;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class CreateCrushingModifier extends NoLevelsModifier implements ProcessLootModifierHook {
    private final Cache<Item,Optional<CrushingRecipe>> recipeCache = CacheBuilder
            .newBuilder()
            .maximumSize(64)
            .build();

    private final CrushingModule inventory = new CrushingModule();

    public CreateCrushingModifier() {
        RecipeCacheInvalidator.addReloadListener(client -> {
            if (!client) {
                recipeCache.invalidateAll();
            }
        });
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.PROCESS_LOOT);
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

    private ItemStack crushingItem(ItemStack stack, Level world) {
        // skip blacklisted entries
        if (stack.is(TGTagKeys.Items.CRUSHING_BLACKLIST)) {
            return stack;
        }
        CrushingRecipe recipe = findCachedRecipe(stack, world);
        if (recipe != null) {
            inventory.setStackInSlot(0, stack);
            ItemStack output = recipe.assemble(new RecipeWrapper(inventory), world.registryAccess()).copy();
            if (stack.getCount() > 1) {
                // recipe output is a copy, safe to modify
                output.setCount(output.getCount() * stack.getCount());
            }
            return output;
        }
        return stack;
    }

    @Override
    public void processLoot(IToolStackView tool, ModifierEntry modifier, List<ItemStack> generatedLoot, LootContext context) {
        Level world = context.getLevel();
        if (!generatedLoot.isEmpty()) {
            ListIterator<ItemStack> iterator = generatedLoot.listIterator();
            while (iterator.hasNext()) {
                ItemStack stack = iterator.next();
                ItemStack smelted = crushingItem(stack, world);
                if (stack != smelted) {
                    iterator.set(smelted);
                    tool.setDamage(tool.getDamage() + 10);
                }
            }
        }
    }
}
