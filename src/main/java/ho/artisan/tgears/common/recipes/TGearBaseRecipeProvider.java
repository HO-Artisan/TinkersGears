package ho.artisan.tgears.common.recipes;

import ho.artisan.tgears.TinkersGears;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.mantle.recipe.data.IRecipeHelper;

import java.util.function.Consumer;

/**
 * Shared logic for each module's recipe provider
 */
public abstract class TGearBaseRecipeProvider extends RecipeProvider implements IConditionBuilder, IRecipeHelper {
    public TGearBaseRecipeProvider(PackOutput generator) {
        super(generator);
        //TConstruct.sealTinkersClass(this, "BaseRecipeProvider", "BaseRecipeProvider is trivial to recreate and directly extending can lead to addon recipes polluting our namespace.");
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
