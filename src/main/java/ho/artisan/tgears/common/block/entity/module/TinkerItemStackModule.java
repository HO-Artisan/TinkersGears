package ho.artisan.tgears.common.block.entity.module;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.recipe.ITinkerableContainer;
import slimeknights.tconstruct.library.tools.item.IModifiable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TinkerItemStackModule extends ItemStackHandler implements ITinkerableContainer {
    private final int separation;
    private final Supplier<Boolean> lockedSupplier;

    private final List<Consumer<TinkerItemStackModule>> listeners;

    public TinkerItemStackModule(int inputSlots, int outputSlots, Supplier<Boolean> lockedSupplier) {
        super(inputSlots + outputSlots);
        this.separation = inputSlots;
        this.lockedSupplier = lockedSupplier;
        this.listeners = new ArrayList<>();
    }

    public void addListener(Consumer<TinkerItemStackModule> listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (Consumer<TinkerItemStackModule> listener : listeners) {
            listener.accept(this);
        }
    }

    public int getInputSlots() {
        return separation;
    }

    public int getOutputSlots() {
        return getSlots() - separation;
    }

    @Override
    protected void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
        notifyListeners();
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (slot >= separation)
            return stack;
        return super.insertItem(slot, stack, simulate);
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (lockedSupplier.get())
            return ItemStack.EMPTY;
        return super.extractItem(slot, amount, simulate);
    }

    public boolean isTinkerable() {
        return getTinkerableStack().getItem() instanceof IModifiable;
    }

    @Override
    public ItemStack getTinkerableStack() {
        return getStackInSlot(0);
    }

    @Override
    public ItemStack getInput(int index) {
        return getStackInSlot(index);
    }

    public ItemStack getOutput(int index) {
        return getStackInSlot(index + separation);
    }

    public void setOutput(int index, ItemStack stack) {
        setStackInSlot(index + separation, stack);
    }

    @Override
    public int getInputCount() {
        return separation;
    }
}
