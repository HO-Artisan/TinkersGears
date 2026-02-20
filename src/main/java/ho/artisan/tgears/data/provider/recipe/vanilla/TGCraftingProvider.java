package ho.artisan.tgears.data.provider.recipe.vanilla;

import com.simibubi.create.AllItems;
import ho.artisan.tgears.data.provider.recipe.TGRecipeProvider;
import ho.artisan.tgears.index.TGBlocks;
import ho.artisan.tgears.index.TGItems;
import ho.artisan.tgears.index.TGMaterials;
import ho.artisan.tgears.index.TGTinkerItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
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
        partRecipes(consumer);
        cardboardRecipes(consumer);
    }

    public void cardboardRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TGItems.SOUL_CARDBOARD, 4)
                .requires(TGBlocks.SOUL_CARDBOARD_BLOCK)
                .unlockedBy("has_item", has(TGItems.SOUL_CARDBOARD))
                .save(consumer, location("soul_cardboard_from_block"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TGBlocks.SOUL_CARDBOARD_BLOCK)
                .pattern("XX")
                .pattern("XX")
                .define('X', TGItems.SOUL_CARDBOARD)
                .unlockedBy("has_item", has(TGItems.SOUL_CARDBOARD))
                .save(consumer, location("soul_cardboard_block"));
    }

    public void partRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.BRASS_HAND)
                .requires(AllItems.ANDESITE_ALLOY)
                .requires(MaterialIngredient.of(TGTinkerItems.HAND_PART, TGMaterials.Ids.BRASS))
                .unlockedBy("has_item", has(AllItems.ANDESITE_ALLOY))
                .save(consumer, location("brass_hand_from_part"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TGItems.CHOCOLATE_HAND)
                .requires(AllItems.ANDESITE_ALLOY)
                .requires(MaterialIngredient.of(TGTinkerItems.HAND_PART, TGMaterials.Ids.CHOCOLATE))
                .unlockedBy("has_item", has(AllItems.ANDESITE_ALLOY))
                .save(consumer, location("chocolate_hand_from_part"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TGItems.BLAZING_CHOCOLATE_HAND)
                .requires(AllItems.ANDESITE_ALLOY)
                .requires(MaterialIngredient.of(TGTinkerItems.HAND_PART, TGMaterials.Ids.BLAZING_CHOCOLATE))
                .unlockedBy("has_item", has(AllItems.ANDESITE_ALLOY))
                .save(consumer, location("blazing_chocolate_hand_from_part"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.PROPELLER)
                .requires(AllItems.ANDESITE_ALLOY)
                .requires(MaterialIngredient.of(TGTinkerItems.PROPELLER_PART, MaterialIds.iron))
                .unlockedBy("has_item", has(AllItems.ANDESITE_ALLOY))
                .save(consumer, location("iron_propeller_from_part"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TGItems.COBALT_PROPELLER)
                .requires(AllItems.ANDESITE_ALLOY)
                .requires(MaterialIngredient.of(TGTinkerItems.PROPELLER_PART, MaterialIds.cobalt))
                .unlockedBy("has_item", has(AllItems.ANDESITE_ALLOY))
                .save(consumer, location("cobalt_propeller_from_part"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.WHISK)
                .requires(AllItems.ANDESITE_ALLOY)
                .requires(MaterialIngredient.of(TGTinkerItems.WHISK_PART, MaterialIds.iron))
                .unlockedBy("has_item", has(AllItems.ANDESITE_ALLOY))
                .save(consumer, location("iron_whisk_from_part"));
    }
}
