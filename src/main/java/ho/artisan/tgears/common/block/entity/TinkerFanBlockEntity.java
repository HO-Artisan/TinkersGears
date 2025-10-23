package ho.artisan.tgears.common.block.entity;

import com.simibubi.create.content.kinetics.fan.AirCurrent;
import com.simibubi.create.content.kinetics.fan.EncasedFanBlockEntity;
import ho.artisan.tgears.TinkersGearsConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TinkerFanBlockEntity extends EncasedFanBlockEntity {
    public TinkerFanBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        airCurrent = new AirCurrent(this);
    }

    @Override
    public float calculateStressApplied() {
        float impact = 1.0F;
        this.lastStressApplied = impact;
        return impact;
    }

    @Override
    public float calculateAddedStressCapacity() {
        float capacity = 0;
        this.lastCapacityProvided = capacity;
        return capacity;
    }

    @Override
    public float getMaxDistance() {
        return super.getMaxDistance() * TinkersGearsConfig.FAN_RANGE.get();
    }
}
