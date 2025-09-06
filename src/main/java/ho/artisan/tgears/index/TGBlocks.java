package ho.artisan.tgears.index;

import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import ho.artisan.tgears.common.block.TinkerSpoutBlock;
import net.minecraft.client.renderer.RenderType;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
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
            .addLayer(() -> RenderType::cutoutMipped)
            .item(AssemblyOperatorBlockItem::new)
            .transform(customItemModel())
            .register();

    public static void register() {

    }
}
