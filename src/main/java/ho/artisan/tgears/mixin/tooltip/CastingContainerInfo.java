package ho.artisan.tgears.mixin.tooltip;

import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import ho.artisan.tgears.TinkersGearsConfig;
import ho.artisan.tgears.util.GogglesUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.block.entity.CastingBlockEntity;

import java.util.List;

@Mixin(CastingBlockEntity.class)
public class CastingContainerInfo implements IHaveGoggleInformation {
    @Override
    public ItemStack getIcon(boolean isPlayerSneaking) {
        return new ItemStack(TinkerCommons.mightySmelting);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        if (!TinkersGearsConfig.client().enableGoggleOverlay.get()) {
            return false;
        }

        boolean flag = false;

        CastingBlockEntity container = (CastingBlockEntity) (Object) this;
        GogglesUtil.addStats(tooltip);

        IFluidHandler fluidHandler = container.getCapability(ForgeCapabilities.FLUID_HANDLER).orElse(null);

        if (fluidHandler != null && !fluidHandler.getFluidInTank(0).isEmpty()) {
            GogglesUtil.addFluidStats(tooltip);
            GogglesUtil.addFluid(tooltip, fluidHandler.getFluidInTank(0));
            flag = true;
        }

        if (container.getCoolingTime() > 0) {
            GogglesUtil.addOutput(tooltip, container.getRecipeOutput());
            GogglesUtil.addProgress(tooltip, container.getTimer(), container.getCoolingTime());
            flag = true;
        }

        if (!flag) {
            tooltip.remove(0);
        }

        return flag;
    }
}
