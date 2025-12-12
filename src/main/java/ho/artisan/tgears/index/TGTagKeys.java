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

    private TGTagKeys() {
    }

    public static final class Items {
        private Items() {
        }

        public static final TagKey<Item> GRIT_SANDPAPER = tgearsTag("grit_sandpaper");
        public static final TagKey<Item> CRUSHING_BLACKLIST = tgearsTag("crushing_blacklist");
        public static final TagKey<Item> CASTING_BLACKLIST = tgearsTag("casting_blacklist");

        public static final TagKey<Item> INGOTS = forgeTag("ingots");
        public static final TagKey<Item> COBALT_INGOT = forgeTag("ingots/cobalt");
        public static final TagKey<Item> ROSE_GOLD_INGOT = forgeTag("ingots/rose_gold");
        public static final TagKey<Item> BRASS_INGOT = forgeTag("ingots/brass");

        public static final TagKey<Item> NUGGETS = forgeTag("nuggets");

        public static final TagKey<Item> PLATES = forgeTag("plates");
        public static final TagKey<Item> COBALT_PLATE = forgeTag("plates/cobalt");
        public static final TagKey<Item> ROSE_GOLD_PLATE = forgeTag("plates/rose_gold");

        public static final TagKey<Item> DRILL = tgearsTag("drill");

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

        public static final TagKey<Block> SPOUT_ATTACHMENTS = tgearsTag("spout_attachments");
        public static final TagKey<Block> DRILL = tgearsTag("drill");
        public static final TagKey<Block> TINKER_ASSEMBLY_OPERATOR = tgearsTag("tinker_assembly_operator");

        private Blocks() {
        }

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

        public static final TagKey<Fluid> BLAZING_CHOCOLATE = forgeTag("blazing_chocolate");
        public static final TagKey<Fluid> CHOCOLATE = forgeTag("chocolate");
        public static final TagKey<Fluid> HONEY = forgeTag("honey");
        public static final TagKey<Fluid> MOLTEN_BRASS = forgeTag("molten_brass");
        public static final TagKey<Fluid> MOLTEN_LUZZIUM = forgeTag("molten_luzzium");

        private Fluids() {
        }

        private static TagKey<Fluid> forgeTag(final String name) {
            return FluidTags.create(new ResourceLocation("forge", name));
        }
    }
}
