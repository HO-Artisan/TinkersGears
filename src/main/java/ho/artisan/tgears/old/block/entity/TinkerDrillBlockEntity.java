package ho.artisan.tgears.old.block.entity;

import com.simibubi.create.content.kinetics.drill.DrillBlockEntity;
import com.simibubi.create.foundation.utility.BlockHelper;
import net.createmod.catnip.math.VecHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public class TinkerDrillBlockEntity extends DrillBlockEntity {

    public TinkerDrillBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ItemStack createTool() {
        return new ItemStack(Items.DIAMOND_PICKAXE);
    }

    @Override
    public float calculateStressApplied() {
        float impact = 2.0F;
        this.lastStressApplied = impact;
        return impact;
    }

    @Override
    public float calculateAddedStressCapacity() {
        float capacity = 0;
        this.lastCapacityProvided = capacity;
        return capacity;
    }

    private void breakBlock(Level level, BlockPos pos, Supplier<ItemStack> tool) {
        Vec3 vec = VecHelper.offsetRandomly(VecHelper.getCenterOf(pos), level.random, .125f);
        BlockHelper.destroyBlockAs(level, pos, null, tool.get(), 1f, (stack) -> {
            if (stack.isEmpty())
                return;
            if (!level.getGameRules()
                    .getBoolean(GameRules.RULE_DOBLOCKDROPS))
                return;
            if (level.restoringBlockSnapshots)
                return;

            ItemEntity itementity = new ItemEntity(level, vec.x, vec.y, vec.z, stack);
            itementity.setDefaultPickUpDelay();
            itementity.setDeltaMovement(Vec3.ZERO);
            level.addFreshEntity(itementity);
        });
    }

    @Override
    public void onBlockBroken(BlockState stateToBreak) {
        if (!optimiseCobbleGen(stateToBreak)) {
            breakBlock(level, breakingPos, this::createTool);
        }
    }

    @Override
    protected float getBreakSpeed() {
        return Math.abs(getSpeed() / 100f) * 2.0F;
    }
}
