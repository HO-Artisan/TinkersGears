package ho.artisan.tgears.api;

/**
 * 表示可作为热源的方块实体的接口
 * 与方块热源不同，方块实体通常自身持有世界/位置数据
 */
public interface ITinkerBlockEntityHeatSource extends ITinkerHeatSource {

    /**
     * 获取该热源方块实体提供的温度
     *
     * @return 热源的温度值
     */
    int getTemperature();

    /**
     * 获取该热源方块实体的加热速率
     *
     * @return 加热速率值
     */
    int getRate();

    /**
     * 检查该方块实体当前是否作为热源活跃
     *
     * @return 若方块实体正在加热则返回true，否则返回false
     */
    boolean isHeating();
}
