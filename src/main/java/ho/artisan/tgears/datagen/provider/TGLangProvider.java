package ho.artisan.tgears.datagen.provider;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.ponder.TGPonderPlugin;
import net.createmod.ponder.foundation.PonderIndex;

import java.util.function.BiConsumer;

public class TGLangProvider {

    public void addTranslations(BiConsumer<String, String> consumer) {
        providePonderLang(consumer);
        providerTooltipsLang(consumer);
        provideTGLang(consumer);
        providerModifierLang(consumer);
    }

    private void providerModifierLang(BiConsumer<String, String> consumer) {
        consumer.accept("modifier.tgears.topnotch", "Topnotch");
        consumer.accept("modifier.tgears.topnotch.flavor", "Industrial standards!");
        consumer.accept("modifier.tgears.topnotch.description", "Improve many aspects of performance!");

        consumer.accept("modifier.tgears.polished", "Polished");
        consumer.accept("modifier.tgears.polished.flavor", "Look brand new!");
        consumer.accept("modifier.tgears.polished.description", "Get a durability shield and damage bonus!");

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

        consumer.accept("modifier.tgears.cardboard", "Cardboard");
        consumer.accept("modifier.tgears.cardboard.flavor", "Harmless but \"Powerful\"!");
        consumer.accept("modifier.tgears.cardboard.description", "No damage can be done, but there is a knockback bonus.");

        consumer.accept("modifier.tgears.sharpness", "Rose Sharpness");
        consumer.accept("modifier.tgears.sharpness.flavor", "Very Sharp!");
        consumer.accept("modifier.tgears.sharpness.description", "Tool is so much sharper, dealing more damage!");

        //consumer.accept("modifier.tgears.adrenaline", "Adrenaline");
        //consumer.accept("modifier.tgears.adrenaline.flavor", "Speed up!");
        //consumer.accept("modifier.tgears.adrenaline.description", "Upon taking damage, briefly increase movement speed and attack speed.");

        //consumer.accept("modifier.tgears.lightweight", "Lightweight");
        //consumer.accept("modifier.tgears.lightweight.flavor", "Like a Shardblade!");
        //consumer.accept("modifier.tgears.lightweight.description", "Tool is incredibly light for its size, allowing you to attack, mine, and launch projectiles faster");

    }

    private void provideTGLang(BiConsumer<String, String> consumer) {
        consumer.accept("itemGroup.tgears.main", "Tinker's Gears");
        consumer.accept("gui.tgears.integrated_smeltery", "Integrated Smeltery");

        consumer.accept("material.tgears.andesite_alloy", "Andesite Alloy");
        consumer.accept("material.tgears.andesite_alloy.flavor", "Sturdier Rocks");
        consumer.accept("material.tgears.andesite_alloy.encyclopedia", "It is durable!");

        consumer.accept("material.tgears.cardboard", "Cardboard");
        consumer.accept("material.tgears.cardboard.flavor", "Unadulterated Lightweight");
        consumer.accept("material.tgears.cardboard.encyclopedia", "Harmless but ultra-light!");

        consumer.accept("material.tgears.rose_quartz", "Rose Quartz");
        consumer.accept("material.tgears.rose_quartz.flavor", "A Unique Crystal");
        consumer.accept("material.tgears.rose_quartz.encyclopedia", "A unique crystal with a sharp quality.");

        //consumer.accept("material.tgears.luzzium", "Luzzium");
        //consumer.accept("material.tgears.luzzium.flavor", "Lightning Fast");
        //consumer.accept("material.tgears.luzzium.encyclopedia", "Lightweight beyond imagination!");

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

    private void providerTooltipsLang(BiConsumer<String, String> consumer) {
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

        consumer.accept("item.tgears.propeller_cast_with_cobalt_propeller.tooltip.summary", "Contain a _Cobalt Propeller_.");
        consumer.accept("item.tgears.propeller_cast_with_cobalt_propeller.tooltip.condition1", "Right click");
        consumer.accept("item.tgears.propeller_cast_with_cobalt_propeller.tooltip.behaviour1", "_Separate_ the _propeller_ from the _cast_.");

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

    private void providePonderLang(BiConsumer<String, String> consumer) {
        PonderIndex.addPlugin(new TGPonderPlugin());
        PonderIndex.getLangAccess().provideLang(TinkersGears.MOD_ID, consumer);
    }
}
