package ho.artisan.tgears.old.item;

import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import ho.artisan.tgears.common.item.cast.CastItem;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static ho.artisan.tgears.TinkersGears.REGISTRATE;
import static ho.artisan.tgears.TinkersGears.asResource;

@Deprecated
public final class TGCastItems {
    public static final Map<String, ItemEntry<?>> ITEM_ENTRY_MAP = new HashMap<>();

    private TGCastItems() {
    }

    static {
        cast("hand");
        partCast("hand", "andesite");
        goldCast("hand", AllItems.BRASS_HAND, "hand/brass");
        goldCast("hand", TGItems.CHOCOLATE_HAND, "hand/chocolate");
        goldCast("hand", TGItems.BLAZING_CHOCOLATE_HAND, "hand/blazing_chocolate");

        cast("propeller");
        partCast("propeller", "andesite");
        goldCast("propeller", AllItems.PROPELLER, "propeller/iron");

        cast("whisk");
        partCast("whisk", "andesite");
        goldCast("whisk", AllItems.WHISK, "whisk/iron");
    }

    public static <T extends Item> NonNullBiConsumer<DataGenContext<Item, T>, RegistrateItemModelProvider> model(ResourceLocation... path) {
        return (ctx, prov) -> prov.generated(ctx::get, path);
    }

    private static <T extends Item> void register(String id, NonNullFunction<Item.Properties, T> item, UnaryOperator<ItemBuilder<T, CreateRegistrate>> entry) {
        ITEM_ENTRY_MAP.put(id, entry.apply(REGISTRATE.item(id, item)).register());
    }

    private static void cast(String id) {
        String name = id.substring(0, 1).toUpperCase() + id.substring(1).toLowerCase();

        register(
                id + "_gold_cast",
                Item::new,
                b -> b.lang(name + " Gold Cast").model(model(asResource("item/cast/" + id + "_gold_cast")))
        );

        register(
                id + "_sand_cast",
                Item::new,
                b -> b.lang(name + " Sand Cast").model(model(asResource("item/cast/" + id + "_sand_cast")))
        );

        register(
                id + "_red_sand_cast",
                Item::new,
                b -> b.lang(name + " Red Sand Cast").model(model(asResource("item/cast/" + id + "_red_sand_cast")))
        );
    }

    private static void partCast(String id, String part) {
        String name = id.substring(0, 1).toUpperCase() + id.substring(1).toLowerCase();

        register(
                id + "_gold_cast_with_part",
                Item::new,
                b -> b.lang(name + " Gold Cast with Part").model(model(
                        asResource("item/cast/" + id + "_gold_cast"),
                        asResource("item/cast/" + id + "_" + part + "_part")
                ))
        );

        register(
                id + "_sand_cast_with_part",
                Item::new,
                b -> b.lang(name + " Sand Cast with Part").model(model(
                        asResource("item/cast/" + id + "_sand_cast"),
                        asResource("item/cast/" + id + "_" + part + "_part")
                ))
        );

        register(
                id + "_red_sand_cast_with_part",
                Item::new,
                b -> b.lang(name + " Red Sand Cast with Part").model(model(
                        asResource("item/cast/" + id + "_red_sand_cast"),
                        asResource("item/cast/" + id + "_" + part + "_part")
                ))
        );
    }

    private static void goldCast(String id, ItemEntry<?> result, String resultTexture) {
        String name = id.substring(0, 1).toUpperCase() + id.substring(1).toLowerCase();
        String result_id = result.getId().getPath();
        String result_name = Arrays.stream(result_id.trim().split("_"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));

        ItemEntry<?> cast = ITEM_ENTRY_MAP.get(id + "_gold_cast");


        REGISTRATE.item(id + "_gold_cast_with_" + result_id, (properties) -> new CastItem(cast, result, properties))
                .lang(name + " Gold Cast with " + result_name)
                .model(model(asResource("item/cast/" + id + "_gold_cast"), asResource("item/" + resultTexture)))
                .register();
    }

    public static void register() {

    }
}
