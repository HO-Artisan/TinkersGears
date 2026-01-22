package ho.artisan.tgears.old.block.entity;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import ho.artisan.tgears.common.block.behaviour.DirectionSavingBehaviour;
import ho.artisan.tgears.common.block.behaviour.TinkerDismantlingBehaviour;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;

import java.util.List;

public class TinkerDismantlerBlockEntity extends SmartBlockEntity {
    @Getter
    TinkerDismantlingBehaviour tinkerBreakingBehaviour;

    @Getter
    DirectionSavingBehaviour directionSavingBehaviour;

    public TinkerDismantlerBlockEntity(BlockEntityType<? extends TinkerDismantlerBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public void setTinkerable(ItemStack stack) {
        tinkerBreakingBehaviour.setTinkerable(stack);
    }

    public boolean isEmpty() {
        return tinkerBreakingBehaviour.isEmpty();
    }

    public boolean isLocked() {
        return tinkerBreakingBehaviour.isLocked();
    }

    public void setLocked(boolean locked) {
        tinkerBreakingBehaviour.setLocked(locked);
    }

    public Direction getDirection() {
        return directionSavingBehaviour.getDirection();
    }

    public NonNullList<ItemStack> getItemStacks() {
        return tinkerBreakingBehaviour.getItemStacks();
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        behaviours.add(tinkerBreakingBehaviour = new TinkerDismantlingBehaviour(this));
        behaviours.add(directionSavingBehaviour = new DirectionSavingBehaviour(this));
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER && side == Direction.UP)
            return tinkerBreakingBehaviour.getItemCapability(cap, side);
        return super.getCapability(cap, side);
    }
}
