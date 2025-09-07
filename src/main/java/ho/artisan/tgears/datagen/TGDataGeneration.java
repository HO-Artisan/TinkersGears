package ho.artisan.tgears.datagen;

import com.tterrag.registrate.providers.ProviderType;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.datagen.provider.TGLangProvider;
import ho.artisan.tgears.datagen.provider.create.TGCompactingRecipe;
import ho.artisan.tgears.datagen.provider.create.TGFillingRecipe;
import ho.artisan.tgears.datagen.provider.create.TGMixingRecipe;
import ho.artisan.tgears.datagen.provider.create.TGPressingRecipe;
import ho.artisan.tgears.datagen.provider.material.TGMaterialDataProvider;
import ho.artisan.tgears.datagen.provider.material.TGMaterialStatsProvider;
import ho.artisan.tgears.datagen.provider.material.TGMaterialTraitsDataProvider;
import ho.artisan.tgears.datagen.provider.tconstruct.TGMaterialRecipeProvider;
import ho.artisan.tgears.datagen.provider.tconstruct.TGSmelteryRecipeProvider;
import ho.artisan.tgears.ponder.TGPonderPlugin;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.data.DataGenerator;
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
        });

        DataGenerator generator =  event.getGenerator();

        // TConstruct
        TGMaterialDataProvider materialProvider = new TGMaterialDataProvider(generator.getPackOutput());
        generator.addProvider(event.includeServer(), materialProvider);

        generator.addProvider(event.includeServer(), new TGMaterialTraitsDataProvider(generator.getPackOutput(), materialProvider));
        generator.addProvider(event.includeServer(), new TGMaterialStatsProvider(generator.getPackOutput(), materialProvider));
        generator.addProvider(event.includeServer(), new TGSmelteryRecipeProvider(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new TGMaterialRecipeProvider(generator.getPackOutput()));

        // Create
        generator.addProvider(event.includeServer(), new TGPressingRecipe(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new TGCompactingRecipe(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new TGFillingRecipe(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new TGMixingRecipe(generator.getPackOutput()));

        // TGears
        generator.addProvider(event.includeServer(), new TGLangProvider(generator.getPackOutput()));
    }

    private static void providePonderLang(BiConsumer<String, String> consumer) {
        PonderIndex.addPlugin(new TGPonderPlugin());
        PonderIndex.getLangAccess().provideLang(TinkersGears.MOD_ID, consumer);
    }
}
