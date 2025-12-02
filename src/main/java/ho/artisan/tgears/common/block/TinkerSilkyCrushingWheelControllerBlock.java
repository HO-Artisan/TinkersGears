package ho.artisan.tgears.common.block;

import ho.artisan.tgears.common.block.entity.TinkerSilkyCrushingWheelControllerBlockEntity;
import ho.artisan.tgears.index.TGBlockEntityTypes;
import ho.artisan.tgears.index.TGBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class TinkerSilkyCrushingWheelControllerBlock extends AbstractTinkerCrushingWheelControllerBlock<TinkerSilkyCrushingWheelControllerBlockEntity> {
    public TinkerSilkyCrushingWheelControllerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Supplier<? extends AbstractTinkerCrushingWheelBlock<?>> getWheelBlock() {
        return TGBlocks.TINKER_SILKY_CRUSHING_WHEEL;
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
