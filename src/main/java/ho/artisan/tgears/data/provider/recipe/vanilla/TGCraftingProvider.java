package ho.artisan.tgears.data.provider.recipe.vanilla;

import com.simibubi.create.AllItems;
import ho.artisan.tgears.data.provider.recipe.TGRecipeProvider;
import ho.artisan.tgears.index.TGItems;
import ho.artisan.tgears.index.TGMaterials;
import ho.artisan.tgears.index.TGTinkerItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import slimeknights.tconstruct.library.recipe.ingredient.MaterialIngredient;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

import java.util.function.Consumer;

public final class TGCraftingProvider extends TGRecipeProvider {
    public TGCraftingProvider(PackOutput generator) {
        super(generator);
    }

    @Override
    public String getType() {
        return "crafting";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.BRASS_HAND)
                .define('A', AllItems.ANDESITE_ALLOY)
                .define('H', MaterialIngredient.of(TGTinkerItems.HAND_PART, TGMaterials.Ids.BRASS))
                .pattern(" H")
                .pattern("A ")
                .unlockedBy("has_item", has(AllItems.ANDESITE_ALLOY))
                .save(consumer, location("brass_hand_from_part"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TGItems.CHOCOLATE_HAND)
                .define('A', AllItems.ANDESITE_ALLOY)
                .define('H', MaterialIngredient.of(TGTinkerItems.HAND_PART, TGMaterials.Ids.CHOCOLATE))
                .pattern(" H")
                .pattern("A ")
                .unlockedBy("has_item", has(AllItems.ANDESITE_ALLOY))
                .save(consumer, location("chocolate_hand_from_part"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TGItems.BLAZING_CHOCOLATE_HAND)
                .define('A', AllItems.ANDESITE_ALLOY)
                .define('H', MaterialIngredient.of(TGTinkerItems.HAND_PART, TGMaterials.Ids.BLAZING_CHOCOLATE))
                .pattern(" H")
                .pattern("A ")
                .unlockedBy("has_item", has(AllItems.ANDESITE_ALLOY))
                .save(consumer, location("blazing_chocolate_hand_from_part"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.PROPELLER)
                .define('A', AllItems.ANDESITE_ALLOY)
                .define('H', MaterialIngredient.of(TGTinkerItems.PROPELLER_PART, MaterialIds.iron))
                .pattern(" H")
                .pattern("A ")
                .unlockedBy("has_item", has(AllItems.ANDESITE_ALLOY))
                .save(consumer, location("iron_propeller_from_part"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TGItems.COBALT_PROPELLER)
                .define('A', AllItems.ANDESITE_ALLOY)
                .define('H', MaterialIngredient.of(TGTinkerItems.PROPELLER_PART, MaterialIds.cobalt))
                .pattern(" H")
                .pattern("A ")
                .unlockedBy("has_item", has(AllItems.ANDESITE_ALLOY))
                .save(consumer, location("cobalt_propeller_from_part"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.WHISK)
                .define('A', AllItems.ANDESITE_ALLOY)
                .define('H', MaterialIngredient.of(TGTinkerItems.WHISK_PART, MaterialIds.iron))
                .pattern(" H")
                .pattern("A ")
                .unlockedBy("has_item", has(AllItems.ANDESITE_ALLOY))
                .save(consumer, location("iron_whisk_from_part"));
    }
}
