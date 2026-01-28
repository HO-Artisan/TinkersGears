package ho.artisan.tgears.mixin;

import com.simibubi.create.Create;
import ho.artisan.tgears.client.gui.TinkerCardboardArmorStealthOverlay;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.tools.item.armor.MultilayerArmorItem;

import java.util.function.Consumer;

@Mixin(MultilayerArmorItem.class)
public class TinkerCardboardHelmetOverlaySupport {
    @Inject(
            remap = false,
            method = "initializeClient",
            at = @At(
                    value = "TAIL"
            )
    )
    public void initMixin(Consumer<IClientItemExtensions> consumer, CallbackInfo ci) {
        consumer.accept(new TinkerCardboardArmorStealthOverlay(Create.asResource("textures/misc/package_blur.png")));
    }
}
