package ho.artisan.tgears.mixin.tooltip;

import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import ho.artisan.tgears.TinkersGearsConfig;
import ho.artisan.tgears.util.BlazeBurnerUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.block.entity.controller.MelterBlockEntity;

import java.util.List;

import static slimeknights.tconstruct.smeltery.block.controller.ControllerBlock.ACTIVE;

@Mixin(MelterBlockEntity.class)
public class MelterInfo implements IHaveGoggleInformation {

    @Override
    public ItemStack getIcon(boolean isPlayerSneaking) {
        return new ItemStack(TinkerCommons.mightySmelting);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        if (!TinkersGearsConfig.IS_GOGGLES_ENABLED.get()) {
            return false;
        }

        MelterBlockEntity melter = (MelterBlockEntity)(Object)this;
        if (melter.getBlockState().getValue(ACTIVE)) {
            BlazeBurnerUtil.addToGoggleTooltip(
                    tooltip,
                    melter.getFuelModule()
            );
            return true;
        }
        return false;
    }
}
