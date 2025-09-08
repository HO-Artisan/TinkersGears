package ho.artisan.tgears.datagen;

import com.tterrag.registrate.providers.ProviderType;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.datagen.provider.TGLangProvider;
import ho.artisan.tgears.datagen.provider.recipe.create.*;
import ho.artisan.tgears.datagen.provider.recipe.tconstruct.TGCastingRecipeProvider;
import ho.artisan.tgears.datagen.provider.recipe.tconstruct.TGMeltingRecipeProvider;
import ho.artisan.tgears.datagen.provider.recipe.tconstruct.material.TGMaterialDataProvider;
import ho.artisan.tgears.datagen.provider.recipe.tconstruct.material.TGMaterialRecipeProvider;
import ho.artisan.tgears.datagen.provider.recipe.tconstruct.material.TGMaterialStatsProvider;
import ho.artisan.tgears.datagen.provider.recipe.tconstruct.material.TGMaterialTraitsDataProvider;
import ho.artisan.tgears.index.TGTags;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static ho.artisan.tgears.TinkersGears.REGISTRATE;

@Mod.EventBusSubscriber(modid = TinkersGears.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGDataGeneration {

    @SubscribeEvent
    public static void generate(GatherDataEvent event) {
        DataGenerator generator =  event.getGenerator();

        REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> {
            new TGLangProvider().addTranslations(provider::add);
        });

        // TConstruct
        TGMaterialDataProvider materialProvider = new TGMaterialDataProvider(generator.getPackOutput());
        generator.addProvider(event.includeServer(), materialProvider);

        generator.addProvider(event.includeServer(), new TGMaterialTraitsDataProvider(generator.getPackOutput(), materialProvider));
        generator.addProvider(event.includeServer(), new TGMaterialStatsProvider(generator.getPackOutput(), materialProvider));
        generator.addProvider(event.includeServer(), new TGMaterialRecipeProvider(generator.getPackOutput()));

        generator.addProvider(event.includeServer(), new TGCastingRecipeProvider(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new TGMeltingRecipeProvider(generator.getPackOutput()));

        // Create
        generator.addProvider(event.includeServer(), new TGPressingProvider(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new TGCompactingProvider(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new TGFillingProvider(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new TGMixingProvider(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new TGCrushingProvider(generator.getPackOutput()));
        generator.addProvider(event.includeServer(), new TGItemApplicationProvider(generator.getPackOutput()));

        // Create: Addition


        addExtraRegistrateData();
    }

    private static void addExtraRegistrateData() {
        TGTags.addGenerators();
    }
}
