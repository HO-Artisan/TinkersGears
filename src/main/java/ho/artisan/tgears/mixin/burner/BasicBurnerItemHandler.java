package ho.artisan.tgears.mixin.burner;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import zeh.createlowheated.content.processing.basicburner.BasicBurnerBlockEntity;

@Mixin(value = BasicBurnerBlockEntity.BurnerItemHandler.class, remap = false)
public class BasicBurnerItemHandler implements IItemHandlerModifiable {
    @Shadow
    @Final
    BasicBurnerBlockEntity this$0;

    @Override
    public void setStackInSlot(int i, @NotNull ItemStack itemStack) {
        this.this$0.inputInv.setStackInSlot(0, itemStack);
    }

    @Shadow
    public int getSlots() {
        throw new AssertionError();
    }

    @Shadow
    public @NotNull ItemStack getStackInSlot(int slot) {
        throw new AssertionError();
    }

    @Shadow
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        throw new AssertionError();
    }

    @Shadow
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        throw new AssertionError();
    }

    @Shadow
    public int getSlotLimit(int slot) {
        throw new AssertionError();
    }

    @Shadow
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        throw new AssertionError();
    }
}
