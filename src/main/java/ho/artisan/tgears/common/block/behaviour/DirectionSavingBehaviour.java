package ho.artisan.tgears.common.block.behaviour;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BehaviourType;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import lombok.Getter;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class DirectionSavingBehaviour extends BlockEntityBehaviour {
    public static final BehaviourType<DirectionSavingBehaviour> TYPE = new BehaviourType<>();

    @Getter
    Direction direction = Direction.NORTH;

    public DirectionSavingBehaviour(SmartBlockEntity be) {
        super(be);
    }

    @Override
    public void write(CompoundTag nbt, boolean clientPacket) {
        nbt.putString("Direction", direction.getName());
        super.write(nbt, clientPacket);
    }

    @Override
    public void read(CompoundTag nbt, boolean clientPacket) {
        direction = Direction.byName(nbt.getString("Direction"));
        super.read(nbt, clientPacket);
    }

    @Override
    public void initialize() {
        super.initialize();
        direction = blockEntity.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING);
    }

    @Override
    public BehaviourType<?> getType() {
        return TYPE;
    }
}
