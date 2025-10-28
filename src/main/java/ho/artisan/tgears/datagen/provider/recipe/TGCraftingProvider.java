package ho.artisan.tgears.datagen.provider.recipe;

import com.simibubi.create.AllItems;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class TGCraftingProvider extends TGBaseRecipeProvider {
    public TGCraftingProvider(PackOutput generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "TGears Crafting Recipes";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        applyPart(consumer, "hand", TGItems.HAND_CAST, TGItems.HAND_CAST_WITH_PART);
        applyPart(consumer, "propeller", TGItems.PROPELLER_CAST, TGItems.PROPELLER_CAST_WITH_PART);
        applyPart(consumer, "whisk", TGItems.WHISK_CAST, TGItems.WHISK_CAST_WITH_PART);
    }

    private void applyPart(Consumer<FinishedRecipe> consumer, String id, ItemLike cast, ItemLike castWithPart) {
        String folder = "crafting/part/";

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, castWithPart, 1)
                .requires(cast)
                .requires(AllItems.ANDESITE_ALLOY)
                .unlockedBy("has_" + id + "_cast", has(cast))
                .save(consumer, location(folder + id));
    }


}
