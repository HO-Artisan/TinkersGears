package ho.artisan.tgears.mixin;


import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.kinetics.fan.EncasedFanBlockEntity;
import ho.artisan.tgears.TinkersGearsConfig;
import ho.artisan.tgears.util.MathUtil;
import ho.artisan.tgears.util.TinkerGogglesUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.block.entity.CastingBlockEntity;

import java.util.List;

@Mixin(CastingBlockEntity.class)
public class CastingContainerMixin implements IHaveGoggleInformation {

    @Shadow(remap = false)
    private int timer;

    @Inject(
            remap = false,
            method = "serverTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lslimeknights/tconstruct/smeltery/block/entity/CastingBlockEntity;updateAnalogSignal()V"
            )
    )
    public void tick(Level level, BlockPos pos, CallbackInfo ci) {
        for (Direction value : Direction.values()) {
            if (level.getBlockEntity(pos.relative(value)) instanceof EncasedFanBlockEntity fan) {
                if (fan.getSpeed() > 0) {
                    int n = MathUtil.findN(Math.abs((int) fan.getSpeed()));
                    if (n > 5) {
                        timer += n - 5;
                    }
                }
            }
        }
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
