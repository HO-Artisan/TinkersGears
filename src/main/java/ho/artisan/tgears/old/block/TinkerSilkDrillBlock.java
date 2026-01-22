package ho.artisan.tgears.old.block;

import ho.artisan.tgears.old.block.entity.TinkerSilkDrillBlockEntity;
import ho.artisan.tgears.index.TGBlockEntityTypes;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TinkerSilkDrillBlock extends AbstractTinkerDrillBlock<TinkerSilkDrillBlockEntity> {
    public TinkerSilkDrillBlock(Properties properties) {
        super(properties);
    }

    @Override
    public double getDamage(float speed) {
        return 1.0D;
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
