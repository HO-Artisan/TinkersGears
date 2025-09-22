package ho.artisan.tgears.client.renderer;

import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.contraptions.render.ContraptionMatrices;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.virtualWorld.VirtualRenderWorld;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import ho.artisan.tgears.common.block.AbstractTinkerDrillBlock;
import ho.artisan.tgears.common.block.entity.TinkerDrillBlockEntity;
import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.math.AngleHelper;
import net.createmod.catnip.math.VecHelper;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class TinkerDrillRenderer extends KineticBlockEntityRenderer<TinkerDrillBlockEntity> {
    private final PartialModel model;

    public TinkerDrillRenderer(BlockEntityRendererProvider.Context context, PartialModel model) {
        super(context);
        this.model = model;
    }

    @Override
    protected SuperByteBuffer getRotatedModel(TinkerDrillBlockEntity be, BlockState state) {
        return CachedBuffers.partialFacing(model, state);
    }

    public static void renderInContraption(MovementContext context, VirtualRenderWorld renderWorld,
                                           ContraptionMatrices matrices, MultiBufferSource buffer, PartialModel model) {
        BlockState state = context.state;
        SuperByteBuffer superBuffer = CachedBuffers.partial(model, state);
        Direction facing = state.getValue(AbstractTinkerDrillBlock.FACING);

        float speed = context.contraption.stalled
                || !VecHelper.isVecPointingTowards(context.relativeMotion, facing
                .getOpposite()) ? context.getAnimationSpeed() : 0;
        float time = AnimationTickHolder.getRenderTime() / 20;
        float angle = ((time * speed) % 360);

        superBuffer
                .transform(matrices.getModel())
                .center()
                .rotateYDegrees(AngleHelper.horizontalAngle(facing))
                .rotateXDegrees(AngleHelper.verticalAngle(facing))
                .rotateZDegrees(angle)
                .uncenter()
                .light(LevelRenderer.getLightColor(renderWorld, context.localPos))
                .useLevelLight(context.world, matrices.getWorld())
                .renderInto(matrices.getViewProjection(), buffer.getBuffer(RenderType.solid()));
    }
}
