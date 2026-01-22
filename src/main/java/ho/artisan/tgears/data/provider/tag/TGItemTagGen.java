package ho.artisan.tgears.data.provider.tag;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import ho.artisan.tgears.compat.TinkersGearsCompat;
import ho.artisan.tgears.index.TGBlocks;
import ho.artisan.tgears.index.TGTagKeys;
import ho.artisan.tgears.index.TGTinkerItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.gadgets.TinkerGadgets;

import java.util.function.Consumer;

public final class TGItemTagGen extends TGTagGen<Item> {
    public TGItemTagGen(RegistrateTagsProvider.IntrinsicImpl<Item> provIn) {
        super(provIn, item -> item.builtInRegistryHolder().key());

        prov.tag(AllTags.AllItemTags.BLAZE_BURNER_FUEL_SPECIAL.tag)
                .add(
                        TinkerGadgets.magmaCake.asItem()
                );

        /*
        prov.tag(TinkerTags.Items.GOLD_CASTS)
                .add(
                        TGItems.HAND_CAST.get(),
                        TGItems.PROPELLER_CAST.get(),
                        TGItems.WHISK_CAST.get()
                );

         */
        TagGen.CreateTagAppender<Item> goldCasts = prov.tag(TinkerTags.Items.GOLD_CASTS);
        TagGen.CreateTagAppender<Item> sandCasts = prov.tag(TinkerTags.Items.SAND_CASTS);
        TagGen.CreateTagAppender<Item> redSandCasts = prov.tag(TinkerTags.Items.RED_SAND_CASTS);
        TagGen.CreateTagAppender<Item> singleUseCasts = prov.tag(TinkerTags.Items.SINGLE_USE_CASTS);
        TagGen.CreateTagAppender<Item> multiUseCasts = prov.tag(TinkerTags.Items.MULTI_USE_CASTS);
        Consumer<CastItemObject> addCast = cast -> {
            // tag based on material
            goldCasts.add(cast.get());
            sandCasts.add(cast.getSand());
            redSandCasts.add(cast.getRedSand());
            // tag based on usage
            singleUseCasts.addTag(cast.getSingleUseTag());
            prov.tag(cast.getSingleUseTag()).add(cast.getSand(), cast.getRedSand());
            multiUseCasts.addTag(cast.getMultiUseTag());
            prov.tag(cast.getMultiUseTag()).add(cast.get());
        };

        addCast.accept(TGTinkerItems.HAND_CAST);
        addCast.accept(TGTinkerItems.PROPELLER_CAST);
        addCast.accept(TGTinkerItems.WHISK_CAST);

        prov.tag(TinkerTags.Items.TOOL_PARTS)
                .add(TGTinkerItems.HAND_PART.get())
                .add(TGTinkerItems.PROPELLER_PART.get())
                .add(TGTinkerItems.WHISK_PART.get());

        prov.tag(TGTagKeys.Items.GRIT_SANDPAPER)
                .addOptional(ResourceLocation.tryBuild(TinkersGearsCompat.CA, "diamond_grit_sandpaper"));

        prov.tag(TGTagKeys.Items.CASTING_BLACKLIST)
                .add(
                        AllBlocks.MECHANICAL_ARM.asItem(),
                        TGBlocks.TINKER_SPOUT.asItem()
                );
    }
}
