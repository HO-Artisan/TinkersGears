package ho.artisan.tgears.common.block;

import ho.artisan.tgears.common.block.entity.TinkerDrillBlockEntity;
import ho.artisan.tgears.index.TGBlockEntityTypes;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TinkerDrillBlock extends AbstractTinkerDrillBlock<TinkerDrillBlockEntity> {
    public TinkerDrillBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<TinkerDrillBlockEntity> getBlockEntityClass() {
        return TinkerDrillBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends TinkerDrillBlockEntity> getBlockEntityType() {
        return TGBlockEntityTypes.DRILL.get();
    }
}
