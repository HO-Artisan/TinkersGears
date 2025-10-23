package ho.artisan.tgears.mixin.tooltip;

import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import ho.artisan.tgears.TinkersGearsConfig;
import ho.artisan.tgears.util.BlazeBurnerUtil;
import ho.artisan.tgears.util.TinkerGogglesUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import slimeknights.tconstruct.library.fluid.FluidTankAnimated;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.block.entity.controller.AlloyerBlockEntity;

import java.util.List;

import static slimeknights.tconstruct.smeltery.block.controller.ControllerBlock.ACTIVE;

@Mixin(AlloyerBlockEntity.class)
public class AlloyerInfo implements IHaveGoggleInformation {
    @Override
    public ItemStack getIcon(boolean isPlayerSneaking) {
        return new ItemStack(TinkerCommons.mightySmelting);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        if (!TinkersGearsConfig.IS_GOGGLES_ENABLED.get()) {
            return false;
        }
        boolean flag = false;

        AlloyerBlockEntity alloyer = (AlloyerBlockEntity)(Object)this;

        TinkerGogglesUtil.addStats(tooltip);

        if (alloyer.getBlockState().getValue(ACTIVE)) {
            BlazeBurnerUtil.addToGoggleTooltip(
                    tooltip,
                    alloyer.getFuelModule()
            );
            flag = true;
        }

        FluidTankAnimated tank = alloyer.getTank();
        if (!tank.isEmpty()) {
            TinkerGogglesUtil.addStats(tooltip);
            TinkerGogglesUtil.addFluid(tooltip, tank.getFluid());
            flag = true;
        }

        if (!flag) {
            tooltip.remove(0);
        }

        return flag;
    }
}
