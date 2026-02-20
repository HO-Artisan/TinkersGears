package ho.artisan.tgears.mixin.modifier;

import com.simibubi.create.content.equipment.armor.DivingBootsItem;
import ho.artisan.tgears.index.TGModifiers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mixin(DivingBootsItem.class)
public class DivingBootsModification {

    @Inject(
            remap = false,
            method = "getWornItem",
            at = @At(value = "RETURN"),
            cancellable = true
    )
    private static void getWornItemMixin(Entity entity, CallbackInfoReturnable<ItemStack> cir) {
        if (entity instanceof LivingEntity livingEntity) {
            ItemStack stack = livingEntity.getItemBySlot(EquipmentSlot.FEET);
            if (stack.getItem() instanceof IModifiable) {
                ToolStack tool = ToolStack.from(stack.copy());

                if (tool.getModifier(TGModifiers.Ids.DIVING).getLevel() > 0) {
                    cir.setReturnValue(stack);
                }
            }
        }
    }
}
