package ho.artisan.tgears.common.block;

import ho.artisan.tgears.TinkersGearsConfig;
import ho.artisan.tgears.common.block.entity.TinkerSilkDrillBlockEntity;
import ho.artisan.tgears.index.TGBlockEntityTypes;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TinkerSilkDrillBlock extends AbstractTinkerDrillBlock<TinkerSilkDrillBlockEntity> {
    public TinkerSilkDrillBlock(Properties properties) {
        super(properties);
    }

    @Override
    public double getDamage(float speed) {
        return TinkersGearsConfig.SILK_TOUCH_DRILL_DAMAGE.get();
    }

    @Override
    public Class<TinkerSilkDrillBlockEntity> getBlockEntityClass() {
        return TinkerSilkDrillBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends TinkerSilkDrillBlockEntity> getBlockEntityType() {
        return TGBlockEntityTypes.SILKTOUCH_DRILL.get();
    }
}
