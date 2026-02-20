package ho.artisan.tgears.mixin.crushing;

import com.simibubi.create.content.kinetics.belt.BeltBlock;
import ho.artisan.tgears.old.block.entity.TinkerCrushingWheelControllerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeltBlock.class)
public class CoveringBeltFix {

    @Inject(
            remap = false,
            method = "isBlockCoveringBelt",
            at = @At("TAIL"),
            cancellable = true
    )
    private static void isBlockCoveringBelt(LevelAccessor world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockEntity(pos) instanceof TinkerCrushingWheelControllerBlockEntity)
            cir.setReturnValue(false);
    }
}
