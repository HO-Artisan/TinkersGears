package ho.artisan.tgears.index;

import ho.artisan.tgears.TinkersGears;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public class TGModifierIds {
    public static final ModifierId ductile = id("topnotch");

    private static ModifierId id(String name) {
        return new ModifierId(TinkersGears.MOD_ID, name);
    }
}
