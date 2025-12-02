package ho.artisan.tgears.common.block.entity;

import com.simibubi.create.content.kinetics.crusher.CrushingWheelBlockEntity;
import ho.artisan.tgears.common.block.AbstractTinkerCrushingWheelBlock;
import net.createmod.catnip.data.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TinkerCrushingWheelBlockEntity extends CrushingWheelBlockEntity {
    public TinkerCrushingWheelBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void fixControllers() {
        for (Direction d : Iterate.directions) {
            var block = (AbstractTinkerCrushingWheelBlock<?>) getBlockState().getBlock();
            block.updateControllers(getBlockState(), getLevel(), getBlockPos(), d);
        }
    }

    @Override
    public float calculateStressApplied() {
        float impact = 4.0F;
        this.lastStressApplied = impact;
        return impact;
    }

    @Override
    public float calculateAddedStressCapacity() {
        float capacity = 0;
        this.lastCapacityProvided = capacity;
        return capacity;
    }
}
