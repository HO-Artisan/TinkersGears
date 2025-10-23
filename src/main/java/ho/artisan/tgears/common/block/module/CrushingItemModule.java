package ho.artisan.tgears.common.block.module;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

public class CrushingItemModule implements IItemHandlerModifiable {
    private ItemStack itemStack = ItemStack.EMPTY;

    @Override
    public void setStackInSlot(int i, @NotNull ItemStack itemStack) {
        if (i == 0)
            this.itemStack = itemStack;
    }

    @Override
    public int getSlots() {
        return 1;
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int i) {
        if (i == 0)
            return itemStack;
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack insertItem(int i, @NotNull ItemStack itemStack, boolean b) {
        if (i == 0) {
            if (this.itemStack.is(itemStack.getItem())) {
                int count = Math.min(this.itemStack.getCount() + itemStack.getCount(), this.itemStack.getMaxStackSize());
                this.itemStack.setCount(count);
                return itemStack.split(this.itemStack.getCount() + itemStack.getCount() - count);
            }
        }
        return itemStack;
    }

    @Override
    public @NotNull ItemStack extractItem(int i, int number, boolean b) {
        if (i == 0) {
            return this.itemStack.split(number);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getSlotLimit(int i) {
        return 64;
    }

    @Override
    public boolean isItemValid(int i, @NotNull ItemStack itemStack) {
        return true;
    }
}
