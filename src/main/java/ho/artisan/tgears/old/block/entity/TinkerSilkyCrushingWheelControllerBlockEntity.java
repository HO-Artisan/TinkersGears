
package ho.artisan.tgears.old.block.entity;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import ho.artisan.tgears.common.block.accessor.WrapperAccessor;
import ho.artisan.tgears.index.TGRecipeTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.Optional;

public class TinkerSilkyCrushingWheelControllerBlockEntity extends TinkerCrushingWheelControllerBlockEntity {
    public TinkerSilkyCrushingWheelControllerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public RecipeWrapper getWrapper() {
        return ((WrapperAccessor) this).tgears$getWrapper();
    }

    public Optional<ProcessingRecipe<RecipeWrapper>> findRecipe() {
        Optional<ProcessingRecipe<RecipeWrapper>> recipe = TGRecipeTypes.SILKY_CRUSHING.find(getWrapper(), level);
        if (recipe.isEmpty()) {
            recipe = AllRecipeTypes.CRUSHING.find(getWrapper(), level);
            if (recipe.isEmpty())
                recipe = AllRecipeTypes.MILLING.find(getWrapper(), level);
        }
        return recipe;
    }
}
