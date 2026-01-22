package ho.artisan.tgears.old.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TinkerSilkDrillBlockEntity extends TinkerDrillBlockEntity {
    public TinkerSilkDrillBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public ItemStack createTool() {
        ItemStack tool = super.createTool();
        tool.enchant(Enchantments.SILK_TOUCH, 1);
        return tool;
    }
}
