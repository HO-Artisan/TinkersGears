package ho.artisan.tgears.datagen.provider.recipe.vanilla;

import ho.artisan.tgears.datagen.provider.recipe.TGRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;

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

    }
}
