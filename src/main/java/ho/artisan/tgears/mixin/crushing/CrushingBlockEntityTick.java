package ho.artisan.tgears.mixin.crushing;

import com.simibubi.create.content.kinetics.crusher.CrushingWheelControllerBlockEntity;
import com.simibubi.create.content.processing.recipe.ProcessingInventory;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import ho.artisan.tgears.old.block.entity.TinkerFortuneCrushingWheelControllerBlockEntity;
import ho.artisan.tgears.old.block.entity.TinkerSilkyCrushingWheelControllerBlockEntity;
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
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"
            )
    )
    public boolean hurtMixin(Entity instance, DamageSource source, float amount) {
        CrushingWheelControllerBlockEntity entity = (CrushingWheelControllerBlockEntity) (Object) this;
        if (!(entity instanceof TinkerSilkyCrushingWheelControllerBlockEntity)) {
            return instance.hurt(source, amount); //
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
    public List<ItemStack> fortuneMixin(ProcessingRecipe<?> instance) {
        CrushingWheelControllerBlockEntity entity = (CrushingWheelControllerBlockEntity) (Object) this;
        if (!(entity instanceof TinkerFortuneCrushingWheelControllerBlockEntity)) {
            return instance.rollResults();
        } else {
            return instance.getRollableResults().stream().map(ProcessingOutput::getStack).map(ItemStack::copy).toList();
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
    public void notDestroyMixin(ProcessingInventory instance) {
        CrushingWheelControllerBlockEntity entity = (CrushingWheelControllerBlockEntity) (Object) this;
        if (!(entity instanceof TinkerSilkyCrushingWheelControllerBlockEntity)) {
            instance.clear();
        }
    }
}
