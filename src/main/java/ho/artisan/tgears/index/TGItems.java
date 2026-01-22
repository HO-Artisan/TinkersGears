package ho.artisan.tgears.index;

import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.equipment.goggles.GogglesItem;
import com.simibubi.create.foundation.item.ItemDescription;
import com.tterrag.registrate.util.entry.ItemEntry;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.common.item.food.BlazingFoodItem;
import ho.artisan.tgears.common.item.food.BlazingLeftoverFoodItem;
import ho.artisan.tgears.common.item.food.LeftoverFoodItem;
import ho.artisan.tgears.util.GogglesUtil;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import static ho.artisan.tgears.TinkersGears.REGISTRATE;

public final class TGItems {

    static {
        REGISTRATE.setCreativeTab(TGCreativeModeTabs.MAIN_TAB);
        REGISTRATE.setTooltipModifierFactory(item ->
                new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
        );
    }

    public static final ItemEntry<Item> CRUSHED_RAW_COBALT =
            REGISTRATE.item("crushed_raw_cobalt", Item::new)
                    .tag(AllTags.AllItemTags.CRUSHED_RAW_MATERIALS.tag)
                    .register();
    public static final ItemEntry<Item> COBALT_SHEET =
            REGISTRATE.item("cobalt_sheet", Item::new)
                    .tag(TGTagKeys.Items.PLATES)
                    .tag(TGTagKeys.Items.COBALT_PLATE)
                    .register();
    public static final ItemEntry<Item> COBALT_PROPELLER =
            REGISTRATE.item("cobalt_propeller", Item::new)
                    .register();

    public static final ItemEntry<Item> ROSE_GOLD_SHEET =
            REGISTRATE.item("rose_gold_sheet", Item::new)
                    .tag(TGTagKeys.Items.PLATES)
                    .tag(TGTagKeys.Items.ROSE_GOLD_PLATE)
                    .register();

    public static final ItemEntry<LeftoverFoodItem> CHOCOLATE_HAND = REGISTRATE.item("chocolate_hand", (properties) -> new LeftoverFoodItem(properties, AllItems.ANDESITE_ALLOY))
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(12)
                    .saturationMod(0.4F)
                    .alwaysEat()
                    .build()))
            .lang("Chocolate Hand")
            .register();
    public static final ItemEntry<BlazingLeftoverFoodItem> BLAZING_CHOCOLATE_HAND = REGISTRATE.item("blazing_chocolate_hand", (properties) -> new BlazingLeftoverFoodItem(properties, AllItems.ANDESITE_ALLOY))
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(16)
                    .saturationMod(0.6F)
                    .alwaysEat()
                    .build()))
            .lang("Blazing Chocolate Hand")
            .register();

    public static final ItemEntry<BlazingFoodItem> BAR_OF_BLAZING_CHOCOLATE = REGISTRATE.item("bar_of_blazing_chocolate", BlazingFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(8)
                    .saturationMod(0.4F)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60), 0.5f)
                    .build()))
            .tag(AllTags.AllItemTags.BLAZE_BURNER_FUEL_SPECIAL.tag)
            .lang("Bar of Blazing Chocolate")
            .register();
    public static final ItemEntry<BlazingFoodItem> BLAZING_CHOCOLATE_BERRIES = REGISTRATE.item("blazing_chocolate_glazed_berries", BlazingFoodItem::new)
            .properties(p -> p.food(new FoodProperties.Builder().nutrition(8)
                    .saturationMod(0.8F)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60), 0.5f)
                    .build()))
            .register();

    public static final ItemEntry<Item> CRUSHED_SCORCHIA = REGISTRATE.item("crushed_scorchia", Item::new)
            .tag(AllTags.AllItemTags.CRUSHED_RAW_MATERIALS.tag)
            .register();

    public static final ItemEntry<Item> RED_STONE_ELEMENT = REGISTRATE.item("red_stone_element", Item::new)
            .register();


    public static void register() {
        GogglesItem.addIsWearingPredicate(GogglesUtil::isWearingGoggles);

        TinkersGears.LOGGER.info("Items initialized");
    }
}
