package ho.artisan.tgears.mixin;

import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import ho.artisan.tgears.TinkersGearsConfig;
import ho.artisan.tgears.util.TinkerGogglesUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import slimeknights.tconstruct.library.fluid.FluidTankAnimated;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.block.entity.component.TankBlockEntity;

import java.util.List;

@Mixin(TankBlockEntity.class)
public class TankMixin implements IHaveGoggleInformation {
    @Override
    public ItemStack getIcon(boolean isPlayerSneaking) {
        return new ItemStack(TinkerCommons.mightySmelting);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        if (!TinkersGearsConfig.IS_GOGGLES_ENABLED.get()) {
            return false;
        }

        TankBlockEntity blockEntity = (TankBlockEntity)(Object)this;
        FluidTankAnimated tank = blockEntity.getTank();
        if (!tank.isEmpty()) {
            TinkerGogglesUtil.addStats(tooltip);
            TinkerGogglesUtil.addFluid(tooltip, tank.getFluid());
            return true;
        }
        return false;
    }
}
