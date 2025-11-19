package ho.artisan.tgears.index;

import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.equipment.goggles.GogglesItem;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.item.ItemDescription;
import com.tterrag.registrate.util.entry.ItemEntry;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.common.item.TinkerWrenchItem;
import ho.artisan.tgears.common.item.casting.HandCastItem;
import ho.artisan.tgears.common.item.casting.PropellerCastItem;
import ho.artisan.tgears.common.item.casting.SturdyWhiskCastItem;
import ho.artisan.tgears.common.item.casting.WhiskCastItem;
import ho.artisan.tgears.common.item.food.BlazingFoodItem;
import ho.artisan.tgears.common.item.food.BlazingLeftoverFoodItem;
import ho.artisan.tgears.common.item.food.LeftoverFoodItem;
import ho.artisan.tgears.util.TinkerGogglesUtil;
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

    private TGItems() {
    }


    public static final ItemEntry<Item> CRUSHED_RAW_COBALT =
            REGISTRATE.item("crushed_raw_cobalt", Item::new)
                    .tag(AllTags.AllItemTags.CRUSHED_RAW_MATERIALS.tag)
                    .register();
    public static final ItemEntry<Item> COBALT_SHEET =
            REGISTRATE.item("cobalt_sheet", Item::new)
                    .tag(TGTagKeys.Items.PLATES)
                    .tag(TGTagKeys.Items.COBALT_SHEET)
                    .register();
    public static final ItemEntry<Item> ROSE_GOLD_SHEET =
            REGISTRATE.item("rose_gold_sheet", Item::new)
                    .tag(TGTagKeys.Items.PLATES)
                    .tag(TGTagKeys.Items.ROSE_GOLD_SHEET)
                    .register();
    public static final ItemEntry<Item> HAND_CAST =
            REGISTRATE.item("hand_cast", Item::new)
                    .lang("Hand Gold Cast")
                    .register();
    public static final ItemEntry<Item> HAND_CAST_WITH_PART =
            REGISTRATE.item("hand_cast_with_part", Item::new)
                    .lang("Hand Gold Cast with Part")
                    .register();
    public static final ItemEntry<HandCastItem> HAND_CAST_WITH_BRASS_HAND =
            REGISTRATE.item("hand_cast_with_brass_hand", (properties) -> new HandCastItem(AllItems.BRASS_HAND, properties))
                    .lang("Hand Gold Cast with Brass Hand")
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
    public static final ItemEntry<HandCastItem> HAND_CAST_WITH_CHOCOLATE_HAND =
            REGISTRATE.item("hand_cast_with_chocolate_hand", (properties) -> new HandCastItem(CHOCOLATE_HAND, properties))
                    .lang("Hand Gold Cast with Chocolate Hand")
                    .register();
    public static final ItemEntry<HandCastItem> HAND_CAST_WITH_BLAZING_CHOCOLATE_HAND =
            REGISTRATE.item("hand_cast_with_blazing_chocolate_hand", (properties) -> new HandCastItem(BLAZING_CHOCOLATE_HAND, properties))
                    .lang("Hand Gold Cast with Blazing Chocolate Hand")
                    .register();
    public static final ItemEntry<Item> PROPELLER_CAST =
            REGISTRATE.item("propeller_cast", Item::new)
                    .lang("Propeller Gold Cast")
                    .register();
    public static final ItemEntry<Item> PROPELLER_CAST_WITH_PART =
            REGISTRATE.item("propeller_cast_with_part", Item::new)
                    .lang("Propeller Gold Cast with Part")
                    .register();
    public static final ItemEntry<PropellerCastItem> PROPELLER_CAST_WITH_PROPELLER =
            REGISTRATE.item("propeller_cast_with_propeller", (properties) -> new PropellerCastItem(AllItems.PROPELLER, properties))
                    .lang("Propeller Gold Cast with Propeller")
                    .register();
    public static final ItemEntry<Item> COBALT_PROPELLER =
            REGISTRATE.item("cobalt_propeller", Item::new)
                    .register();
    public static final ItemEntry<PropellerCastItem> PROPELLER_CAST_WITH_COBALT_PROPELLER =
            REGISTRATE.item("propeller_cast_with_cobalt_propeller", (properties) -> new PropellerCastItem(COBALT_PROPELLER, properties))
                    .lang("Propeller Gold Cast with Cobalt Propeller")
                    .register();
    public static final ItemEntry<Item> WHISK_CAST =
            REGISTRATE.item("whisk_cast", Item::new)
                    .lang("Whisk Gold Cast")
                    .register();
    public static final ItemEntry<Item> WHISK_CAST_WITH_PART =
            REGISTRATE.item("whisk_cast_with_part", Item::new)
                    .lang("Whisk Gold Cast with Part")
                    .register();
    public static final ItemEntry<WhiskCastItem> WHISK_CAST_WITH_WHISK =
            REGISTRATE.item("whisk_cast_with_whisk", (properties) -> new WhiskCastItem(AllItems.WHISK, properties))
                    .lang("Whisk Gold Cast with Whisk")
                    .register();
    public static final ItemEntry<SequencedAssemblyItem> WHISK_CAST_TO_STURDY_WHISK =
            REGISTRATE.item("whisk_cast_to_sturdy_whisk", SequencedAssemblyItem::new)
                    .lang("Whisk Gold Cast with Incomplete Sturdy Whisk")
                    .register();
    public static final ItemEntry<SturdyWhiskCastItem> WHISK_CAST_WITH_STURDY_WHISK =
            REGISTRATE.item("whisk_cast_with_sturdy_whisk", SturdyWhiskCastItem::new)
                    .lang("Whisk Gold Cast with Sturdy Whisk")
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

    public static final ItemEntry<TinkerWrenchItem> WRENCH = REGISTRATE.item("wrench", TinkerWrenchItem::new)
            .properties(p -> p.stacksTo(1))
            .model(AssetLookup.itemModelWithPartials())
            .tag(AllTags.AllItemTags.WRENCH.tag)
            .lang("Tinker Wrench")
            .register();

    /*
    public static final ItemEntry<Item> LUZZIUM_INGOT =
            REGISTRATE.item("luzzium_ingot", Item::new)
                    .tag(TGTagKeys.Items.INGOTS)
                    .tag(TGTagKeys.Items.LUZZIUM_INGOT)
                    .register();

    public static final ItemEntry<Item> LUZZIUM_NUGGET =
            REGISTRATE.item("luzzium_nugget", Item::new)
                    .tag(TGTagKeys.Items.NUGGETS)
                    .tag(TGTagKeys.Items.LUZZIUM_NUGGET)
                    .register();
     */

    public static void register() {
        GogglesItem.addIsWearingPredicate(TinkerGogglesUtil::isWearingGoggles);

        TinkersGears.LOGGER.info("Items initialized");
    }
}
