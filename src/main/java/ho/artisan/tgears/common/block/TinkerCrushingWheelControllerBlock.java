package ho.artisan.tgears.common.block;

import ho.artisan.tgears.common.block.entity.TinkerCrushingWheelControllerBlockEntity;
import ho.artisan.tgears.index.TGBlockEntityTypes;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TinkerCrushingWheelControllerBlock extends AbstractTinkerCrushingWheelControllerBlock<TinkerCrushingWheelControllerBlockEntity> {

    public TinkerCrushingWheelControllerBlock(Properties properties) {
        super(properties);
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
