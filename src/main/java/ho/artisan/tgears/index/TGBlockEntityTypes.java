package ho.artisan.tgears.index;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.client.renderer.TinkerDrillRenderer;
import ho.artisan.tgears.client.renderer.TinkerFanRenderer;
import ho.artisan.tgears.client.renderer.TinkerSpoutRenderer;
import ho.artisan.tgears.client.visual.TinkerDrillVisual;
import ho.artisan.tgears.client.visual.TinkerFanVisual;
import ho.artisan.tgears.common.block.entity.TinkerDrillBlockEntity;
import ho.artisan.tgears.common.block.entity.TinkerFanBlockEntity;
import ho.artisan.tgears.common.block.entity.TinkerSilkDrillBlockEntity;
import ho.artisan.tgears.common.block.entity.TinkerSpoutBlockEntity;

import static ho.artisan.tgears.TinkersGears.REGISTRATE;

public final class TGBlockEntityTypes {

    private TGBlockEntityTypes() {}

    public static final BlockEntityEntry<TinkerSpoutBlockEntity> SPOUT = REGISTRATE
            .blockEntity("tinker_spout", TinkerSpoutBlockEntity::new)
            .validBlocks(TGBlocks.TINKER_SPOUT)
            .renderer(() -> TinkerSpoutRenderer::new)
            .register();

    public static final BlockEntityEntry<TinkerFanBlockEntity> FAN = REGISTRATE
            .blockEntity("tinker_fan", TinkerFanBlockEntity::new)
            .visual(() -> TinkerFanVisual::new, false)
            .validBlocks(TGBlocks.TINKER_FAN)
            .renderer(() -> TinkerFanRenderer::new)
            .register();

    public static final BlockEntityEntry<TinkerDrillBlockEntity> DRILL = REGISTRATE
            .blockEntity("tinker_drill", TinkerDrillBlockEntity::new)
            .visual(() -> TinkerDrillVisual.create(TGPartialModels.DRILL_HEAD), false)
            .validBlocks(TGBlocks.TINKER_DRILL)
            .renderer(() -> context -> new TinkerDrillRenderer(context, TGPartialModels.DRILL_HEAD))
            .register();

    public static final BlockEntityEntry<TinkerSilkDrillBlockEntity> SILKTOUCH_DRILL = REGISTRATE
            .blockEntity("tinker_silktouch_drill", TinkerSilkDrillBlockEntity::new)
            .visual(() -> TinkerDrillVisual.create(TGPartialModels.SILKTOUCH_DRILL_HEAD), false)
            .validBlocks(TGBlocks.TINKER_SILKTOUCH_DRILL)
            .renderer(() -> context -> new TinkerDrillRenderer(context, TGPartialModels.SILKTOUCH_DRILL_HEAD))
            .register();

    public static void register() {
        TinkersGears.LOGGER.info("BlockEntityTypes initialized");
    }
}
