package ho.artisan.tgears.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * 表示可作为热源的方块的接口
 */
public interface ITinkerBlockHeatSource extends ITinkerHeatSource {

    /**
     * 获取该热源方块提供的温度
     *
     * @param level 方块所在的世界
     * @param pos   方块在世界中的位置
     * @return 热源的温度值
     */
    int getTemperature(Level level, BlockPos pos);

    /**
     * 获取该热源方块的加热速率
     *
     * @param level 方块所在的世界/层级
     * @param pos   方块在世界中的位置
     * @return 加热速率值
     */
    int getRate(Level level, BlockPos pos);

    /**
     * 检查该方块当前是否作为热源活跃
     *
     * @param level 方块所在的世界/层级
     * @param pos   方块在世界中的位置
     * @return 若方块正在加热则返回true，否则返回false
     */
    boolean isHeating(Level level, BlockPos pos);
}
