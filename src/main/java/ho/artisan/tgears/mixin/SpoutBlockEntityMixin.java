package ho.artisan.tgears.mixin;

import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import ho.artisan.tgears.common.block.entity.TinkerSpoutBlockEntity;
import ho.artisan.tgears.common.block.entity.module.SpoutModule;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SpoutBlockEntity.class)
public abstract class SpoutBlockEntityMixin implements SpoutModule {
    @Shadow(remap = false)
    SmartFluidTankBehaviour tank;

    @Override
    public SmartFluidTankBehaviour tgears$getTank() {
        return tank;
    }

    @Redirect(
            remap = false,
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/fluids/FluidStack;isEmpty()Z"
            )
    )
    public boolean tickMixin(FluidStack instance) {
        SpoutBlockEntity spout = (SpoutBlockEntity) (Object) this;
        if (spout instanceof TinkerSpoutBlockEntity tinkerSpout)
            return !instance.isEmpty() && tinkerSpout.isOn();
        return !instance.isEmpty();
    }

    @Redirect(
            remap = false,
            method = "addBehaviours",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/simibubi/create/foundation/blockEntity/behaviour/fluid/SmartFluidTankBehaviour;single(Lcom/simibubi/create/foundation/blockEntity/SmartBlockEntity;I)Lcom/simibubi/create/foundation/blockEntity/behaviour/fluid/SmartFluidTankBehaviour;")
    )
    public SmartFluidTankBehaviour capacityMixin(SmartBlockEntity be, int capacity) {
        SpoutBlockEntity spout = (SpoutBlockEntity) (Object) this;
        if (spout instanceof TinkerSpoutBlockEntity)
            return SmartFluidTankBehaviour.single(spout, 4000);
        return SmartFluidTankBehaviour.single(spout, 1000);
    }
}
