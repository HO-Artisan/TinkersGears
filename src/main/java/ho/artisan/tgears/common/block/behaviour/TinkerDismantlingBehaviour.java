package ho.artisan.tgears.common.block.behaviour;

import com.simibubi.create.foundation.blockEntity.behaviour.BehaviourType;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import ho.artisan.tgears.old.block.TinkerDismantlerBlock;
import ho.artisan.tgears.old.block.entity.TinkerDismantlerBlockEntity;
import ho.artisan.tgears.common.block.module.TinkerItemModule;
import ho.artisan.tgears.common.recipe.DismantlingRecipe;
import ho.artisan.tgears.index.TGRecipeTypes;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import slimeknights.tconstruct.library.materials.definition.MaterialVariant;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;
import slimeknights.tconstruct.library.tools.definition.module.material.ToolPartsHook;
import slimeknights.tconstruct.library.tools.part.IToolPart;

import java.util.List;
import java.util.Optional;

public class TinkerDismantlingBehaviour extends BlockEntityBehaviour {
    public static final BehaviourType<TinkerDismantlingBehaviour> TYPE = new BehaviourType<>();

    @Getter
    TinkerItemModule module;
    LazyOptional<ItemStackHandler> lazyItemHandler;

    int timer = 0;
    @Setter
    @Getter
    boolean locked = false;

    public TinkerDismantlingBehaviour(TinkerDismantlerBlockEntity be) {
        super(be);
        module = new TinkerItemModule(1, 9, () -> locked);
        lazyItemHandler = LazyOptional.of(() -> module);
        module.addListener(stackModule -> {
            boolean flag = !stackModule.getInput(0).isEmpty();
            this.setLocked(flag);
            getWorld().setBlock(getPos(), blockEntity.getBlockState().setValue(TinkerDismantlerBlock.CLAMPED, flag), Block.UPDATE_ALL);
        });
    }

    public Optional<DismantlingRecipe> getRecipe() {
        return TGRecipeTypes.DISMANTLING.find(module, getWorld());
    }

    @Override
    public void write(CompoundTag nbt, boolean clientPacket) {
        nbt.put("Inventory", module.serializeNBT());
        nbt.putInt("Timer", timer);
        nbt.putBoolean("Locked", locked);
        super.write(nbt, clientPacket);
    }

    @Override
    public void read(CompoundTag nbt, boolean clientPacket) {
        module.deserializeNBT(nbt.getCompound("Inventory"));
        timer = nbt.getInt("Timer");
        locked = nbt.getBoolean("Locked");
        super.read(nbt, clientPacket);
    }

    public void setTinkerable(ItemStack stack) {
        module.setStackInSlot(0, stack);
    }

    public boolean isEmpty() {
        return module.getStackInSlot(0).isEmpty();
    }


    public NonNullList<ItemStack> getItemStacks() {
        NonNullList<ItemStack> list = NonNullList.create();
        for (int i = 0; i < module.getSlots(); i++) {
            if (!module.getStackInSlot(i).isEmpty())
                list.add(module.getStackInSlot(i));
        }
        return list;
    }

    @Override
    public void tick() {
        super.tick();

        Optional<DismantlingRecipe> recipe = getRecipe();

        if (recipe.isPresent() || module.isTinkerable()) {

            timer++;

            int threshold = recipe.map(DismantlingRecipe::time).orElse(200);

            if (timer >= threshold) {
                timer = 0;
                recipe.ifPresentOrElse(this::processRecipe, this::process);
            }
        }
    }

    public void addPart(ItemStack stack) {
        for (int i = 0; i < module.getOutputSlots(); i++) {
            if (module.getOutput(i).isEmpty()) {
                module.setOutput(i, stack);
                return;
            }
        }
    }

    public void processRecipe(DismantlingRecipe recipe) {
        List<ItemStack> stacks = recipe.assembleAll(module, getWorld().getRandom());

        stacks.forEach(this::addPart);

        setTinkerable(ItemStack.EMPTY);
    }

    public void process() {
        ToolDefinition definition = module.getTinkerable().getDefinition();
        if (definition == ToolDefinition.EMPTY)
            return;
        List<IToolPart> parts = ToolPartsHook.parts(definition);
        for (int i = 0; i < parts.size(); i++) {
            IToolPart part = parts.get(i);
            MaterialVariant variant = module.getTinkerable().getMaterial(i);
            if (i == 0) {
                addPart(part.withMaterial(variant.getVariant()));
            } else {
                if (getWorld().getRandom().nextFloat() < 0.5f) {
                    addPart(part.withMaterial(variant.getVariant()));
                }
            }
        }

        setTinkerable(ItemStack.EMPTY);
    }

    public <T> LazyOptional<T> getItemCapability(Capability<T> cap, Direction side) {

        return lazyItemHandler.cast();
    }

    @Override
    public BehaviourType<?> getType() {
        return TYPE;
    }
}
