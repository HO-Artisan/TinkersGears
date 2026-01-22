package ho.artisan.tgears.common.block.behaviour;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;

public class TinkerFortuneDrillMovementBehaviour extends TinkerDrillMovementBehaviour {
    public TinkerFortuneDrillMovementBehaviour(PartialModel model) {
        super(model);
    }

    @Override
    public ItemStack createTool() {
        ItemStack tool = super.createTool();
        tool.enchant(Enchantments.BLOCK_FORTUNE, 3);
        return tool;
    }
}
