package ho.artisan.tgears;

import com.simibubi.create.foundation.data.CreateRegistrate;
import ho.artisan.tgears.index.TGBlockEntityTypes;
import ho.artisan.tgears.index.TGBlocks;
import ho.artisan.tgears.index.TGCreativeModeTabs;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TinkersGears.MOD_ID)
public class TinkersGears {
    public static final String MOD_ID = "tgears";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String NAME = "Tinker's Gears";

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public TinkersGears() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        TGCreativeModeTabs.register(bus);
        REGISTRATE.registerEventListeners(bus);

        TGBlocks.register();
        TGBlockEntityTypes.register();
        TGItems.register();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> TinkersGearsClient.onClientInit(bus));

        bus.register(this);
    }


    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(TinkersGears.MOD_ID, path);
    }
}
