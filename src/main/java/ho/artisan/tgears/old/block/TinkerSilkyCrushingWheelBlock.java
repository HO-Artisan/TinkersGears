package ho.artisan.tgears.old.block;

import com.tterrag.registrate.util.entry.BlockEntry;
import ho.artisan.tgears.old.block.entity.TinkerCrushingWheelBlockEntity;
import ho.artisan.tgears.index.TGBlockEntityTypes;
import ho.artisan.tgears.index.TGBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class TinkerSilkyCrushingWheelBlock extends AbstractTinkerCrushingWheelBlock<TinkerCrushingWheelBlockEntity> {

    public TinkerSilkyCrushingWheelBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntry<? extends AbstractTinkerCrushingWheelControllerBlock<?>> getControllerBlock() {
        return TGBlocks.TINKER_SILKY_CRUSHING_WHEEL_CONTROLLER;
    }

    @Override
    public Supplier<? extends AbstractTinkerCrushingWheelBlock<?>> getWheelBlock() {
        return TGBlocks.TINKER_SILKY_CRUSHING_WHEEL;
    }

    @Override
    public Class<TinkerCrushingWheelBlockEntity> getBlockEntityClass() {
        return TinkerCrushingWheelBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends TinkerCrushingWheelBlockEntity> getBlockEntityType() {
        return TGBlockEntityTypes.TINKER_SILKY_CRUSHING_WHEEL.get();
    }
}
