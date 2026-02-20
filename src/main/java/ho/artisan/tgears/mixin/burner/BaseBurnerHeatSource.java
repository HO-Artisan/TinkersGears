package ho.artisan.tgears.mixin.burner;

import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import ho.artisan.tgears.api.ITinkerBlockHeatSource;
import ho.artisan.tgears.util.BlazeBurnerUtil;
import net.dragonegg.moreburners.content.block.BaseBurnerBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BaseBurnerBlock.class)
public class BaseBurnerHeatSource implements ITinkerBlockHeatSource {
    @Override
    public int getTemperature(Level level, BlockPos pos) {
        BlockState burner = level.getBlockState(pos);
        return BlazeBurnerUtil.getTemperature(burner.getValue(BaseBurnerBlock.HEAT_LEVEL));
    }

    @Override
    public int getRate(Level level, BlockPos pos) {
        BlockState burner = level.getBlockState(pos);
        return BlazeBurnerUtil.getRate(burner.getValue(BaseBurnerBlock.HEAT_LEVEL));
    }

    @Override
    public boolean isHeating(Level level, BlockPos pos) {
        BlockState burner = level.getBlockState(pos);
        return burner.getValue(BaseBurnerBlock.HEAT_LEVEL).isAtLeast(BlazeBurnerBlock.HeatLevel.SMOULDERING);
    }
}
