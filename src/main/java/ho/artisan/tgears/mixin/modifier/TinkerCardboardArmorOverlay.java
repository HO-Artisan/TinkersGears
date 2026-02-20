package ho.artisan.tgears.mixin.modifier;

import com.simibubi.create.content.equipment.armor.CardboardArmorStealthOverlay;
import ho.artisan.tgears.compat.CCGConfigSync;
import ho.artisan.tgears.compat.TinkersGearsCompat;
import ho.artisan.tgears.index.TGMaterials;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import slimeknights.tconstruct.library.materials.definition.MaterialVariant;
import slimeknights.tconstruct.library.tools.item.armor.ModifiableArmorItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mixin(ForgeGui.class)
public class TinkerCardboardArmorOverlay {

    @Redirect(
            remap = false,
            method = "renderHelmet",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/client/extensions/common/IClientItemExtensions;renderHelmetOverlay(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;IIF)V"
            )
    )
    public void test(IClientItemExtensions instance, ItemStack stack, Player player, int width, int height, float partialTick) {
        if (stack.getItem() instanceof ModifiableArmorItem) {
            ToolStack tool = ToolStack.from(stack);
            boolean flag = true;
            for (MaterialVariant material : tool.getMaterials()) {
                flag = flag && material.matches(TGMaterials.Ids.CARDBOARD);
            }
            if (flag) {
                if (TinkersGearsCompat.GOGGLES_LOADED)
                    if (CCGConfigSync.isCardboardOverlayRemoved())
                        return;
                new CardboardArmorStealthOverlay().renderHelmetOverlay(stack, player, width, height, partialTick);
            }
        } else {
            instance.renderHelmetOverlay(stack, player, width, height, partialTick);
        }
    }
}
