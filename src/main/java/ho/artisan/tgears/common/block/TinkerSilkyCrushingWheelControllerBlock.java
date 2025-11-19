package ho.artisan.tgears.common.block;

import ho.artisan.tgears.common.block.entity.TinkerSilkyCrushingWheelControllerBlockEntity;
import ho.artisan.tgears.index.TGBlockEntityTypes;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TinkerSilkyCrushingWheelControllerBlock extends AbstractTinkerCrushingWheelControllerBlock<TinkerSilkyCrushingWheelControllerBlockEntity> {
    public TinkerSilkyCrushingWheelControllerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<TinkerSilkyCrushingWheelControllerBlockEntity> getBlockEntityClass() {
        return TinkerSilkyCrushingWheelControllerBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends TinkerSilkyCrushingWheelControllerBlockEntity> getBlockEntityType() {
        return TGBlockEntityTypes.TINKER_SILKY_CRUSHING_WHEEL_CONTROLLER.get();
    }
}
