package ho.artisan.tgears;

import com.simibubi.create.foundation.data.CreateRegistrate;
import ho.artisan.tgears.client.TinkersGearsClient;
import ho.artisan.tgears.index.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TinkersGears.MOD_ID)
public class TinkersGears {
    public static final String MOD_ID = "tgears";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String NAME = "Tinker's Gears";

    public static boolean METALLURGY_LOADED = false;
    public static boolean ALLOYED_LOADED = false;
    public static boolean ADDITION_LOADED = false;

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public TinkersGears() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, TinkersGearsConfig.CLIENT_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TinkersGearsConfig.COMMON_SPEC);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        METALLURGY_LOADED = ModList.get().isLoaded("createmetallurgy");
        ALLOYED_LOADED = ModList.get().isLoaded("alloyed");
        ADDITION_LOADED = ModList.get().isLoaded("createaddition");

        TGCreativeModeTabs.register(bus);
        REGISTRATE.registerEventListeners(bus);

        TGBlocks.register();
        TGBlockEntityTypes.register();
        TGItems.register();

        TGModifiers.register(bus);

        bus.addListener(TinkersGearsClient::clientInit);

        bus.register(this);
    }


    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(TinkersGears.MOD_ID, path);
    }
}
