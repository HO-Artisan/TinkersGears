package ho.artisan.tgears.old.block;

import ho.artisan.tgears.old.block.entity.TinkerFortuneDrillBlockEntity;
import ho.artisan.tgears.index.TGBlockEntityTypes;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class TinkerFortuneDrillBlock extends AbstractTinkerDrillBlock<TinkerFortuneDrillBlockEntity> {
    public TinkerFortuneDrillBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<TinkerFortuneDrillBlockEntity> getBlockEntityClass() {
        return TinkerFortuneDrillBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends TinkerFortuneDrillBlockEntity> getBlockEntityType() {
        return TGBlockEntityTypes.FORTUNE_DRILL.get();
    }
}
