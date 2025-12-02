package ho.artisan.tgears.mixin.assessor;

import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import ho.artisan.tgears.common.block.accessor.TankAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SpoutBlockEntity.class)
public abstract class AccessorSpoutTank implements TankAccessor {
    @Override
    @Accessor(
            remap = false,
            value = "tank"
    )
    public abstract SmartFluidTankBehaviour tgears$getTank();
}
