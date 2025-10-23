package ho.artisan.tgears.common.block.behaviour;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.contraptions.render.ActorVisual;
import com.simibubi.create.content.contraptions.render.ContraptionMatrices;
import com.simibubi.create.content.kinetics.base.BlockBreakingMovementBehaviour;
import com.simibubi.create.foundation.damageTypes.CreateDamageSources;
import com.simibubi.create.foundation.utility.BlockHelper;
import com.simibubi.create.foundation.virtualWorld.VirtualRenderWorld;
import com.simibubi.create.infrastructure.config.AllConfigs;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import ho.artisan.tgears.client.renderer.TinkerDrillRenderer;
import ho.artisan.tgears.client.visual.TinkerDrillActorVisual;
import ho.artisan.tgears.common.block.AbstractTinkerDrillBlock;
import net.createmod.catnip.math.VecHelper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class TinkerDrillMovementBehaviour extends BlockBreakingMovementBehaviour {
    private final PartialModel model;

    public TinkerDrillMovementBehaviour(PartialModel model) {
        this.model = model;
    }

    public ItemStack createTool() {
        return new ItemStack(Items.DIAMOND_PICKAXE);
    }

    @Override
    public boolean isActive(MovementContext context) {
        return super.isActive(context)
                && !VecHelper.isVecPointingTowards(context.relativeMotion, context.state.getValue(AbstractTinkerDrillBlock.FACING)
                .getOpposite());
    }

    @Override
    protected void destroyBlock(MovementContext context, BlockPos breakingPos) {
        Level level = context.world;
        breakBlock(context, level, breakingPos, this::createTool);
    }

    private void breakBlock(MovementContext context, Level level, BlockPos pos, Supplier<ItemStack> tool) {
        BlockHelper.destroyBlockAs(level, pos, null, tool.get(), 1f, (stack) -> {
            ItemStack remainder;
            if (AllConfigs.server().kinetics.moveItemsToStorage.get())
                remainder = ItemHandlerHelper.insertItem(context.contraption.getStorage().getAllItems(), stack, false);
            else
                remainder = stack;
            if (remainder.isEmpty())
                return;
            // Actors might void items if their positions is undefined
            Vec3 vec = context.position;
            if (vec == null)
                return;

            ItemEntity itemEntity = new ItemEntity(context.world, vec.x, vec.y, vec.z, remainder);
            itemEntity.setDeltaMovement(context.motion.add(0, 0.5f, 0)
                    .scale(context.world.random.nextFloat() * .3f));
            context.world.addFreshEntity(itemEntity);
        });
    }

    @Override
    public Vec3 getActiveAreaOffset(MovementContext context) {
        return Vec3.atLowerCornerOf(context.state.getValue(AbstractTinkerDrillBlock.FACING)
                .getNormal()).scale(.65f);
    }

    @Override
    @OnlyIn(value = Dist.CLIENT)
    public void renderInContraption(MovementContext context, VirtualRenderWorld renderWorld,
                                    ContraptionMatrices matrices, MultiBufferSource buffer) {
        if (!VisualizationManager.supportsVisualization(context.world))
            TinkerDrillRenderer.renderInContraption(context, renderWorld, matrices, buffer, model);
    }

    @Nullable
    @Override
    public ActorVisual createVisual(VisualizationContext visualizationContext, VirtualRenderWorld simulationWorld, MovementContext movementContext) {
        return new TinkerDrillActorVisual(visualizationContext, simulationWorld, movementContext, model);
    }

    @Override
    public boolean disableBlockEntityRendering() {
        return true;
    }

    @Override
    protected DamageSource getDamageSource(Level level) {
        return CreateDamageSources.drill(level);
    }

    @Override
    public boolean canBreak(Level world, BlockPos breakingPos, BlockState state) {
        return super.canBreak(world, breakingPos, state) && !state.getCollisionShape(world, breakingPos)
                .isEmpty() && !AllTags.AllBlockTags.TRACKS.matches(state);
    }
}
