package ho.artisan.tgears.mixin;

import ho.artisan.tgears.data.ExtendedFuelModule;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import slimeknights.mantle.block.entity.MantleBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.controller.MelterBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.module.SolidFuelModule;

/**
 * 对TiC的MelterBlockEntity（冶炼炉炉方块实体实体）进行Mixin
 * 用于将默认燃料模块替换为自定义的ExtendedFuelModule
 */
@Mixin(MelterBlockEntity.class)
public class ReplaceMelterFuel {

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
