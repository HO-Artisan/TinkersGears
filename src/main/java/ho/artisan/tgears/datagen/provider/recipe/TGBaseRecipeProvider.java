package ho.artisan.tgears.datagen.provider.recipe;

import ho.artisan.tgears.TinkersGears;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.mantle.recipe.data.IRecipeHelper;

import java.util.function.Consumer;

public abstract class TGBaseRecipeProvider extends RecipeProvider implements IConditionBuilder, IRecipeHelper {
    public TGBaseRecipeProvider(PackOutput generator) {
        super(generator);
    }

    @Override
    public abstract String getName();

    @Override
    protected abstract void buildRecipes(Consumer<FinishedRecipe> consumer);

    @Override
    public String getModId() {
        return TinkersGears.MOD_ID;
    }
}
