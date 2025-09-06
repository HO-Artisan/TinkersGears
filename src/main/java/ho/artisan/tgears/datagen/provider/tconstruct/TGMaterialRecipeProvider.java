package ho.artisan.tgears.datagen.provider.tconstruct;

import ho.artisan.tgears.datagen.provider.TGBaseRecipeProvider;
import ho.artisan.tgears.index.TGMaterialIds;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

import java.util.function.Consumer;

import static slimeknights.mantle.Mantle.COMMON;

public class TGMaterialRecipeProvider extends TGBaseRecipeProvider implements IMaterialRecipeHelper {
    public TGMaterialRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    public String getName() {
        return "TGear's Materials Recipes";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        this.addMaterialItems(consumer);
        this.addMaterialSmeltery(consumer);
    }

    private void addMaterialItems(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/materials/";
        materialRecipe(consumer, TGMaterialIds.ANDESITE_ALLOY, Ingredient.of(getItemTag(COMMON, "ingots/andesite_alloy")),   1, 1, folder + "alloy/andesite_alloy/ingot");
        materialRecipe(consumer, TGMaterialIds.ANDESITE_ALLOY, Ingredient.of(getItemTag(COMMON, "storage_blocks/andesite_alloy")),   9, 1, folder + "alloy/andesite_alloy/block");
    }

    private void addMaterialSmeltery(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/materials/";
        materialComposite(consumer, MaterialIds.andesite, TGMaterialIds.ANDESITE_ALLOY, TinkerFluids.moltenIron, FluidValues.NUGGET * 3, folder + "alloy/andesite_alloy/");
    }
}
