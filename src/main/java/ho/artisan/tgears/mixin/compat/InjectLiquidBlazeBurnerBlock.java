package ho.artisan.tgears.mixin.compat;

import com.mrh0.createaddition.blocks.liquid_blaze_burner.LiquidBlazeBurnerBlock;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import ho.artisan.tgears.api.ITinkerBlockHeatSource;
import ho.artisan.tgears.util.BlazeBurnerUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LiquidBlazeBurnerBlock.class)
public class InjectLiquidBlazeBurnerBlock implements ITinkerBlockHeatSource {
    @Override
    public int getTemperature(Level level, BlockPos pos) {
        BlockState burner = level.getBlockState(pos);
        return BlazeBurnerUtil.getTemperature(burner.getValue(LiquidBlazeBurnerBlock.HEAT_LEVEL));
    }

    @Override
    public int getRate(Level level, BlockPos pos) {
        BlockState burner = level.getBlockState(pos);
        return BlazeBurnerUtil.getRate(burner.getValue(LiquidBlazeBurnerBlock.HEAT_LEVEL));
    }

    @Override
    public boolean isHeating(Level level, BlockPos pos) {
        BlockState burner = level.getBlockState(pos);
        return burner.getValue(LiquidBlazeBurnerBlock.HEAT_LEVEL).isAtLeast(BlazeBurnerBlock.HeatLevel.SMOULDERING);
    }
}
