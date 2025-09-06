package ho.artisan.tgears.index;

import ho.artisan.tgears.TinkersGears;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class TGItemTags {

    private TGItemTags() {}

    public static final TagKey<Item> GRIT_SANDPAPER = tgearsTag("grit_sandpaper");
    public static final TagKey<Item> CRUSHING_BLACKLIST = tgearsTag("crushing_blacklist");

    public static final TagKey<Item> CRUSHED_RAW_MATERIALS = createTag("crushed_raw_materials");

    public static final TagKey<Item> BLAZING_FOOD = createTag("blaze_burner_fuel/special");

    private static TagKey<Item> forgeTag(final String name) {
        return ItemTags.create(new ResourceLocation("forge", name));
    }

    private static TagKey<Item> createTag(final String name) {
        return ItemTags.create(new ResourceLocation("create", name));
    }

    private static TagKey<Item> tgearsTag(final String name) {
        return ItemTags.create(TinkersGears.asResource(name));
    }

    public static TagKey<Item> ticTag(final String name) {
        return ItemTags.create(new ResourceLocation("tconstruct", name));
    }
}
