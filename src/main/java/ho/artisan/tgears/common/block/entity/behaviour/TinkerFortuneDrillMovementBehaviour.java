package ho.artisan.tgears.common.block.entity.behaviour;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import ho.artisan.tgears.TinkersGearsConfig;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;

public class TinkerFortuneDrillMovementBehaviour extends TinkerDrillMovementBehaviour {
    public TinkerFortuneDrillMovementBehaviour(PartialModel model) {
        super(model);
    }

    @Override
    public ItemStack createTool() {
        ItemStack tool = super.createTool();
        tool.enchant(Enchantments.BLOCK_FORTUNE, TinkersGearsConfig.FORTUNE_DRILL_LEVEL.get());
        return tool;
    }
}
