package ho.artisan.tgears.mixin;

import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import ho.artisan.tgears.TinkersGearsConfig;
import ho.artisan.tgears.common.block.entity.module.BurnerModule;
import ho.artisan.tgears.util.BlazeBurnerUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import slimeknights.mantle.block.entity.MantleBlockEntity;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.smeltery.block.entity.controller.MelterBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.module.SolidFuelModule;

import java.util.List;

import static slimeknights.tconstruct.smeltery.block.controller.ControllerBlock.ACTIVE;

@Mixin(MelterBlockEntity.class)
public class MelterMixin implements IHaveGoggleInformation {

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

    @Redirect(
            remap = false,
            method = "<init>(Lnet/minecraft/world/level/block/entity/BlockEntityType;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V",
            at = @At(
                    value = "NEW",
                    target = "Lslimeknights/tconstruct/smeltery/block/entity/module/SolidFuelModule;"
            )
    )
    private SolidFuelModule init(MantleBlockEntity parent, BlockPos fuelPos) {
        return new BurnerModule(parent, fuelPos);
    }
}
