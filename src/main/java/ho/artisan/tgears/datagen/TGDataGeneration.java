package ho.artisan.tgears.datagen;

import com.tterrag.registrate.providers.ProviderType;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.datagen.provider.lang.*;
import ho.artisan.tgears.datagen.provider.recipe.create.*;
import ho.artisan.tgears.datagen.provider.recipe.tconstruct.*;
import ho.artisan.tgears.datagen.provider.recipe.vanilla.TGBlastingProvider;
import ho.artisan.tgears.datagen.provider.recipe.vanilla.TGCraftingProvider;
import ho.artisan.tgears.datagen.provider.recipe.vanilla.TGSmeltingProvider;
import ho.artisan.tgears.datagen.provider.tag.TGBlockTagGen;
import ho.artisan.tgears.datagen.provider.tag.TGFluidTagGen;
import ho.artisan.tgears.datagen.provider.tag.TGItemTagGen;
import ho.artisan.tgears.datagen.provider.tinker.*;
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

        // Lang
        REGISTRATE.addDataGenerator(ProviderType.LANG, TGCommonLang::new);
        REGISTRATE.addDataGenerator(ProviderType.LANG, TGMaterialLang::new);
        REGISTRATE.addDataGenerator(ProviderType.LANG, TGModifierLang::new);
        REGISTRATE.addDataGenerator(ProviderType.LANG, TGPonderLang::new);
        REGISTRATE.addDataGenerator(ProviderType.LANG, TGRecipeLang::new);
        REGISTRATE.addDataGenerator(ProviderType.LANG, TGTooltipLang::new);

        // Recipes
        vanillaRecipes(add);
        ticRecipes(add);
        createRecipes(add);

        // Tags
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, TGBlockTagGen::new);
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, TGItemTagGen::new);
        REGISTRATE.addDataGenerator(ProviderType.FLUID_TAGS, TGFluidTagGen::new);
    }

    private static void vanillaRecipes(Consumer<Function<PackOutput, ? extends DataProvider>> consumer) {
        consumer.accept(TGCraftingProvider::new);
        consumer.accept(TGBlastingProvider::new);
        consumer.accept(TGSmeltingProvider::new);
    }

    private static void ticRecipes(Consumer<Function<PackOutput, ? extends DataProvider>> consumer) {
        // Modifiers
        consumer.accept(TGModifierProvider::new);

        // Materials
        consumer.accept(TGMaterialDataProvider::new);
        consumer.accept(TGMaterialTraitsDataProvider::new);
        consumer.accept(TGMaterialStatsProvider::new);

        // Tool Definition
        consumer.accept(TGToolDefinitionDataProvider::new);
        consumer.accept(TGMaterialRecipeProvider::new);

        // Recipe
        consumer.accept(TGCastingRecipeProvider::new);
        consumer.accept(TGMeltingRecipeProvider::new);

        consumer.accept(TGAlloyingProvider::new);
        consumer.accept(TGModifierRecipeProvider::new);
        consumer.accept(TGModifierSalvageProvider::new);
    }

    private static void createRecipes(Consumer<Function<PackOutput, ? extends DataProvider>> consumer) {
        consumer.accept(TGCrushingProvider::new);

        consumer.accept(TGPressingProvider::new);

        consumer.accept(TGCompactingProvider::new);
        consumer.accept(TGMixingProvider::new);

        consumer.accept(TGFillingProvider::new);

        consumer.accept(TGDeployingProvider::new);
        consumer.accept(TGItemApplicationRecipeGen::new);

        consumer.accept(TGSplashingProvider::new);

        consumer.accept(TGSequencedAssemblyRecipeGen::new);
    }
}
