package ho.artisan.tgears.client;

import ho.artisan.tgears.index.TGPartialModels;
import ho.artisan.tgears.index.TGSpriteShifts;
import ho.artisan.tgears.client.ponder.TGPonderPlugin;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class TinkersGearsClient {
    public static void clientInit(final FMLClientSetupEvent event) {
        TGPartialModels.register();
        TGSpriteShifts.register();

        event.enqueueWork(() -> {
            PonderIndex.addPlugin(new TGPonderPlugin());
        });
    }
}
