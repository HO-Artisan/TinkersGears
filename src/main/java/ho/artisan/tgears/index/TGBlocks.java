package ho.artisan.tgears.index;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.common.block.*;
import ho.artisan.tgears.common.block.behaviour.TinkerDrillMovementBehaviour;
import ho.artisan.tgears.common.block.behaviour.TinkerFortuneDrillMovementBehaviour;
import ho.artisan.tgears.common.block.behaviour.TinkerSilkDrillMovementBehaviour;
import ho.artisan.tgears.common.item.TinkerAssemblyOperatorBlockItem;
import ho.artisan.tgears.data.util.TGBlockStateGen;
import ho.artisan.tgears.old.block.*;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static com.simibubi.create.api.behaviour.movement.MovementBehaviour.movementBehaviour;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static ho.artisan.tgears.TinkersGears.REGISTRATE;

public final class TGBlocks {

    static {
        REGISTRATE.setCreativeTab(TGCreativeModeTabs.MAIN_TAB);
    }

    private TGBlocks() {
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
            .transform(BuilderTransformers.casing(() -> TGSpriteShifts.COBALT_CASING))
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK).sound(SoundType.NETHERITE_BLOCK))
            .register();

    public static final BlockEntry<CasingBlock> TINKER_CASING = REGISTRATE.block("tinker_casing", CasingBlock::new)
            .initialProperties(SharedProperties::stone)
            .transform(pickaxeOnly())
            .transform(BuilderTransformers.casing(() -> TGSpriteShifts.TINKER_CASING))
            .properties(p -> p.mapColor(MapColor.COLOR_BLACK).sound(SoundType.NETHERITE_BLOCK))
            .register();

    public static final BlockEntry<TinkerDrillBlock> TINKER_DRILL = REGISTRATE.block("tinker_drill", TinkerDrillBlock::new)
            .initialProperties(SharedProperties::stone)
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
            .onRegister(movementBehaviour(new TinkerSilkDrillMovementBehaviour(TGPartialModels.SILKTOUCH_DRILL_HEAD)))
            .item()
            .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
            .tag(TGTagKeys.Items.DRILL)
            .properties(p -> p.rarity(Rarity.RARE))
            .transform(customItemModel())
            .register();

    public static final BlockEntry<TinkerFortuneDrillBlock> TINKER_FORTUNE_DRILL = REGISTRATE.block("tinker_fortune_drill", TinkerFortuneDrillBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform(axeOrPickaxe())
            .lang("Tinker Mechanical Drill (Fortune)")
            .blockstate(BlockStateGen.directionalBlockProvider(true))
            .tag(TGTagKeys.Blocks.DRILL)
            .onRegister(movementBehaviour(new TinkerFortuneDrillMovementBehaviour(TGPartialModels.FORTUNE_DRILL_HEAD)))
            .item()
            .tag(AllTags.AllItemTags.CONTRAPTION_CONTROLLED.tag)
            .tag(TGTagKeys.Items.DRILL)
            .properties(p -> p.rarity(Rarity.RARE))
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

    public static final BlockEntry<TinkerCrushingWheelBlock> TINKER_CRUSHING_WHEEL =
            REGISTRATE.block("tinker_crushing_wheel", TinkerCrushingWheelBlock::new)
                    .properties(p -> p.mapColor(MapColor.METAL))
                    .initialProperties(SharedProperties::stone)
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .transform(pickaxeOnly())
                    .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, s -> AssetLookup.partialBaseModel(c, p)))
                    .item()
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<TinkerSilkyCrushingWheelBlock> TINKER_SILKY_CRUSHING_WHEEL =
            REGISTRATE.block("tinker_silktouch_crushing_wheel", TinkerSilkyCrushingWheelBlock::new)
                    .properties(p -> p.mapColor(MapColor.METAL))
                    .initialProperties(SharedProperties::stone)
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .transform(pickaxeOnly())
                    .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, s -> AssetLookup.partialBaseModel(c, p)))
                    .lang("Tinker Crushing Wheel (Silk Touch)")
                    .item()
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<TinkerFortuneCrushingWheelBlock> TINKER_FORTUNE_CRUSHING_WHEEL =
            REGISTRATE.block("tinker_fortune_crushing_wheel", TinkerFortuneCrushingWheelBlock::new)
                    .properties(p -> p.mapColor(MapColor.METAL))
                    .initialProperties(SharedProperties::stone)
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .transform(pickaxeOnly())
                    .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, s -> AssetLookup.partialBaseModel(c, p)))
                    .lang("Tinker Crushing Wheel (Fortune)")
                    .item()
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<TinkerCrushingWheelControllerBlock> TINKER_CRUSHING_WHEEL_CONTROLLER =
            REGISTRATE.block("tinker_crushing_wheel_controller", TinkerCrushingWheelControllerBlock::new)
                    .properties(p -> p.mapColor(MapColor.STONE)
                            .noOcclusion()
                            .noLootTable()
                            .air()
                            .noCollission()
                            .pushReaction(PushReaction.BLOCK))
                    .blockstate((c, p) -> p.getVariantBuilder(c.get())
                            .forAllStatesExcept(BlockStateGen.mapToAir(p), AbstractTinkerCrushingWheelControllerBlock.FACING))
                    .register();

    public static final BlockEntry<TinkerSilkyCrushingWheelControllerBlock> TINKER_SILKY_CRUSHING_WHEEL_CONTROLLER =
            REGISTRATE.block("tinker_silky_crushing_wheel_controller", TinkerSilkyCrushingWheelControllerBlock::new)
                    .properties(p -> p.mapColor(MapColor.STONE)
                            .noOcclusion()
                            .noLootTable()
                            .air()
                            .noCollission()
                            .pushReaction(PushReaction.BLOCK))
                    .blockstate((c, p) -> p.getVariantBuilder(c.get())
                            .forAllStatesExcept(BlockStateGen.mapToAir(p), AbstractTinkerCrushingWheelControllerBlock.FACING))
                    .register();

    public static final BlockEntry<TinkerFortuneCrushingWheelControllerBlock> TINKER_FORTUNE_CRUSHING_WHEEL_CONTROLLER =
            REGISTRATE.block("tinker_fortune_crushing_wheel_controller", TinkerFortuneCrushingWheelControllerBlock::new)
                    .properties(p -> p.mapColor(MapColor.STONE)
                            .noOcclusion()
                            .noLootTable()
                            .air()
                            .noCollission()
                            .pushReaction(PushReaction.BLOCK))
                    .blockstate((c, p) -> p.getVariantBuilder(c.get())
                            .forAllStatesExcept(BlockStateGen.mapToAir(p), AbstractTinkerCrushingWheelControllerBlock.FACING))
                    .register();

    public static final BlockEntry<TinkerDismantlerBlock> TINKER_DISMANTLER = REGISTRATE.block("tinker_dismantler", TinkerDismantlerBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .blockstate((ctx, prov) -> TGBlockStateGen.horizontalDismantler(ctx, prov, TGBlockStateGen.dismantlerModelFunc(ctx, prov)))
            .transform(pickaxeOnly())
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
