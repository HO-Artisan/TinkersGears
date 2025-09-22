package ho.artisan.tgears.client.visual;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.OrientedRotatingVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.engine_room.flywheel.lib.visualization.SimpleBlockEntityVisualizer;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class TinkerDrillVisual<T extends KineticBlockEntity> extends OrientedRotatingVisual<T> {
    public TinkerDrillVisual(VisualizationContext context, T blockEntity, float partialTick, Direction from, Direction to, PartialModel model) {
        super(context, blockEntity, partialTick, from, to, Models.partial(model));
    }

    public static <T extends KineticBlockEntity> SimpleBlockEntityVisualizer.Factory<T> create(PartialModel model) {
        return (context, blockEntity, partialTick) -> {
            Direction facing = blockEntity.getBlockState().getValue(BlockStateProperties.FACING);
            return new TinkerDrillVisual<>(context, blockEntity, partialTick, Direction.SOUTH, facing, model);
        };
    }
}
