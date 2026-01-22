package ho.artisan.tgears.data.provider.recipe.vanilla;

import ho.artisan.tgears.data.provider.recipe.TGRecipeProvider;
import ho.artisan.tgears.index.TGItems;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import slimeknights.mantle.recipe.cooking.CookingRecipeBuilder;

import java.util.function.Consumer;

public final class TGBlastingProvider extends TGRecipeProvider {
    public TGBlastingProvider(PackOutput generator) {
        super(generator);
    }

    @Override
    public String getType() {
        return "blasting";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        CookingRecipeBuilder.builder(TGTagKeys.Items.COBALT_INGOT)
                .cookingTime(100)
                .requires(TGItems.CRUSHED_RAW_COBALT)
                .experience(0.1F)
                .type(CookingRecipeBuilder.CookingType.BLASTING)
                .save(consumer, location("cobalt_ingot_from_crushed_raw_cobalt"));
    }
}
