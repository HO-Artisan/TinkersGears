package ho.artisan.tgears.client;

import ho.artisan.tgears.index.TGPartialModels;
import ho.artisan.tgears.ponder.TGPonderPlugin;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class TinkersGearsClient {
    public static void clientInit(final FMLClientSetupEvent event) {
        TGPartialModels.init();

        event.enqueueWork(() -> {
            PonderIndex.addPlugin(new TGPonderPlugin());
        });
    }
}
