package ho.artisan.tgears;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import ho.artisan.tgears.client.TinkersGearsClient;
import ho.artisan.tgears.compat.TinkersGearsCompat;
import ho.artisan.tgears.index.*;
import ho.artisan.tgears.tools.PartMaterialStats;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TinkersGears.MOD_ID)
public class TinkersGears {
    public static final String MOD_ID = "tgears";
    public static final String NAME = "Tinkers' Gears";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID).setTooltipModifierFactory(item ->
            new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                    .andThen(TooltipModifier.mapNull(KineticStats.create(item)))
    );

    public TinkersGears() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext context = ModLoadingContext.get();
        TinkersGearsCompat.load();

        TGCreativeModeTabs.register(bus);
        TGFanProcessingTypes.register(bus);

        REGISTRATE.registerEventListeners(bus);

        TGBlocks.register();
        TGBlockEntityTypes.register();
        TGItems.register();

        TGTinkerItems.ITEMS.register(bus);
        TGFluids.register();
        TGRecipeTypes.register(bus);

        TGModifiers.register(bus);

        TinkersGearsConfig.register(context);

        PartMaterialStats.register();

        bus.addListener(TinkersGearsClient::clientInit);
        bus.addListener(TinkersGears::init);

        bus.addListener(TinkersGears::onRegister);

        bus.register(this);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.tryBuild(TinkersGears.MOD_ID, path);
    }

    public static void onRegister(final RegisterEvent event) {
        TGArmInteractionPointTypes.init();
    }

    public static void init(final FMLCommonSetupEvent event) {
        TGFluids.registerFluidInteractions();
    }
}
