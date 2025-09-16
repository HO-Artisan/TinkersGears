package ho.artisan.tgears;

import net.minecraftforge.common.ForgeConfigSpec;

public class TinkersGearsConfig {

    // Blaze Burner
    public static final ForgeConfigSpec.ConfigValue<Float> EXTINGUISHED_FACTOR;
    public static final ForgeConfigSpec.ConfigValue<Float> KINDLED_FACTOR;
    public static final ForgeConfigSpec.ConfigValue<Float> SEETHING_FACTOR;

    // Polishing
    public static final ForgeConfigSpec.ConfigValue<Float> POLISHING_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Float> POLISHING_SHIELD;

    // Adrenaline
    public static final ForgeConfigSpec.ConfigValue<Float> ADRENALINE_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> ADRENALINE_MAX_LEVEL;
    public static final ForgeConfigSpec.ConfigValue<Float> ADRENALINE_MULTIPLIER_PER_LEVEL;

    public static final ForgeConfigSpec.ConfigValue<Boolean> ADRENALINE_DAMAGE_FLAG;
    // Goggles
    public static final ForgeConfigSpec.ConfigValue<Boolean> IS_GOGGLES_ENABLED;

    public static final ForgeConfigSpec CLIENT_SPEC, COMMON_SPEC;

    static {
        ForgeConfigSpec.Builder client = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder common = new ForgeConfigSpec.Builder();

        // Common
        common.comment("The Blaze Burner Factors");
        common.comment("- temperature = 800 * factor");
        common.comment("- rate = 10 * factor");
        EXTINGUISHED_FACTOR = common.comment("Factor when the burner is extinguished")
                .define("extinguished_factor", 0.5F);
        KINDLED_FACTOR = common.comment("Factor when the burner is kindled")
                .define("kindled_factor", 1F);
        SEETHING_FACTOR = common.comment("Factor when the burner is seething")
                .define("seething_factor", 2F);

        common.comment("The Polishing Percentages");
        POLISHING_DAMAGE = common.comment("The damage to item when polishing")
                .define("polishing_damage", 0.1F);
        POLISHING_SHIELD = common.comment("The shield provided when polishing")
                .define("polishing_shield", 0.1F);

        common.comment("The Adrenaline Settings");
        ADRENALINE_CHANCE = common.comment("The chance to get adrenaline when being attacked")
                .define("adrenaline_chance", 0.5F);
        ADRENALINE_MAX_LEVEL = common.comment("The max level of adrenaline")
                .define("adrenaline_max_level", 5);
        ADRENALINE_MULTIPLIER_PER_LEVEL = common.comment("The multiplier per level of adrenaline")
                .define("adrenaline_multiplier_per_level", 0.05f);
        ADRENALINE_DAMAGE_FLAG = common.comment("Enable the damage bonus when adrenaline is active")
                .define("adrenaline_damage_flag", false);

        // Client
        IS_GOGGLES_ENABLED = client.comment("Enable the goggles when looking at TConstruct blocks")
                .define("is_goggles_enabled", true);

        CLIENT_SPEC = client.build();
        COMMON_SPEC = common.build();
    }
}
