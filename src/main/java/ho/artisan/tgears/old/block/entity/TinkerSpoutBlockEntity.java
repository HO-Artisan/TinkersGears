package ho.artisan.tgears.old.block.entity;

import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import ho.artisan.tgears.common.block.accessor.TankAccessor;
import ho.artisan.tgears.index.TGTagKeys;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashSet;
import java.util.Set;

import static ho.artisan.tgears.common.block.TinkerSpoutBlock.POWERED;

public class TinkerSpoutBlockEntity extends SpoutBlockEntity {
    @Setter
    @Getter
    private boolean isOn;

    public TinkerSpoutBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        isOn = false;
    }

    public SmartFluidTankBehaviour getTank() {
        return ((TankAccessor) this).tgears$getTank();
    }

    public Set<Direction> getAttachments() {
        Set<Direction> attachments = new HashSet<>();
        for (Direction direction : Direction.values()) {
            if (direction.getAxis() != Direction.Axis.Y) {
                if (level.getBlockState(getBlockPos().relative(direction)).is(TGTagKeys.Blocks.SPOUT_ATTACHMENTS)) {
                    attachments.add(direction.getOpposite());
                }
            }
        }
        return attachments;
    }

    @Override
    public void tick() {
        isOn = getBlockState().getValue(POWERED);
        super.tick();
    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);
        compound.putBoolean("IsOn", isOn);
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        isOn = compound.getBoolean("IsOn");
    }
}
