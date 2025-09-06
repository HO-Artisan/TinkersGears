package ho.artisan.tgears.index;

import ho.artisan.tgears.TinkersGears;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class TGItemTags {

    private TGItemTags() {}

    public static final TagKey<Item> GRIT_SANDPAPER = ItemTags.create(TinkersGears.asResource("grit_sandpaper"));
    public static final TagKey<Item> CRUSHING_BLACKLIST = ItemTags.create(TinkersGears.asResource("crushing_blacklist"));
}
