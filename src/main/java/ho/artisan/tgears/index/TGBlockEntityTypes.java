package ho.artisan.tgears.index;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import ho.artisan.tgears.client.renderer.TinkerSpoutRenderer;
import ho.artisan.tgears.common.block.entity.TinkerSpoutBlockEntity;

import static ho.artisan.tgears.TinkersGears.REGISTRATE;

public class TGBlockEntityTypes {

    public static final BlockEntityEntry<TinkerSpoutBlockEntity> SPOUT = REGISTRATE
            .blockEntity("tinker_spout", TinkerSpoutBlockEntity::new)
            .validBlocks(TGBlocks.SPOUT)
            .renderer(() -> TinkerSpoutRenderer::new)
            .register();

    public static void register() {

    }
}
