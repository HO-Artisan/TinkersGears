package ho.artisan.tgears.mixin.assessor;

import com.simibubi.create.content.kinetics.crusher.CrushingWheelControllerBlockEntity;
import ho.artisan.tgears.common.block.accessor.WrapperAccessor;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CrushingWheelControllerBlockEntity.class)
public abstract class AccessorCrushingWrapper implements WrapperAccessor {
    @Override
    @Accessor(
            remap = false,
            value = "wrapper"
    )
    public abstract RecipeWrapper tgears$getWrapper();
}
