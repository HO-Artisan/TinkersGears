package ho.artisan.tgears.common.block;

import ho.artisan.tgears.common.block.entity.TinkerCrushingWheelControllerBlockEntity;
import ho.artisan.tgears.index.TGBlockEntityTypes;
import ho.artisan.tgears.index.TGBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class TinkerCrushingWheelControllerBlock extends AbstractTinkerCrushingWheelControllerBlock<TinkerCrushingWheelControllerBlockEntity> {

    public TinkerCrushingWheelControllerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Supplier<? extends AbstractTinkerCrushingWheelBlock<?>> getWheelBlock() {
        return TGBlocks.TINKER_CRUSHING_WHEEL;
    }

    @Override
    public Class<TinkerCrushingWheelControllerBlockEntity> getBlockEntityClass() {
        return TinkerCrushingWheelControllerBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends TinkerCrushingWheelControllerBlockEntity> getBlockEntityType() {
        return TGBlockEntityTypes.TINKER_CRUSHING_WHEEL_CONTROLLER.get();
    }
}
