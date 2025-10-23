package ho.artisan.tgears.mixin.fan;

import com.simibubi.create.content.kinetics.fan.AirCurrent;
import com.simibubi.create.content.kinetics.fan.IAirCurrentSource;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import ho.artisan.tgears.api.block.entity.IFanProcessingTarget;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AirCurrent.class)
public abstract class InjectAirCurrent {

    @Shadow(remap = false)
    @Final
    public IAirCurrentSource source;

    @Shadow(remap = false)
    public Direction direction;

    @Shadow(remap = false)
    protected abstract int getLimit();

    @Inject(
            remap = false,
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/simibubi/create/content/kinetics/fan/AirCurrent;tickAffectedHandlers()V")
    )
    public void tickMixin(CallbackInfo ci) {
        AirCurrent airCurrent = (AirCurrent) (Object) this;
        Level world = source.getAirCurrentWorld();
        BlockPos start = source.getAirCurrentPos();
        int limit = getLimit();
        float speed = source.getSpeed();
        for (int i = 1; i <= limit; i++) {
            FanProcessingType type = airCurrent.getTypeAt(i - 1);
            BlockPos pos = start.relative(direction, i);
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof IFanProcessingTarget target)
                target.tgears$process(type, speed, airCurrent);
        }
    }
}
