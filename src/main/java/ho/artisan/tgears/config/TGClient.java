package ho.artisan.tgears.config;

import net.createmod.catnip.config.ConfigBase;

public class TGClient extends ConfigBase {
    public final ConfigGroup server = group(0, "client",
            TGClient.Comments.client);

    public final ConfigBool enableGoggleOverlay = b(true, "enableGoggleOverlay", Comments.enableGoggleOverlay);

    @Override
    public String getName() {
        return "client";
    }

    private static class Comments {
        static String client = "The Client Config of TGears";
        static String enableGoggleOverlay = "Whether to enable goggle overlay for TiC's blocks";
    }
}
