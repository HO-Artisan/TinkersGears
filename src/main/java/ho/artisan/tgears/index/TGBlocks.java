package ho.artisan.tgears.index;

import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.common.block.TinkerSpoutBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.Tags;

import static com.simibubi.create.foundation.data.BlockStateGen.simpleCubeAll;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static com.simibubi.create.foundation.data.TagGen.tagBlockAndItem;
import static ho.artisan.tgears.TinkersGears.REGISTRATE;

public final class TGBlocks {

    private TGBlocks() {}

    static {
        REGISTRATE.setCreativeTab(TGCreativeModeTabs.MAIN_TAB);
    }

    public static final BlockEntry<TinkerSpoutBlock> SPOUT = REGISTRATE.block("tinker_spout", TinkerSpoutBlock::new)
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .blockstate((ctx, prov) -> BlockStateGen.simpleBlock(ctx, prov, AssetLookup.forPowered(ctx, prov)))
            .item(AssemblyOperatorBlockItem::new)
            .transform(customItemModel())
            .register();

    public static final BlockEntry<CasingBlock> COBALT_CASING = REGISTRATE.block("cobalt_casing", CasingBlock::new)
            .initialProperties(SharedProperties::stone)
            .transform(pickaxeOnly())
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK))
            .transform(BuilderTransformers.casing(() -> TGSpriteShifts.COBALT_CASING))
            .register();

    public static final BlockEntry<Block> LUZZIUM_BLOCK = REGISTRATE.block("luzzium_block", Block::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p.mapColor(MapColor.COLOR_CYAN).requiresCorrectToolForDrops())
            .transform(pickaxeOnly())
            .blockstate(simpleCubeAll("luzzium_block"))
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .tag(Tags.Blocks.STORAGE_BLOCKS)
            .tag(BlockTags.BEACON_BASE_BLOCKS)
            .transform(tagBlockAndItem("storage_blocks/luzzium"))
            .tag(Tags.Items.STORAGE_BLOCKS)
            .build()
            .lang("Block of Luzzium")
            .register();

    public static void register() {
        TinkersGears.LOGGER.info("Blocks initialized");
    }
}
