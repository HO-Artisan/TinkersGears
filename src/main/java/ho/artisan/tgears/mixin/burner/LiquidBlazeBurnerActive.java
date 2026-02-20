package ho.artisan.tgears.mixin.burner;

import com.mrh0.createaddition.blocks.liquid_blaze_burner.LiquidBlazeBurnerBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntry;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LiquidBlazeBurnerBlockEntity.class)
public class LiquidBlazeBurnerActive {
    @Redirect(
            remap = false,
            method = "isValidBlockAbove",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"
            )
    )
    public boolean valid(BlockEntry<?> instance, BlockState state) {
        return instance.has(state) || state.is(TGTagKeys.Blocks.BURNER_TARGETS);
    }
}
