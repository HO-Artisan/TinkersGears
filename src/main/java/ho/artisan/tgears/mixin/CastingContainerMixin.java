package ho.artisan.tgears.mixin;


import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import ho.artisan.tgears.TinkersGearsConfig;
import ho.artisan.tgears.common.block.entity.module.CastingModule;
import ho.artisan.tgears.util.TinkerGogglesUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import slimeknights.tconstruct.library.recipe.casting.ICastingRecipe;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.block.entity.CastingBlockEntity;

import java.util.List;

@Mixin(CastingBlockEntity.class)
public class CastingContainerMixin implements IHaveGoggleInformation, CastingModule {

    @Shadow(remap = false)
    private int timer;

    @Shadow(remap = false)
    private ICastingRecipe currentRecipe;

    @Override
    public void tgears$process(int times) {
        timer += times;
    }

    @Override
    public boolean tgears$hasRecipe() {
        return currentRecipe != null;
    }

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

        CastingBlockEntity container = (CastingBlockEntity)(Object)this;
        TinkerGogglesUtil.addStats(tooltip);

        IFluidHandler fluidHandler = container.getCapability(ForgeCapabilities.FLUID_HANDLER).orElse(null);

        if (fluidHandler != null && !fluidHandler.getFluidInTank(0).isEmpty()) {
            TinkerGogglesUtil.addFluidStats(tooltip);
            TinkerGogglesUtil.addFluid(tooltip, fluidHandler.getFluidInTank(0));
            flag = true;
        }

        if (container.getCoolingTime() > 0) {
            TinkerGogglesUtil.addOutput(tooltip, container.getRecipeOutput());
            TinkerGogglesUtil.addProgress(tooltip, container.getTimer(), container.getCoolingTime());
            flag = true;
        }

        if (!flag) {
            tooltip.remove(0);
        }

        return flag;
    }
}
