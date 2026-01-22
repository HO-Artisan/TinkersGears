package ho.artisan.tgears.old.block;

import ho.artisan.tgears.old.block.entity.TinkerFortuneCrushingWheelControllerBlockEntity;
import ho.artisan.tgears.index.TGBlockEntityTypes;
import ho.artisan.tgears.index.TGBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class TinkerFortuneCrushingWheelControllerBlock extends AbstractTinkerCrushingWheelControllerBlock<TinkerFortuneCrushingWheelControllerBlockEntity> {
    public TinkerFortuneCrushingWheelControllerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Supplier<? extends AbstractTinkerCrushingWheelBlock<?>> getWheelBlock() {
        return TGBlocks.TINKER_FORTUNE_CRUSHING_WHEEL;
    }

    @Override
    public Class<TinkerFortuneCrushingWheelControllerBlockEntity> getBlockEntityClass() {
        return TinkerFortuneCrushingWheelControllerBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends TinkerFortuneCrushingWheelControllerBlockEntity> getBlockEntityType() {
        return TGBlockEntityTypes.TINKER_FORTUNE_CRUSHING_WHEEL_CONTROLLER.get();
    }
}
