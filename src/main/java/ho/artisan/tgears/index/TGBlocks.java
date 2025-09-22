package ho.artisan.tgears.index;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.common.block.TinkerDrillBlock;
import ho.artisan.tgears.common.block.TinkerFanBlock;
import ho.artisan.tgears.common.block.TinkerSilkDrillBlock;
import ho.artisan.tgears.common.block.TinkerSpoutBlock;
import ho.artisan.tgears.common.block.entity.behaviour.TinkerDrillMovementBehaviour;
import ho.artisan.tgears.common.item.TinkerAssemblyOperatorBlockItem;
import net.minecraft.world.level.material.MapColor;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import static com.simibubi.create.api.behaviour.movement.MovementBehaviour.movementBehaviour;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static ho.artisan.tgears.TinkersGears.REGISTRATE;

public final class TGBlocks {

    private TGBlocks() {}

    static {
        REGISTRATE.setCreativeTab(TGCreativeModeTabs.MAIN_TAB);
    }

    public static final BlockEntry<TinkerSpoutBlock> TINKER_SPOUT = REGISTRATE.block("tinker_spout", TinkerSpoutBlock::new)
            .initialProperties(SharedProperties::copperMetal)
            .transform(pickaxeOnly())
            .blockstate((ctx, prov) -> BlockStateGen.simpleBlock(ctx, prov, AssetLookup.forPowered(ctx, prov)))
            .item(TinkerAssemblyOperatorBlockItem::new)
            .transform(customItemModel())
            .register();

    public static final BlockEntry<CasingBlock> COBALT_CASING = REGISTRATE.block("cobalt_casing", CasingBlock::new)
            .initialProperties(SharedProperties::stone)
            .transform(pickaxeOnly())
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK))
            .transform(BuilderTransformers.casing(() -> TGSpriteShifts.COBALT_CASING))
            .register();

    public static final BlockEntry<TinkerDrillBlock> TINKER_DRILL = REGISTRATE.block("tinker_drill", TinkerDrillBlock::new)
            .initialProperties(TinkerSmeltery.searedStone::get)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform(axeOrPickaxe())
            .lang("Tinker Mechanical Drill")
            .blockstate(BlockStateGen.directionalBlockProvider(true))
            .tag(TGTagKeys.Blocks.DRILL)
            .onRegister(movementBehaviour(new TinkerDrillMovementBehaviour(TGPartialModels.DRILL_HEAD)))
            .item()
            .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
            .tag(TGTagKeys.Items.DRILL)
            .transform(customItemModel())
            .register();

    public static final BlockEntry<TinkerSilkDrillBlock> TINKER_SILKTOUCH_DRILL = REGISTRATE.block("tinker_silktouch_drill", TinkerSilkDrillBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform(axeOrPickaxe())
            .lang("Tinker Mechanical Drill (Silk Touch)")
            .blockstate(BlockStateGen.directionalBlockProvider(true))
            .tag(TGTagKeys.Blocks.DRILL)
            .onRegister(movementBehaviour(new TinkerDrillMovementBehaviour(TGPartialModels.SILKTOUCH_DRILL_HEAD)))
            .item()
            .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
            .tag(TGTagKeys.Items.DRILL)
            .transform(customItemModel())
            .register();

    public static final BlockEntry<TinkerFanBlock> TINKER_FAN = REGISTRATE.block("tinker_fan", TinkerFanBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .blockstate(BlockStateGen.directionalBlockProvider(true))
            .transform(axeOrPickaxe())
            .lang("Tinker Encased Fan")
            .item()
            .transform(customItemModel())
            .register();

    /*
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
     */

    public static void register() {
        TinkersGears.LOGGER.info("Blocks initialized");
    }
}
