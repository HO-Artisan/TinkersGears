package ho.artisan.tgears.datagen;

import com.tterrag.registrate.providers.ProviderType;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.datagen.provider.TGLangProvider;
import ho.artisan.tgears.datagen.provider.recipe.TGCraftingProvider;
import ho.artisan.tgears.datagen.provider.recipe.create.*;
import ho.artisan.tgears.datagen.provider.recipe.tconstruct.TGCastingRecipeProvider;
import ho.artisan.tgears.datagen.provider.recipe.tconstruct.TGMeltingRecipeProvider;
import ho.artisan.tgears.datagen.provider.recipe.tconstruct.material.TGMaterialDataProvider;
import ho.artisan.tgears.datagen.provider.recipe.tconstruct.material.TGMaterialRecipeProvider;
import ho.artisan.tgears.datagen.provider.recipe.tconstruct.material.TGMaterialStatsProvider;
import ho.artisan.tgears.datagen.provider.recipe.tconstruct.material.TGMaterialTraitsDataProvider;
import ho.artisan.tgears.index.TGTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Consumer;
import java.util.function.Function;

import static ho.artisan.tgears.TinkersGears.REGISTRATE;

@Mod.EventBusSubscriber(modid = TinkersGears.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGDataGeneration {

    @SubscribeEvent
    public static void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();

        Consumer<Function<PackOutput, ? extends DataProvider>> add = (func) -> {
            generator.addProvider(event.includeServer(), func.apply(output));
        };

        REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> {
            new TGLangProvider().addTranslations(provider::add);
        });

        // Vanilla
        add.accept(TGCraftingProvider::new);

        // TConstruct
        TGMaterialDataProvider materialProvider = new TGMaterialDataProvider(output);
        generator.addProvider(event.includeServer(), materialProvider);

        generator.addProvider(event.includeServer(), new TGMaterialTraitsDataProvider(output, materialProvider));
        generator.addProvider(event.includeServer(), new TGMaterialStatsProvider(output, materialProvider));

        add.accept(TGMaterialRecipeProvider::new);

        add.accept(TGCastingRecipeProvider::new);
        add.accept(TGMeltingRecipeProvider::new);

        // Create
        add.accept(TGPressingProvider::new);
        add.accept(TGCompactingProvider::new);
        add.accept(TGFillingProvider::new);
        add.accept(TGMixingProvider::new);
        add.accept(TGCrushingProvider::new);
        add.accept(TGDeployingProvider::new);

        addExtraRegistrateData();
    }

    private static void addExtraRegistrateData() {
        TGTags.addGenerators();
    }
}
