package ho.artisan.tgears.mixin.modifier;

import com.simibubi.create.content.equipment.armor.CardboardArmorHandler;
import ho.artisan.tgears.index.TGMaterials;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.materials.definition.MaterialVariant;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mixin(CardboardArmorHandler.class)
public class TinkerCardboardArmorSupport {

    @Inject(
            remap = false,
            method = "isCardboardArmor",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private static void support(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof IModifiable modifiable) {
            ToolStack tool = ToolStack.from(stack);
            boolean flag = true;
            for (MaterialVariant material : tool.getMaterials()) {
                flag = flag && material.matches(TGMaterials.Ids.CARDBOARD);
            }
            if (flag) {
                cir.setReturnValue(true);
            }
        }
    }
}
