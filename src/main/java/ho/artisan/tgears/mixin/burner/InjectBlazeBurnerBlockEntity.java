package ho.artisan.tgears.mixin.burner;

import com.chemiofitor.tgearbox.api.ITinkerBlockEntityHeatSource;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity;
import ho.artisan.tgears.util.BlazeBurnerUtil;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlazeBurnerBlockEntity.class)
public class InjectBlazeBurnerBlockEntity implements ITinkerBlockEntityHeatSource {

    @Override
    public int getTemperature() {
        BlazeBurnerBlockEntity burner = (BlazeBurnerBlockEntity) (Object) this;
        return BlazeBurnerUtil.getTemperature(burner.getBlockState().getValue(BlazeBurnerBlock.HEAT_LEVEL));
    }

    @Override
    public int getRate() {
        BlazeBurnerBlockEntity burner = (BlazeBurnerBlockEntity) (Object) this;
        return BlazeBurnerUtil.getRate(burner.getBlockState().getValue(BlazeBurnerBlock.HEAT_LEVEL));
    }

    @Override
    public boolean isHeating() {
        BlazeBurnerBlockEntity burner = (BlazeBurnerBlockEntity) (Object) this;
        return burner.getBlockState().getValue(BlazeBurnerBlock.HEAT_LEVEL).isAtLeast(BlazeBurnerBlock.HeatLevel.SMOULDERING);
    }
}
