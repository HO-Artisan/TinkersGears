package ho.artisan.tgears;

import ho.artisan.tgears.ponder.TGPonderPlugin;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class TinkersGearsClient {
    public static void onClientInit(IEventBus bus) {
        bus.addListener(TinkersGearsClient::clientInit);
    }

    public static void clientInit(final FMLClientSetupEvent event) {
        PonderIndex.addPlugin(new TGPonderPlugin());
    }
}
