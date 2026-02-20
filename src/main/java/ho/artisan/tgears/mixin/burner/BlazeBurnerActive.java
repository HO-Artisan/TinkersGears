package ho.artisan.tgears.mixin.burner;

import com.simibubi.create.content.processing.basin.BasinBlock;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlazeBurnerBlockEntity.class)
public class BlazeBurnerActive {
    @Redirect(
            remap = false,
            method = "isValidBlockAbove",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/simibubi/create/content/processing/basin/BasinBlock;isBasin(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z"
            )
    )
    public boolean valid(LevelReader world, BlockPos pos) {
        return BasinBlock.isBasin(world, pos) || world.getBlockState(pos).is(TGTagKeys.Blocks.BURNER_TARGETS);
    }
}
