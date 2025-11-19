package ho.artisan.tgears.index;

import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.client.renderer.TinkerDismantlerRenderer;
import ho.artisan.tgears.client.renderer.TinkerDrillRenderer;
import ho.artisan.tgears.client.renderer.TinkerFanRenderer;
import ho.artisan.tgears.client.renderer.TinkerSpoutRenderer;
import ho.artisan.tgears.client.visual.TinkerDrillVisual;
import ho.artisan.tgears.client.visual.TinkerFanVisual;
import ho.artisan.tgears.common.block.entity.*;

import static ho.artisan.tgears.TinkersGears.REGISTRATE;

public final class TGBlockEntityTypes {

    private TGBlockEntityTypes() {
    }


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
            .renderer(() -> TinkerDrillRenderer::common)
            .register();

    public static final BlockEntityEntry<TinkerSilkDrillBlockEntity> SILKTOUCH_DRILL = REGISTRATE
            .blockEntity("tinker_silktouch_drill", TinkerSilkDrillBlockEntity::new)
            .visual(() -> TinkerDrillVisual.create(TGPartialModels.SILKTOUCH_DRILL_HEAD), false)
            .validBlocks(TGBlocks.TINKER_SILKTOUCH_DRILL)
            .renderer(() -> TinkerDrillRenderer::silkTouch)
            .register();

    public static final BlockEntityEntry<TinkerFortuneDrillBlockEntity> FORTUNE_DRILL = REGISTRATE
            .blockEntity("tinker_fortune_drill", TinkerFortuneDrillBlockEntity::new)
            .visual(() -> TinkerDrillVisual.create(TGPartialModels.FORTUNE_DRILL_HEAD), false)
            .validBlocks(TGBlocks.TINKER_FORTUNE_DRILL)
            .renderer(() -> TinkerDrillRenderer::fortune)
            .register();

    public static final BlockEntityEntry<TinkerDismantlerBlockEntity> DISMANTLER = REGISTRATE
            .blockEntity("tinker_dismantler", TinkerDismantlerBlockEntity::new)
            .validBlocks(TGBlocks.TINKER_DISMANTLER)
            .renderer(() -> TinkerDismantlerRenderer::new)
            .register();

    public static final BlockEntityEntry<TinkerCrushingWheelBlockEntity> TINKER_CRUSHING_WHEEL = REGISTRATE
            .blockEntity("tinker_crushing_wheel", TinkerCrushingWheelBlockEntity::new)
            .visual(() -> SingleAxisRotatingVisual.of(TGPartialModels.CRUSHING_WHEEL), false)
            .validBlocks(TGBlocks.TINKER_CRUSHING_WHEEL)
            .renderer(() -> KineticBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<TinkerCrushingWheelBlockEntity> TINKER_SILKY_CRUSHING_WHEEL = REGISTRATE
            .blockEntity("tinker_silky_crushing_wheel", TinkerCrushingWheelBlockEntity::new)
            .visual(() -> SingleAxisRotatingVisual.of(TGPartialModels.SILKY_CRUSHING_WHEEL), false)
            .validBlocks(TGBlocks.TINKER_SILKY_CRUSHING_WHEEL)
            .renderer(() -> KineticBlockEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<TinkerCrushingWheelControllerBlockEntity> TINKER_CRUSHING_WHEEL_CONTROLLER = REGISTRATE
            .blockEntity("tinker_crushing_wheel_controller", TinkerCrushingWheelControllerBlockEntity::new)
            .validBlocks(TGBlocks.TINKER_CRUSHING_WHEEL_CONTROLLER)
            // .renderer(() -> renderer)
            .register();

    public static final BlockEntityEntry<TinkerSilkyCrushingWheelControllerBlockEntity> TINKER_SILKY_CRUSHING_WHEEL_CONTROLLER = REGISTRATE
            .blockEntity("tinker_silky_crushing_wheel_controller", TinkerSilkyCrushingWheelControllerBlockEntity::new)
            .validBlocks(TGBlocks.TINKER_SILKY_CRUSHING_WHEEL_CONTROLLER)
            // .renderer(() -> renderer)
            .register();


    public static void register() {
        TinkersGears.LOGGER.info("BlockEntityTypes initialized");
    }
}
