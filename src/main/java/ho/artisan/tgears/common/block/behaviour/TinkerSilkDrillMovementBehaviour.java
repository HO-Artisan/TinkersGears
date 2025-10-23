package ho.artisan.tgears.common.block.behaviour;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;

public class TinkerSilkDrillMovementBehaviour extends TinkerDrillMovementBehaviour {
    public TinkerSilkDrillMovementBehaviour(PartialModel model) {
        super(model);
    }

    @Override
    public ItemStack createTool() {
        ItemStack tool = super.createTool();
        tool.enchant(Enchantments.SILK_TOUCH, 1);
        return tool;
    }
}
