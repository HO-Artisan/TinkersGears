package ho.artisan.tgears.mixin.crushing;

import com.simibubi.create.content.kinetics.crusher.CrushingWheelControllerBlockEntity;
import com.simibubi.create.content.processing.recipe.ProcessingInventory;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import ho.artisan.tgears.common.block.entity.TinkerFortuneCrushingWheelControllerBlockEntity;
import ho.artisan.tgears.common.block.entity.TinkerSilkyCrushingWheelControllerBlockEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(CrushingWheelControllerBlockEntity.class)
public class CrushingBlockEntityTick {
    @Redirect(
            remap = false,
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"
            )
    )
    public boolean tickMixin(Entity instance, DamageSource source, float amount) {
        CrushingWheelControllerBlockEntity entity = (CrushingWheelControllerBlockEntity) (Object) this;
        if (!(entity instanceof TinkerSilkyCrushingWheelControllerBlockEntity)) {
            instance.hurt(source, amount);
        }
        return false;
    }

    @Redirect(
            remap = false,
            method = "applyRecipe",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/simibubi/create/content/processing/recipe/ProcessingRecipe;rollResults()Ljava/util/List;"
            )
    )
    public List<ItemStack> applyRecipeMixin(ProcessingRecipe<?> instance) {
        CrushingWheelControllerBlockEntity entity = (CrushingWheelControllerBlockEntity) (Object) this;
        if (!(entity instanceof TinkerFortuneCrushingWheelControllerBlockEntity)) {
            return instance.rollResults();
        } else {
            return List.copyOf(instance.getRollableResultsAsItemStacks());
        }
    }

    @Redirect(
            remap = false,
            method = "applyRecipe",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/simibubi/create/content/processing/recipe/ProcessingInventory;clear()V",
                    ordinal = 1
            )
    )
    public void clearMixin(ProcessingInventory instance) {
        CrushingWheelControllerBlockEntity entity = (CrushingWheelControllerBlockEntity) (Object) this;
        if (!(entity instanceof TinkerSilkyCrushingWheelControllerBlockEntity)) {
            instance.clear();
        }
    }
}
