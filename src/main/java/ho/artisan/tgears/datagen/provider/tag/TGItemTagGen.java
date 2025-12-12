package ho.artisan.tgears.datagen.provider.tag;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import ho.artisan.tgears.index.TGBlocks;
import ho.artisan.tgears.index.TGItems;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.gadgets.TinkerGadgets;

public final class TGItemTagGen extends TGTagGen<Item> {
    public TGItemTagGen(RegistrateTagsProvider.IntrinsicImpl<Item> provIn) {
        super(provIn, Item::builtInRegistryHolder);

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

        prov.tag(TGTagKeys.Items.CASTING_BLACKLIST)
                .add(
                        AllBlocks.MECHANICAL_ARM.asItem(),
                        TGBlocks.TINKER_SPOUT.asItem()
                );
    }
}
