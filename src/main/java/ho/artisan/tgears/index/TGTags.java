package ho.artisan.tgears.index;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateItemTagsProvider;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.gadgets.TinkerGadgets;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import static ho.artisan.tgears.TinkersGears.REGISTRATE;
import static net.minecraft.tags.BlockTags.BUTTONS;

public final class TGTags {

    private TGTags() {}

    public static void addGenerators() {
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, TGTags::genBlockTags);
        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, TGTags::genItemTags);
        REGISTRATE.addDataGenerator(ProviderType.FLUID_TAGS, TGTags::genFluidTags);
    }

    private static void genBlockTags(RegistrateTagsProvider.IntrinsicImpl<Block> provIn) {
        var prov = new TagGen.CreateTagsProvider<>(provIn, Block::builtInRegistryHolder);

        prov.tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
                .add(
                        TinkerSmeltery.searedBasin.get(),
                        TinkerSmeltery.searedTable.get(),
                        TinkerSmeltery.scorchedBasin.get(),
                        TinkerSmeltery.scorchedTable.get()
                );

        prov.tag(TGTagKeys.Blocks.TINKER_ASSEMBLY_OPERATOR)
                .add(
                        TinkerSmeltery.searedBasin.get(),
                        TinkerSmeltery.searedTable.get(),
                        TinkerSmeltery.scorchedBasin.get(),
                        TinkerSmeltery.scorchedTable.get()
                );

        prov.tag(TinkerTags.Blocks.FUEL_TANKS)
                .add(AllBlocks.BLAZE_BURNER.get());

        prov.tag(TGTagKeys.Blocks.SPOUT_ATTACHMENTS)
                .add(Blocks.LEVER)
                .addTag(BUTTONS);
    }

    private static void genItemTags(RegistrateItemTagsProvider provIn) {
        var prov = new TagGen.CreateTagsProvider<>(provIn, Item::builtInRegistryHolder);

        prov.tag(AllTags.AllItemTags.BLAZE_BURNER_FUEL_SPECIAL.tag)
                .add(
                        TinkerGadgets.magmaCake.asItem()
                );

        prov.tag(TinkerTags.Items.GOLD_CASTS)
                .add(
                        TGItems.HAND_CAST.get(),
                        TGItems.PROPELLER_CAST.get(),
                        TGItems.WHISK_CAST.get()
                );

        prov.tag(TGTagKeys.Items.GRIT_SANDPAPER)
                .addOptional(new ResourceLocation("createaddition", "diamond_grit_sandpaper"));
    }

    private static void genFluidTags(RegistrateTagsProvider.IntrinsicImpl<Fluid> provIn) {
        var prov = new TagGen.CreateTagsProvider<>(provIn, Fluid::builtInRegistryHolder);

        prov.tag(TGTagKeys.Fluids.BLAZING_CHOCOLATE)
                .add(
                        TGFluids.BLAZING_CHOCOLATE.getSource(),
                        TGFluids.BLAZING_CHOCOLATE.get()
                );

        /*
        prov.tag(TGTagKeys.Fluids.MOLTEN_LUZZIUM)
                .add(
                        TGFluids.MOLTEN_LUZZIUM.getSource(),
                        TGFluids.MOLTEN_LUZZIUM.get()
                );

         */
    }
}
