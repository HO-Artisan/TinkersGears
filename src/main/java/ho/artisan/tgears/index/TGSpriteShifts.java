package ho.artisan.tgears.index;

import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;
import ho.artisan.tgears.TinkersGears;

public final class TGSpriteShifts {
    private TGSpriteShifts() {}

    public static final CTSpriteShiftEntry COBALT_CASING = omni("cobalt_casing");

    private static CTSpriteShiftEntry omni(String name) {
        return getCT(AllCTTypes.OMNIDIRECTIONAL, name);
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName, String connectedTextureName) {
        return CTSpriteShifter.getCT(type, TinkersGears.asResource("block/" + blockTextureName),
                TinkersGears.asResource("block/" + connectedTextureName + "_connected"));
    }

    private static CTSpriteShiftEntry getCT(CTType type, String blockTextureName) {
        return getCT(type, blockTextureName, blockTextureName);
    }

    public static void register() {
        TinkersGears.LOGGER.info("Sprite shifts initialized");
    }
}
