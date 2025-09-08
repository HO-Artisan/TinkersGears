package ho.artisan.tgears.index;

import ho.artisan.tgears.TinkersGears;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;

public final class TGTagKeys {

    private TGTagKeys() {}

    public static final class Items {

        private Items() {}

        public static final TagKey<Item> GRIT_SANDPAPER = tgearsTag("grit_sandpaper");
        public static final TagKey<Item> CRUSHING_BLACKLIST = tgearsTag("crushing_blacklist");

        private static TagKey<Item> tgearsTag(final String name) {
            return ItemTags.create(TinkersGears.asResource(name));
        }
    }

    public static final class Blocks {

        private Blocks() {}


    }

    public static final class Fluids {

        private Fluids() {}

        public static final TagKey<Fluid> BLAZING_CHOCOLATE = forgeTag("blazing_chocolate");

        private static TagKey<Fluid> forgeTag(final String name) {
            return TagKey.create(Registries.FLUID, new ResourceLocation("forge", name));
        }
    }
}
