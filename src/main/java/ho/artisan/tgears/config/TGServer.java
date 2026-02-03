package ho.artisan.tgears.config;

import ho.artisan.tgears.config.data.FanProcessingFactors;
import net.createmod.catnip.config.ConfigBase;

public class TGServer extends ConfigBase {
    public final ConfigGroup server = group(0, "server",
            Comments.server);

    public final ConfigGroup burnerSettings = group(1, "burnerSettings", Comments.burnerSettings);

    public final ConfigInt extinguishedTemperature =
            i(400, 0, 10000, "extinguishedTemperature", Comments.extinguishedTemperature);
    public final ConfigInt kindledTemperature =
            i(800, 0, 10000, "kindledTemperature", Comments.kindledTemperature);
    public final ConfigInt seethingTemperature =
            i(1600, 0, 10000, "seethingTemperature", Comments.seethingTemperature);

    public final ConfigInt extinguishedRate =
            i(5, 0, 100, "extinguishedRate", Comments.extinguishedRate);
    public final ConfigInt kindledRate =
            i(10, 0, 100, "kindledRate", Comments.kindledRate);
    public final ConfigInt seethingRate =
            i(20, 0, 100, "seethingRate", Comments.seethingRate);

    public final FanProcessingFactors fanProcessingFactors = nested(1, FanProcessingFactors::new, Comments.fanProcessingFactors);

    public final ConfigGroup fanInteractionSettings = group(1, "fanInteractionSettings", Comments.fanInteractionSettings);

    @Override
    public String getName() {
        return "server";
    }

    private static class Comments {
        static String server = "The Server Config of TGears";

        static String burnerSettings = "The Blaze Burner Settings for TiC";

        static String extinguishedTemperature = "Temperature when the burner is extinguished";
        static String kindledTemperature = "Temperature when the burner is kindled";
        static String seethingTemperature = "Temperature when the burner is seething";

        static String extinguishedRate = "Rate when the burner is extinguished";
        static String kindledRate = "Rate when the burner is kindled";
        static String seethingRate = "Rate when the burner is seething";

        static String fanProcessingFactors = "Fan processing factors for casting";
        static String fanInteractionSettings = "Fan interaction settings";
    }
}
