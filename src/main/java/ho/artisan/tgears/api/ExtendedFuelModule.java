package ho.artisan.tgears.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import slimeknights.mantle.block.entity.MantleBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.module.SolidFuelModule;

/**
 * 扩展燃料模块，支持自定义热源
 * 继承自基础SolidFuelModule，整合额外的加热机制
 */
public class ExtendedFuelModule extends SolidFuelModule {
    private final BlockPos fuelPos;

    public ExtendedFuelModule(MantleBlockEntity parent, BlockPos fuelPos) {
        super(parent, fuelPos);
        this.fuelPos = fuelPos;
    }

    /**
     * 检查燃料位置是否存在活跃的热源
     * 优先检查方块实体，再检查方块类型
     *
     * @return 若存在活跃热源则返回true，否则返回false
     */
    public boolean isHeating() {
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

    /**
     * 从燃料位置的活跃热源获取温度
     * 优先检查方块实体，再检查方块类型
     *
     * @return 热源的温度值，若无活跃热源则返回0
     */
    private int getHeatSourceTemperature() {
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

    /**
     * 从燃料位置的活跃热源获取加热速率
     * 优先检查方块实体，再检查方块类型
     *
     * @return 热源的加热速率值，若无活跃热源则返回0
     */
    private int getHeatSourceRate() {
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

    /**
     * 查找燃料温度，若存在活跃自定义热源则使用其温度
     * 若无活跃热源则回退到父类实现
     *
     * @param consume 本实现中未使用（继承的参数）
     * @return 活跃热源的温度或父类燃料系统的温度
     */
    @Override
    public int findFuel(boolean consume) {
        return isHeating() ? getHeatSourceTemperature() : super.findFuel(consume);
    }

    /**
     * 获取当前温度，若存在活跃自定义热源则使用其温度
     * 若无活跃热源则回退到父类实现
     *
     * @return 当前温度值
     */
    @Override
    public int getTemperature() {
        return isHeating() ? getHeatSourceTemperature() : super.getTemperature();
    }

    /**
     * 获取当前加热速率，若存在活跃自定义热源则使用其速率
     * 若无活跃热源则回退到父类实现
     *
     * @return 当前加热速率值
     */
    @Override
    public int getRate() {
        return isHeating() ? getHeatSourceRate() : super.getRate();
    }

    /**
     * 检查是否存在可用燃料/热量，包括活跃的自定义热源
     *
     * @return 若存在活跃热源或父类系统中有燃料则返回true
     */
    @Override
    public boolean hasFuel() {
        return isHeating() || super.hasFuel();
    }

    /**
     * 减少燃料量，但仅在无活跃自定义热源时执行
     *
     * @param amount 要减少的燃料量
     */
    @Override
    public void decreaseFuel(int amount) {
        if (!isHeating()) {
            super.decreaseFuel(amount);
        }
    }
}
