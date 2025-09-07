package ho.artisan.tgears.index;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import ho.artisan.tgears.TinkersGears;

public final class TGPartialModels {

    private TGPartialModels() {}

    public static final PartialModel
            SPOUT_TOP = block("tinker_spout/top"), SPOUT_MIDDLE = block("tinker_spout/middle"), SPOUT_BOTTOM = block("tinker_spout/bottom");

    private static PartialModel block(String path) {
        return PartialModel.of(TinkersGears.asResource("block/" + path));
    }

    public static void init() {
        TinkersGears.LOGGER.info("Partial models initialized");
    }
}
