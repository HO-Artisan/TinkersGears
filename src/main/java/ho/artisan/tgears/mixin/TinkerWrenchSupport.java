package ho.artisan.tgears.mixin;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.equipment.wrench.WrenchEventHandler;
import ho.artisan.tgears.index.TGModifiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

@Mixin(WrenchEventHandler.class)
public class TinkerWrenchSupport {

    @Inject(
            remap = false,
            method = "useOwnWrenchLogicForCreateBlocks",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/event/entity/player/PlayerInteractEvent$RightClickBlock;getLevel()Lnet/minecraft/world/level/Level;",
                    ordinal = 1

            ),
            cancellable = true
    )
    private static void inject(PlayerInteractEvent.RightClickBlock event, CallbackInfo ci) {
        ItemStack s = event.getItemStack();
        if (s.getItem() instanceof IModifiable) {
            ToolStack stack = ToolStack.from(s);

            if (stack.getModifier(TGModifiers.Ids.WRENCH).getLevel() == 0) {
                ci.cancel();
            }
        }
    }

    @Redirect(
            remap = false,
            method = "useOwnWrenchLogicForCreateBlocks",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/simibubi/create/AllTags$AllItemTags;matches(Lnet/minecraft/world/item/Item;)Z"
            )
    )
    private static boolean support(AllTags.AllItemTags instance, Item item) {
        if (item instanceof IModifiable)
            return true;
        return instance.matches(item);
    }
}
