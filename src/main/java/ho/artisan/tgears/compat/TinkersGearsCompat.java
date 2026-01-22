package ho.artisan.tgears.compat;

import net.minecraftforge.fml.ModList;

public final class TinkersGearsCompat {
    public static final String CM = "createmetallurgy";
    public static final String CA = "createaddition";
    public static final String AL = "alloyed";
    public static final String CEI = "create_enchantment_industry";
    public static final String TI = "tinkersinnovation";

    public static final boolean METALLURGY_LOADED = ModList.get().isLoaded(CM);
    public static final boolean ALLOYED_LOADED = ModList.get().isLoaded(AL);
    public static final boolean ADDITION_LOADED = ModList.get().isLoaded(CA);
    public static final boolean ENCHANTMENT_LOADED = ModList.get().isLoaded(CEI);

    public static void load() {
    }
}
