package ho.artisan.tgears.mixin.crushing;

import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.belt.transport.BeltCrusherInteractionHandler;
import com.simibubi.create.content.kinetics.belt.transport.BeltInventory;
import com.simibubi.create.content.kinetics.belt.transport.TransportedItemStack;
import ho.artisan.tgears.util.BeltUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BeltInventory.class)
public class BeltInventoryFix {

    @Final
    @Shadow(remap = false)
    BeltBlockEntity belt;
    @Shadow(remap = false)
    boolean beltMovementPositive;

    @Redirect(
            remap = false,
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/simibubi/create/content/kinetics/belt/transport/BeltCrusherInteractionHandler;checkForCrushers(Lcom/simibubi/create/content/kinetics/belt/transport/BeltInventory;Lcom/simibubi/create/content/kinetics/belt/transport/TransportedItemStack;F)Z"
            )
    )
    public boolean checkForCrushers(BeltInventory crusherBE, TransportedItemStack currentItem, float world) {
        BeltInventory beltInventory = (BeltInventory) (Object) this;
        boolean flag = BeltUtil.checkForCrushers(beltInventory, currentItem, world, beltMovementPositive, belt);
        return BeltCrusherInteractionHandler.checkForCrushers(beltInventory, currentItem, world) || flag;
    }
}
