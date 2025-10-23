package ho.artisan.tgears.common.block.entity;

import ho.artisan.tgears.TinkersGearsConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TinkerFortuneDrillBlockEntity extends TinkerDrillBlockEntity {
    public TinkerFortuneDrillBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public ItemStack createTool() {
        ItemStack tool = super.createTool();
        tool.enchant(Enchantments.BLOCK_FORTUNE, TinkersGearsConfig.FORTUNE_DRILL_LEVEL.get());
        return tool;
    }
}
