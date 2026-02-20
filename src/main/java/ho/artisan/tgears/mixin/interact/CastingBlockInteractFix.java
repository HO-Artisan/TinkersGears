package ho.artisan.tgears.mixin.interact;

import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.smeltery.block.AbstractCastingBlock;

@Mixin(AbstractCastingBlock.class)
public class CastingBlockInteractFix {

    @Inject(
            method = "use",
            at = @At("HEAD"),
            cancellable = true
    )
    private void useMixin(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult rayTraceResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (player.getItemInHand(hand).is(TGTagKeys.Items.CASTING_BLACKLIST))
            cir.setReturnValue(InteractionResult.CONSUME);
    }
}
