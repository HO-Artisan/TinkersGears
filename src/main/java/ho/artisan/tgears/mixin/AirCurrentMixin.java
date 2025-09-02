package ho.artisan.tgears.mixin;

import com.simibubi.create.content.kinetics.fan.AirCurrent;
import com.simibubi.create.content.kinetics.fan.IAirCurrentSource;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import ho.artisan.tgears.common.block.entity.module.CastingModule;
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
public class AirCurrentMixin {

    @Shadow(remap = false)
    @Final
    public IAirCurrentSource source;

    @Shadow(remap = false)
    public Direction direction;

    @Shadow(remap = false)
    private int getLimit() {
        return 0;
    }

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
            FanProcessingType segmentType = airCurrent.getTypeAt(i - 1);
            BlockPos pos = start.relative(direction, i);
            BlockEntity current = world.getBlockEntity(pos);
            if (current instanceof CastingModule module) {
                CastingModule.changeSpeedWithFan(module, segmentType, speed);
            }
//            else if (current instanceof BlazeBurnerBlockEntity burner) {
//                BlockEntity above = world.getBlockEntity(pos.above());
//                if (above instanceof MelterBlockEntity melter) {
//                    BurnerModule module = (BurnerModule) melter.getFuelModule();
//                } else if (above instanceof AlloyerBlockEntity alloyer) {
//                    BurnerModule module = (BurnerModule) alloyer.getFuelModule();
//                }
//            }
        }
    }
}
