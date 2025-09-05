package ho.artisan.tgears.datagen;

import com.tterrag.registrate.providers.ProviderType;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.datagen.provider.TGearMaterialTraitsDataProvider;
import ho.artisan.tgears.datagen.provider.TGearMaterialDataProvider;
import ho.artisan.tgears.ponder.TGPonderPlugin;
import ho.artisan.tgears.datagen.provider.TGearMaterialStatsProvider;
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
            provideTGLang(langConsumer);
            providerModifierLang(langConsumer);
            providerTooltipsLang(langConsumer);
        });

        TGearMaterialDataProvider materialProvider = new TGearMaterialDataProvider(event.getGenerator().getPackOutput());
        event.getGenerator().addProvider(event.includeServer(), materialProvider);
        event.getGenerator().addProvider(event.includeServer(), new TGearMaterialTraitsDataProvider(event.getGenerator().getPackOutput(), materialProvider));
        event.getGenerator().addProvider(event.includeServer(), new TGearMaterialStatsProvider(event.getGenerator().getPackOutput(), materialProvider));
    }


    private static void providePonderLang(BiConsumer<String, String> consumer) {
        PonderIndex.addPlugin(new TGPonderPlugin());
        PonderIndex.getLangAccess().provideLang(TinkersGears.MOD_ID, consumer);
    }

    private static void providerTooltipsLang(BiConsumer<String, String> consumer) {
        consumer.accept("item.tgears.hand_cast_with_brass_hand.tooltip.summary", "Contain a _Brass Hand_.");
        consumer.accept("item.tgears.hand_cast_with_brass_hand.tooltip.condition1", "Right click");
        consumer.accept("item.tgears.hand_cast_with_brass_hand.tooltip.behaviour1", "_Separate_ the _hand_ from the _cast_.");

        consumer.accept("item.tgears.hand_cast_with_chocolate_hand.tooltip.summary", "Contain a _Chocolate Hand_.");
        consumer.accept("item.tgears.hand_cast_with_chocolate_hand.tooltip.condition1", "Right click");
        consumer.accept("item.tgears.hand_cast_with_chocolate_hand.tooltip.behaviour1", "_Separate_ the _hand_ from the _cast_.");

        consumer.accept("item.tgears.hand_cast_with_blazing_chocolate_hand.tooltip.summary", "Contain a _Blazing Chocolate Hand_.");
        consumer.accept("item.tgears.hand_cast_with_blazing_chocolate_hand.tooltip.condition1", "Right click");
        consumer.accept("item.tgears.hand_cast_with_blazing_chocolate_hand.tooltip.behaviour1", "_Separate_ the _hand_ from the _cast_.");

        consumer.accept("item.tgears.chocolate_hand.tooltip.summary", "Be careful not to eat Andesite Alloy!");
        consumer.accept("item.tgears.blazing_chocolate_hand.tooltip.summary", "Be careful not to eat Andesite Alloy!");

        consumer.accept("item.tgears.propeller_cast_with_propeller.tooltip.summary", "Contain a _Propeller_.");
        consumer.accept("item.tgears.propeller_cast_with_propeller.tooltip.condition1", "Right click");
        consumer.accept("item.tgears.propeller_cast_with_propeller.tooltip.behaviour1", "_Separate_ the _propeller_ from the _cast_.");

        consumer.accept("item.tgears.whisk_cast_with_whisk.tooltip.summary", "Contain a _Whisk_.");
        consumer.accept("item.tgears.whisk_cast_with_whisk.tooltip.condition1", "Right click");
        consumer.accept("item.tgears.whisk_cast_with_whisk.tooltip.behaviour1", "_Separate_ the _whisk_ from the _cast_.");

        consumer.accept("item.tgears.whisk_cast_with_sturdy_whisk.tooltip.summary", "Contain a _Sturdy Whisk_.");
        consumer.accept("item.tgears.whisk_cast_with_sturdy_whisk.tooltip.condition1", "Right click");
        consumer.accept("item.tgears.whisk_cast_with_sturdy_whisk.tooltip.behaviour1", "_Separate_ the _whisk_ from the _cast_.");

        consumer.accept("item.tgears.blazing_chocolate_bucket.tooltip.summary", "_Superhigh Calorie_ liquid, which is the Blaze love best!");

        consumer.accept("item.tgears.bar_of_blazing_chocolate.tooltip.summary", "_Superhigh Calorie_ food, which is the Blaze love best!");


        consumer.accept("item.create.sand_paper.tooltip.behaviour2", "Let the _tool_ get _polished_ and _sharpened_.");
        consumer.accept("item.create.sand_paper.tooltip.condition2", "When Polishing Tinker's Tools");
    }

    private static void provideTGLang(BiConsumer<String, String> consumer) {
        consumer.accept("itemGroup.tgears.main", "Tinker's Gears");

        consumer.accept("material.tgears.andesite_alloy", "Andesite Alloy");
        consumer.accept("material.tgears.andesite_alloy.flavor", "Sturdier Rocks");
        consumer.accept("material.tgears.andesite_alloy.encyclopedia", "It is durable!");

        consumer.accept("create.generic.unit.percent", "%");
        consumer.accept("create.generic.unit.temperature", "°C");

        consumer.accept("create.tooltip.gears.tinker_stats", "Tinker Stats:");
        consumer.accept("create.tooltip.gears.tinker_stats.fluid", "Fluid:");
        consumer.accept("create.tooltip.gears.temperature", "Temperature:");
        consumer.accept("create.tooltip.gears.rate", "Rate:");
        consumer.accept("create.tooltip.gears.progress", "Progress:");
        consumer.accept("create.tooltip.gears.output", "Output:");

        consumer.accept("create.gui.goggles.at_current_heating", "at current heating");
        consumer.accept("create.gui.goggles.at_current_recipe", "at current recipe");
    }

    private static void providerModifierLang(BiConsumer<String, String> consumer) {
        consumer.accept("modifier.tgears.topnotch", "Topnotch");
        consumer.accept("modifier.tgears.topnotch.flavor", "Industrial standards!");
        consumer.accept("modifier.tgears.topnotch.description", "Improve many aspects of performance!");

        consumer.accept("modifier.tgears.tapering", "Tapering");
        consumer.accept("modifier.tgears.tapering.flavor", "Effortless and smooth!");
        consumer.accept("modifier.tgears.tapering.description", "Increase attack damage!");

        consumer.accept("modifier.tgears.polished", "Polished");
        consumer.accept("modifier.tgears.polished.flavor", "Look brand new!");
        consumer.accept("modifier.tgears.polished.description", "Increase durability!");

        consumer.accept("modifier.tgears.crushing", "Crushing");
        consumer.accept("modifier.tgears.crushing.flavor", "Shrink mountains to pebbles");
        consumer.accept("modifier.tgears.crushing.description", "Crush when destroyed!");

        consumer.accept("modifier.tgears.goggles", "Engineer's Goggles");
        consumer.accept("modifier.tgears.goggles.flavor", "Create style");
        consumer.accept("modifier.tgears.goggles.description", "Create Google information!");

        consumer.accept("modifier.tgears.extendo", "Engineer's Extendo Grip");
        consumer.accept("modifier.tgears.extendo.flavor", "Boioioing!");
        consumer.accept("modifier.tgears.extendo.description", "Increase the intersection range!");

        consumer.accept("modifier.tgears.diving_weights", "Diving Weights");
        consumer.accept("modifier.tgears.diving_weights.flavor", "Dive with ease!");
        consumer.accept("modifier.tgears.diving_weights.description", "Wielder descends more quickly in liquids. Grants the ability to walk and jump underwater. Wielder also is no longer affected by Mechanical Belts.");
    }
}
