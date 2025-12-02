package ho.artisan.tgears.index;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import ho.artisan.tgears.TinkersGears;

public final class TGPartialModels {

    private TGPartialModels() {
    }

    public static final PartialModel
            SPOUT_TOP = block("tinker_spout/top"),
            SPOUT_MIDDLE = block("tinker_spout/middle"),
            SPOUT_BOTTOM = block("tinker_spout/bottom"),
            SPOUT_CONNECTION = block("tinker_spout/connection"),
            SPOUT_CONNECTION_ON = block("tinker_spout/connection_on");
    public static final PartialModel
            DRILL_HEAD = block("tinker_drill/head"),
            SILKTOUCH_DRILL_HEAD = block("tinker_silktouch_drill/head"),
            FORTUNE_DRILL_HEAD = block("tinker_fortune_drill/head");
    public static final PartialModel
            CRUSHING_WHEEL = block("tinker_crushing_wheel/block"),
            SILKY_CRUSHING_WHEEL = block("tinker_silktouch_crushing_wheel/block"),
            FORTUNE_CRUSHING_WHEEL = block("tinker_fortune_crushing_wheel/block");
    public static final PartialModel
            COBALT_FAN = block("tinker_fan/propeller");

    private static PartialModel block(String path) {
        return PartialModel.of(TinkersGears.asResource("block/" + path));
    }

    private static PartialModel item(String path) {
        return PartialModel.of(TinkersGears.asResource("item/" + path));
    }


    public static void register() {
        TinkersGears.LOGGER.info("Partial models initialized");
    }
}
