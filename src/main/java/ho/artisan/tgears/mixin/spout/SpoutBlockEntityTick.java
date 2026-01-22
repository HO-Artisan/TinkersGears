package ho.artisan.tgears.mixin.spout;

import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import ho.artisan.tgears.old.block.entity.TinkerSpoutBlockEntity;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SpoutBlockEntity.class)
public abstract class SpoutBlockEntityTick {
    @Redirect(
            remap = false,
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/fluids/FluidStack;isEmpty()Z"
            )
    )
    public boolean canProcessTick(FluidStack instance) {
        SpoutBlockEntity spout = (SpoutBlockEntity) (Object) this;
        boolean flag = true;
        if (spout instanceof TinkerSpoutBlockEntity t)
            flag = t.isOn();
        return !instance.isEmpty() && flag;
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
            capacity = capacity * 4;
        return SmartFluidTankBehaviour.single(spout, capacity);
    }
}
