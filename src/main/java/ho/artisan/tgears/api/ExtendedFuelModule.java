package ho.artisan.tgears.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import slimeknights.mantle.block.entity.MantleBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.module.SolidFuelModule;

public class ExtendedFuelModule extends SolidFuelModule {
    private final BlockPos fuelPos;

    public ExtendedFuelModule(MantleBlockEntity parent, BlockPos fuelPos) {
        super(parent, fuelPos);
        this.fuelPos = fuelPos;
    }

    private boolean isCustomHeatSourceHeating() {
        Level level = getLevel();

        BlockEntity be = level.getBlockEntity(fuelPos);
        if (be instanceof ITinkerBlockEntityHeatSource heatSource) {
            return heatSource.isHeating();
        }

        Block block = level.getBlockState(fuelPos).getBlock();
        if (block instanceof ITinkerBlockHeatSource heatSource) {
            return heatSource.isHeating(level, fuelPos);
        }

        return false;
    }

    private int getCustomHeatSourceTemperature() {
        Level level = getLevel();

        BlockEntity be = level.getBlockEntity(fuelPos);
        if (be instanceof ITinkerBlockEntityHeatSource heatSource) {
            return heatSource.getTemperature();
        }

        Block block = level.getBlockState(fuelPos).getBlock();
        if (block instanceof ITinkerBlockHeatSource heatSource) {
            return heatSource.getTemperature(level, fuelPos);
        }

        return 0;
    }

    private int getCustomHeatSourceRate() {
        Level level = getLevel();

        BlockEntity be = level.getBlockEntity(fuelPos);
        if (be instanceof ITinkerBlockEntityHeatSource heatSource) {
            return heatSource.getRate();
        }

        Block block = level.getBlockState(fuelPos).getBlock();
        if (block instanceof ITinkerBlockHeatSource heatSource) {
            return heatSource.getRate(level, fuelPos);
        }

        return 0;
    }

    @Override
    public int findFuel(boolean consume) {
        if (isCustomHeatSourceHeating()) {
            int heatSourceTemp = getCustomHeatSourceTemperature();
            int heatSourceRate = getCustomHeatSourceRate();

            if (consume) {
                this.fuel = 1;
                this.fuelQuality = 100;
                this.temperature = heatSourceTemp;
                this.rate = heatSourceRate;
                this.getParent().setChangedFast();
            } else {
                this.temperature = 0;
                this.rate = 0;
            }
            return heatSourceTemp;
        } else {
            int nativeTemp = super.findFuel(consume);

            return nativeTemp;
        }
    }

    private MantleBlockEntity getParent() {
        return super.parent;
    }
}
