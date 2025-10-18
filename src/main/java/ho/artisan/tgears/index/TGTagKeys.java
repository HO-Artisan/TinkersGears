package ho.artisan.tgears.index;

import ho.artisan.tgears.TinkersGears;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public final class TGTagKeys {

    private TGTagKeys() {}

    public static final class Items {

        private Items() {}

        public static final TagKey<Item> GRIT_SANDPAPER = tgearsTag("grit_sandpaper");
        public static final TagKey<Item> CRUSHING_BLACKLIST = tgearsTag("crushing_blacklist");

        public static final TagKey<Item> INGOTS = forgeTag("ingots");
        public static final TagKey<Item> LUZZIUM_INGOT = forgeTag("ingots/luzzium");

        public static final TagKey<Item> NUGGETS = forgeTag("nuggets");
        public static final TagKey<Item> LUZZIUM_NUGGET = forgeTag("nuggets/luzzium");

        public static final TagKey<Item> DRILL = tgearsTag("drill");

        public static final TagKey<Item> PLATES = forgeTag("plates");
        public static final TagKey<Item> COBALT_SHEET = forgeTag("plates/cobalt");

        private static TagKey<Item> createTag(final String name) {
            return ItemTags.create(new ResourceLocation("create", name));
        }

        private static TagKey<Item> tgearsTag(final String name) {
            return ItemTags.create(TinkersGears.asResource(name));
        }

        private static TagKey<Item> forgeTag(final String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }

    public static final class Blocks {

        private Blocks() {}

        public static final TagKey<Block> SPOUT_ATTACHMENTS = tgearsTag("spout_attachments");

        public static final TagKey<Block> DRILL = tgearsTag("drill");

        public static final TagKey<Block> TINKER_ASSEMBLY_OPERATOR = tgearsTag("tinker_assembly_operator");

        private static TagKey<Block> createTag(final String name) {
            return BlockTags.create(new ResourceLocation("create", name));
        }

        private static TagKey<Block> tgearsTag(final String name) {
            return BlockTags.create(TinkersGears.asResource(name));
        }

        private static TagKey<Block> tconstructTag(final String name) {
            return BlockTags.create(new ResourceLocation("tconstruct", name));
        }
    }

    public static final class Fluids {

        private Fluids() {}

        public static final TagKey<Fluid> BLAZING_CHOCOLATE = forgeTag("blazing_chocolate");
        public static final TagKey<Fluid> CHOCOLATE = forgeTag("chocolate");
        public static final TagKey<Fluid> HONEY = forgeTag("honey");

        public static final TagKey<Fluid> MOLTEN_BRASS = forgeTag("molten_brass");
        public static final TagKey<Fluid> MOLTEN_LUZZIUM = forgeTag("molten_luzzium");

        private static TagKey<Fluid> forgeTag(final String name) {
            return FluidTags.create(new ResourceLocation("forge", name));
        }
    }
}
