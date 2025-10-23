package ho.artisan.tgears.api.block.module;

import ho.artisan.tgears.api.block.entity.ITinkerHeatSource;
import net.minecraft.core.BlockPos;
import slimeknights.mantle.block.entity.MantleBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.module.SolidFuelModule;

public class ExtendedFuelModule extends SolidFuelModule {
    private final BlockPos pos;

    public ExtendedFuelModule(MantleBlockEntity parent, BlockPos fuelPos) {
        super(parent, fuelPos);
        this.pos = fuelPos;
    }

    public boolean isHeating() {
        ITinkerHeatSource heatSource = getHeatSource();
        return heatSource != null && heatSource.tgears$isHeating();
    }

    public ITinkerHeatSource getHeatSource() {
        if (getLevel().getBlockEntity(pos) instanceof ITinkerHeatSource source)
            return source;
        return null;
    }

    @Override
    public int findFuel(boolean consume) {
        return isHeating() ? getHeatSource().tgears$getTemperature() : super.findFuel(consume);
    }

    @Override
    public int getTemperature() {
        return isHeating() ? getHeatSource().tgears$getTemperature() : super.getTemperature();
    }

    @Override
    public int getRate() {
        return isHeating() ? getHeatSource().tgears$getRate() : super.getRate();
    }

    @Override
    public boolean hasFuel() {
        return isHeating() || super.hasFuel();
    }

    @Override
    public void decreaseFuel(int amount) {
        if (!isHeating()) {
            super.decreaseFuel(amount);
        }
    }
}
