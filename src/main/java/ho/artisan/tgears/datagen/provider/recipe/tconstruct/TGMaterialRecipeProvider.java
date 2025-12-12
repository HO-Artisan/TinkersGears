package ho.artisan.tgears.datagen.provider.recipe.tconstruct;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import ho.artisan.tgears.datagen.provider.recipe.TGRecipeProvider;
import ho.artisan.tgears.index.TGMaterials;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;

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


        materialRecipe(consumer, TGMaterials.Ids.CARDBOARD, Ingredient.of(AllItems.CARDBOARD), 1, 1, "cardboard/item");
        materialRecipe(consumer, TGMaterials.Ids.CARDBOARD, Ingredient.of(AllBlocks.CARDBOARD_BLOCK), 4, 1, "cardboard/block");

        materialRecipe(consumer, TGMaterials.Ids.ROSE_QUARTZ, Ingredient.of(AllItems.ROSE_QUARTZ), 1, 1, "rose_quartz/gem");
        materialRecipe(consumer, TGMaterials.Ids.ROSE_QUARTZ, Ingredient.of(AllItems.POLISHED_ROSE_QUARTZ), 1, 1, "rose_quartz/polished_gem");

    }

}
