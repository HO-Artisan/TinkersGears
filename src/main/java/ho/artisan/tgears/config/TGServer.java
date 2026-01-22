package ho.artisan.tgears.config;

import ho.artisan.tgears.config.data.FanProcessingFactors;
import net.createmod.catnip.config.ConfigBase;

public class TGServer extends ConfigBase {
    public final ConfigGroup server = group(0, "server",
            Comments.server);

    public final ConfigGroup burnerFactors = group(1, "burnerFactors", Comments.burnerFactors);
    public final ConfigFloat extinguishedFactor =
            f(0.5f, .125f, 10, "extinguishedFactor", Comments.extinguishedFactor);
    public final ConfigFloat kindledFactor =
            f(1.0f, .125f, 10, "kindledFactor", Comments.kindledFactor);
    public final ConfigFloat seethingFactor =
            f(2.0f, .125f, 10, "seethingFactor", Comments.seethingFactor);

    public final FanProcessingFactors fanProcessingFactors = nested(1, FanProcessingFactors::new, Comments.fanProcessingFactors);

    public final ConfigGroup fanInteractionSettings = group(1, "fanInteractionSettings", Comments.fanInteractionSettings);

    @Override
    public String getName() {
        return "server";
    }

    private static class Comments {
        static String server = "The Server Config of TGears";

        static String burnerFactors = "The Blaze Burner Factors(temperature = 800 * factor, rate = 10 * factor)";
        static String extinguishedFactor = "Factor when the burner is extinguished";
        static String kindledFactor = "Factor when the burner is kindled";
        static String seethingFactor = "Factor when the burner is seething";

        static String fanProcessingFactors = "Fan processing factors for casting";
        static String fanInteractionSettings = "Fan interaction settings";
    }
}
