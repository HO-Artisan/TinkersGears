package ho.artisan.tgears.common.recipe;

import ho.artisan.tgears.index.TGRecipeTypes;
import lombok.Getter;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import slimeknights.mantle.recipe.ICommonRecipe;
import slimeknights.tconstruct.library.materials.definition.MaterialVariant;
import slimeknights.tconstruct.library.recipe.ITinkerableContainer;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;

import java.util.ArrayList;
import java.util.List;

public final class DismantlingRecipe implements ICommonRecipe<ITinkerableContainer> {
    private final ResourceLocation id;
    @Getter
    private final List<PartStack> parts;
    @Getter
    private final Ingredient ingredient;
    @Getter
    private final int primary;
    @Getter
    private final int time;

    public DismantlingRecipe(ResourceLocation id, List<PartStack> parts, Ingredient ingredient, int primary,
                             int time) {
        this.id = id;
        this.parts = parts;
        this.ingredient = ingredient;
        this.primary = primary;
        this.time = time;
    }

    @Override
    public boolean matches(ITinkerableContainer container, Level level) {
        return ingredient.test(container.getTinkerableStack());
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return parts.get(primary).stack;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public ItemStack assemble(ITinkerableContainer inv, RegistryAccess access) {
        ItemStack result = getResultItem(access);
        if (result.getItem() instanceof IMaterialItem item) {
            result = assemble(item, inv.getTinkerable().getMaterial(primary));
        }
        return result;
    }

    public ItemStack assemble(IMaterialItem item, MaterialVariant material) {
        return item.withMaterial(material.getVariant());
    }

    public List<ItemStack> assembleAll(ITinkerableContainer inv, RandomSource random) {
        List<ItemStack> result = new ArrayList<>();

        for (PartStack part : parts) {
            int slot = parts.indexOf(part);
            if (slot == primary || random.nextFloat() < part.chance) {
                if (part.stack.getItem() instanceof IMaterialItem item) {
                    MaterialVariant variant = inv.getTinkerable().getMaterial(slot);
                    if (variant != MaterialVariant.UNKNOWN) {
                        result.add(assemble(item, variant));
                    }
                } else {
                    result.add(part.stack);
                }
            }
        }

        return result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return TGRecipeTypes.DISMANTLING.getSerializer();
    }

    @Override
    public RecipeType<?> getType() {
        return TGRecipeTypes.DISMANTLING.getType();
    }

    public record PartStack(ItemStack stack, float chance) { }
}
