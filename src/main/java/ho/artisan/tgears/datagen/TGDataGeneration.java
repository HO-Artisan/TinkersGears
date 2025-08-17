package ho.artisan.tgears.datagen;

import com.tterrag.registrate.providers.ProviderType;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.ponder.TGPonderPlugin;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.BiConsumer;

import static ho.artisan.tgears.TinkersGears.REGISTRATE;

@Mod.EventBusSubscriber(modid = TinkersGears.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGDataGeneration {

    @SubscribeEvent
    public static void generate(GatherDataEvent event) {
        REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> {
            BiConsumer<String, String> langConsumer = provider::add;
            providePonderLang(langConsumer);
            provideTinkerLang(langConsumer);
        });
    }

    private static void providePonderLang(BiConsumer<String, String> consumer) {
        PonderIndex.addPlugin(new TGPonderPlugin());
        PonderIndex.getLangAccess().provideLang(TinkersGears.MOD_ID, consumer);
    }

    private static void provideTinkerLang(BiConsumer<String, String> consumer) {
        consumer.accept("itemGroup.tgears.main", "Tinker's Gears");

        consumer.accept("modifier.tgears.topnotch", "Topnotch");
        consumer.accept("modifier.tgears.topnotch.flavor", "Industrial standards!");
        consumer.accept("modifier.tgears.topnotch.description", "Improve many aspects of performance!");

        consumer.accept("material.tgears.andesite_alloy", "Andesite Alloy");
        consumer.accept("material.tgears.andesite_alloy.flavor", "Sturdier Rocks");
        consumer.accept("material.tgears.andesite_alloy.encyclopedia", "It is durable!");
    }
}
