package ho.artisan.tgears.mixin.burner;

import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import ho.artisan.tgears.api.block.entity.ITinkerHeatSource;
import ho.artisan.tgears.util.BlazeBurnerUtil;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlazeBurnerBlockEntity.class)
public class InjectBlazeBurnerBlockEntity implements ITinkerHeatSource {

    @Override
    public int tgears$getTemperature() {
        BlazeBurnerBlockEntity burner = (BlazeBurnerBlockEntity) (Object) this;
        return BlazeBurnerUtil.getTemperature(burner.getBlockState().getValue(BlazeBurnerBlock.HEAT_LEVEL));
    }

    @Override
    public int tgears$getRate() {
        BlazeBurnerBlockEntity burner = (BlazeBurnerBlockEntity) (Object) this;
        return BlazeBurnerUtil.getRate(burner.getBlockState().getValue(BlazeBurnerBlock.HEAT_LEVEL));
    }

    @Override
    public boolean tgears$isHeating() {
        BlazeBurnerBlockEntity burner = (BlazeBurnerBlockEntity) (Object) this;
        return burner.getBlockState().getValue(BlazeBurnerBlock.HEAT_LEVEL).isAtLeast(BlazeBurnerBlock.HeatLevel.SMOULDERING);
    }
}
