package ho.artisan.tgears.compat;

import io.github.forgestove.create_cyber_goggles.CCG;

public class CCGConfigSync {
    public static boolean isCardboardOverlayRemoved() {
        return CCG.CONFIG.misc.removeCardboardOverlay;
    }
}
