package ho.artisan.tgears.common.block;

import ho.artisan.tgears.common.block.entity.TinkerCrushingWheelBlockEntity;
import ho.artisan.tgears.index.TGBlockEntityTypes;
import ho.artisan.tgears.index.TGBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class TinkerCrushingWheelBlock extends AbstractTinkerCrushingWheelBlock<TinkerCrushingWheelBlockEntity> {

    public TinkerCrushingWheelBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Supplier<? extends AbstractTinkerCrushingWheelControllerBlock<?>> getControllerBlock() {
        return TGBlocks.TINKER_CRUSHING_WHEEL_CONTROLLER;
    }

    @Override
    public Supplier<? extends AbstractTinkerCrushingWheelBlock<?>> getWheelBlock() {
        return TGBlocks.TINKER_CRUSHING_WHEEL;
    }

    @Override
    public Class<TinkerCrushingWheelBlockEntity> getBlockEntityClass() {
        return TinkerCrushingWheelBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends TinkerCrushingWheelBlockEntity> getBlockEntityType() {
        return TGBlockEntityTypes.TINKER_CRUSHING_WHEEL.get();
    }
}
