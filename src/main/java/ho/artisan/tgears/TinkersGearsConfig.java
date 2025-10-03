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

    // Goggles
    public static final ForgeConfigSpec.ConfigValue<Boolean> IS_GOGGLES_ENABLED;

    public static final ForgeConfigSpec CLIENT_SPEC, COMMON_SPEC;

    static {
        ForgeConfigSpec.Builder client = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder common = new ForgeConfigSpec.Builder();

        // Common
        common.push("The Blaze Burner Factors(temperature = 800 * factor, rate = 10 * factor)");
        EXTINGUISHED_FACTOR = common.comment("Factor when the burner is extinguished")
                .define("extinguished_factor", 0.5F);
        KINDLED_FACTOR = common.comment("Factor when the burner is kindled")
                .define("kindled_factor", 1.0F);
        SEETHING_FACTOR = common.comment("Factor when the burner is seething")
                .define("seething_factor", 2.0F);
        common.pop();

        common.push("The Polishing Percentages");
        POLISHING_DAMAGE = common.comment("The damage to item when polishing")
                .define("polishing_damage", 0.1F);
        POLISHING_SHIELD = common.comment("The shield provided when polishing")
                .define("polishing_shield", 0.1F);
        common.pop();

        // Client
        client.push("The Client Settings");
        IS_GOGGLES_ENABLED = client.comment("Enable the goggles when looking at TConstruct blocks")
                .define("is_goggles_enabled", true);
        client.pop();

        CLIENT_SPEC = client.build();
        COMMON_SPEC = common.build();
    }
}
