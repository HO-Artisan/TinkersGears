package ho.artisan.tgears.datagen.provider.tag;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import static net.minecraft.tags.BlockTags.BUTTONS;

public final class TGBlockTagGen extends TGTagGen<Block> {
    public TGBlockTagGen(RegistrateTagsProvider.IntrinsicImpl<Block> provIn) {
        super(provIn, block -> block.builtInRegistryHolder().key());

        prov.tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
                .add(
                        TinkerSmeltery.searedBasin.get(),
                        TinkerSmeltery.searedTable.get(),
                        TinkerSmeltery.scorchedBasin.get(),
                        TinkerSmeltery.scorchedTable.get(),
                        TinkerCommons.soulGlass.get(),
                        TinkerCommons.soulGlassPane.get()
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
}
