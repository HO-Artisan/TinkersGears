package ho.artisan.tgears.data.util;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import ho.artisan.tgears.old.block.TinkerDismantlerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;

import java.util.function.Function;

import static com.simibubi.create.foundation.data.AssetLookup.partialBaseModel;

public final class TGBlockStateGen {
    public static <T extends Block> void horizontalDismantler(DataGenContext<Block, T> ctx,
                                                              RegistrateBlockstateProvider prov, Function<BlockState, ModelFile> modelFunc) {
        prov.getVariantBuilder(ctx.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(modelFunc.apply(state))
                        .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING)
                                .toYRot() + 180) % 360)
                        .build());
    }

    public static <T extends Block> Function<BlockState, ModelFile> dismantlerModelFunc(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov) {
        return state -> state.getValue(TinkerDismantlerBlock.CLAMPED) ? partialBaseModel(ctx, prov, "clamped")
                : partialBaseModel(ctx, prov);
    }
}
