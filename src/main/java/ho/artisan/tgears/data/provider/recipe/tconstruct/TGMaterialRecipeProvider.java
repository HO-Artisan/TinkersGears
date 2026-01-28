package ho.artisan.tgears.data.provider.recipe.tconstruct;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import ho.artisan.tgears.data.provider.recipe.TGRecipeProvider;
import ho.artisan.tgears.index.TGItems;
import ho.artisan.tgears.index.TGMaterials;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

import java.util.function.Consumer;

public final class TGMaterialRecipeProvider extends TGRecipeProvider implements IMaterialRecipeHelper {
    public TGMaterialRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public String getType() {
        return "tinker/materials/";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        this.addMaterials(consumer);
    }

    private void addMaterials(Consumer<FinishedRecipe> consumer) {

        materialRecipe(consumer, TGMaterials.Ids.ANDESITE_ALLOY, Ingredient.of(AllItems.ANDESITE_ALLOY), 1, 1, "andesite_alloy/ingot");
        materialRecipe(consumer, TGMaterials.Ids.ANDESITE_ALLOY, Ingredient.of(AllBlocks.ANDESITE_ALLOY_BLOCK), 9, 1, "andesite_alloy/block");
        materialComposite(consumer, MaterialIds.andesite, TGMaterials.Ids.ANDESITE_ALLOY, TinkerFluids.moltenIron, 20, "andesite_alloy/andesite_composite");

        materialRecipe(consumer, TGMaterials.Ids.CHOCOLATE, Ingredient.of(AllItems.BAR_OF_CHOCOLATE), 1, 1, "chocolate/ingot");
        materialRecipe(consumer, TGMaterials.Ids.BLAZING_CHOCOLATE, Ingredient.of(TGItems.BAR_OF_BLAZING_CHOCOLATE), 1, 1, "blazing_chocolate/ingot");

        materialRecipe(consumer, TGMaterials.Ids.BRASS, Ingredient.of(AllItems.BRASS_INGOT), 1, 1, "brass/ingot");
        materialRecipe(consumer, TGMaterials.Ids.BRASS, Ingredient.of(AllBlocks.BRASS_BLOCK), 9, 1, "brass/block");
        materialMeltingCasting(consumer, TGMaterials.Ids.BRASS, TinkerFluids.moltenBrass, "brass/fluid");

        materialRecipe(consumer, TGMaterials.Ids.CARDBOARD, Ingredient.of(AllItems.CARDBOARD), 1, 1, "cardboard/item");
        materialRecipe(consumer, TGMaterials.Ids.CARDBOARD, Ingredient.of(AllBlocks.CARDBOARD_BLOCK), 4, 1, "cardboard/block");

        materialRecipe(consumer, TGMaterials.Ids.ROSE_QUARTZ, Ingredient.of(AllItems.ROSE_QUARTZ), 1, 1, "rose_quartz/gem");
        materialRecipe(consumer, TGMaterials.Ids.ROSE_QUARTZ, Ingredient.of(AllItems.POLISHED_ROSE_QUARTZ), 1, 1, "rose_quartz/polished_gem");

    }

}
