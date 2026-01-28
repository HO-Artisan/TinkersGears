package ho.artisan.tgears.api;

import com.simibubi.create.content.kinetics.fan.processing.AllFanProcessingTypes;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;

/**
 * 用于可被Create模组风扇气流处理的方块实体的API接口
 * 定义了风扇气流处理逻辑的核心交互方法
 */
public interface IFanProcessingTarget {
    /**
     * 用特定类型和速度的风扇气流处理目标
     * 当风扇气流影响到该方块实体时调用
     *
     * @param segmentType 风扇处理类型（具体查看：{@linkplain AllFanProcessingTypes}）
     * @param speed       生成气流的风扇速度（0.0f至最大风扇速度）
     */
    void process(FanProcessingType segmentType, float speed);

    /**
     * 检查目标是否可被指定类型的风扇处理
     * 用于阻止无效的处理交互
     *
     * @param segmentType 要检查的风扇处理类型
     * @return 若目标支持该处理类型则返回true，否则返回false
     */
    boolean canProcess(FanProcessingType segmentType);
}
