package ho.artisan.tgears.common.block;

import com.simibubi.create.AllShapes;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.foundation.block.IBE;
import ho.artisan.tgears.common.block.entity.TinkerDismantlerBlockEntity;
import ho.artisan.tgears.index.TGBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class TinkerDismantlerBlock extends HorizontalDirectionalBlock implements IBE<TinkerDismantlerBlockEntity>, IWrenchable {
    public static final BooleanProperty CLAMPED = BooleanProperty.create("clamped");

    public TinkerDismantlerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH).setValue(CLAMPED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return AllShapes.CASING_13PX.get(Direction.UP);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (hit.getDirection() != Direction.UP)
            return InteractionResult.PASS;

        if (hand != InteractionHand.MAIN_HAND)
            return InteractionResult.PASS;

        ItemStack heldItem = player.getMainHandItem();
        if (heldItem.isEmpty())
            return InteractionResult.PASS;
        TinkerDismantlerBlockEntity blockEntity = getBlockEntity(level, pos);
        if (blockEntity.isEmpty() && !blockEntity.isLocked()) {
            blockEntity.setTinkerable(heldItem.split(1));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        for (Direction direction : context.getNearestLookingDirections()) {
            BlockState blockstate;

            if (direction.getAxis() == Direction.Axis.Y) {
                blockstate = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
            } else {
                blockstate = this.defaultBlockState().setValue(FACING, direction.getOpposite());
            }

            if (blockstate.canSurvive(context.getLevel(), context.getClickedPos())) {
                return blockstate;
            }
        }
        return null;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            TinkerDismantlerBlockEntity dismantler = getBlockEntity(level, pos);

            Containers.dropContents(level, pos, dismantler.getItemStacks());
            level.updateNeighbourForOutputSignal(pos, this);

            super.onRemove(state, level, pos, newState, isMoving);
        }

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, CLAMPED);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public Class<TinkerDismantlerBlockEntity> getBlockEntityClass() {
        return TinkerDismantlerBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends TinkerDismantlerBlockEntity> getBlockEntityType() {
        return TGBlockEntityTypes.DISMANTLER.get();
    }
}
