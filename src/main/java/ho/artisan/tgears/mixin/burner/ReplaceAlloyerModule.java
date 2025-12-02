package ho.artisan.tgears.mixin.burner;

import com.simibubi.create.api.equipment.goggles.IHaveGoggleInformation;
import ho.artisan.tgears.api.block.module.ExtendedFuelModule;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import slimeknights.mantle.block.entity.MantleBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.controller.AlloyerBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.module.SolidFuelModule;

@Mixin(AlloyerBlockEntity.class)
public class ReplaceAlloyerModule implements IHaveGoggleInformation {

    @Redirect(
            remap = false,
            method = "<init>(Lnet/minecraft/world/level/block/entity/BlockEntityType;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V",
            at = @At(
                    value = "NEW",
                    target = "Lslimeknights/tconstruct/smeltery/block/entity/module/SolidFuelModule;"
            )
    )
    private SolidFuelModule init(MantleBlockEntity parent, BlockPos fuelPos) {
        return new ExtendedFuelModule(parent, fuelPos);
    }
}
