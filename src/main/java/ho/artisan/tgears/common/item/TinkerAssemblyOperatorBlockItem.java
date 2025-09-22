package ho.artisan.tgears.common.item;

import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class TinkerAssemblyOperatorBlockItem extends AssemblyOperatorBlockItem {
    public TinkerAssemblyOperatorBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    protected boolean operatesOn(LevelReader world, BlockPos pos, BlockState placedOnState) {
        return super.operatesOn(world, pos, placedOnState) || placedOnState.is(TGTagKeys.Blocks.TINKER_ASSEMBLY_OPERATOR);
    }
}
