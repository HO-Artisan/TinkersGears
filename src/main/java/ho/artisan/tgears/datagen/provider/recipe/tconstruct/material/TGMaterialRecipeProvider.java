package ho.artisan.tgears.datagen.provider.recipe.tconstruct.material;

import ho.artisan.tgears.datagen.provider.recipe.TGBaseRecipeProvider;
import ho.artisan.tgears.index.TGMaterials;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

import java.util.function.Consumer;

import static slimeknights.mantle.Mantle.COMMON;

public final class TGMaterialRecipeProvider extends TGBaseRecipeProvider implements IMaterialRecipeHelper {
    public TGMaterialRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    public String getName() {
        return "TGears Materials Recipes";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        this.addMaterials(consumer);
    }

    private void addMaterials(Consumer<FinishedRecipe> consumer) {
        String folder = "tinker/materials/";
        materialRecipe(consumer, TGMaterials.Ids.ANDESITE_ALLOY, Ingredient.of(getItemTag(COMMON, "ingots/andesite_alloy")), 1, 1, folder + "andesite_alloy/ingot");
        materialRecipe(consumer, TGMaterials.Ids.ANDESITE_ALLOY, Ingredient.of(getItemTag(COMMON, "storage_blocks/andesite_alloy")), 9, 1, folder + "andesite_alloy/block");

        materialComposite(consumer, MaterialIds.andesite, TGMaterials.Ids.ANDESITE_ALLOY, TinkerFluids.moltenIron, FluidValues.NUGGET, folder);
    }
}
