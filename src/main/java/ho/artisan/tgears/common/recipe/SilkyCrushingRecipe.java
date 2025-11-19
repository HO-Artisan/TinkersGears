package ho.artisan.tgears.common.recipe;

import com.simibubi.create.content.kinetics.crusher.AbstractCrushingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import ho.artisan.tgears.index.TGRecipeTypes;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class SilkyCrushingRecipe extends AbstractCrushingRecipe {

    public SilkyCrushingRecipe(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
        super(TGRecipeTypes.SILKY_CRUSHING, params);
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level worldIn) {
        if (inv.isEmpty())
            return false;
        return ingredients.get(0)
                .test(inv.getItem(0));
    }

    @Override
    protected int getMaxOutputCount() {
        return 7;
    }
}
