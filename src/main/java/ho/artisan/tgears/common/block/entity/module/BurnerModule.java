package ho.artisan.tgears.common.block.entity.module;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import ho.artisan.tgears.util.BlazeBurnerUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import slimeknights.mantle.block.entity.MantleBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.module.SolidFuelModule;

public class BurnerModule extends SolidFuelModule {
    private final BlockPos fuelPos;

    public BurnerModule(MantleBlockEntity parent, BlockPos fuelPos) {
        super(parent, fuelPos);
        this.fuelPos = fuelPos;
    }

    public boolean isBurner() {
        BlockState state = parent.getLevel().getBlockState(fuelPos);
        if (state.is(AllBlocks.BLAZE_BURNER.get()))
            return state.getValue(BlazeBurnerBlock.HEAT_LEVEL).isAtLeast(BlazeBurnerBlock.HeatLevel.SMOULDERING);
        return false;
    }

    public float getBurningFactor() {
        BlockState state = parent.getLevel().getBlockState(fuelPos);
        if (state.is(AllBlocks.BLAZE_BURNER.get()))
            return BlazeBurnerUtil.getFactor(state.getValue(BlazeBurnerBlock.HEAT_LEVEL));
        else
            return 0F;
    }

    @Override
    public int findFuel(boolean consume) {
        if (isBurner()) {
            return (int) (getBurningFactor() * 800);
        } else {
            return super.findFuel(consume);
        }
    }

    @Override
    public int getTemperature() {
        if (isBurner()) {
            return (int) (getBurningFactor() * 800);
        } else {
            return super.getTemperature();
        }
    }

    @Override
    public int getRate() {
        if (isBurner()) {
            return (int) (getBurningFactor() * 10);
        } else {
            return super.getRate();
        }
    }

    @Override
    public boolean hasFuel() {
        return isBurner() || super.hasFuel();
    }

    @Override
    public void decreaseFuel(int amount) {
        if (!isBurner()) {
            super.decreaseFuel(amount);
        }
    }
}
