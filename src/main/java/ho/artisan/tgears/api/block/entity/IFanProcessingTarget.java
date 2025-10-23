package ho.artisan.tgears.api.block.entity;

import com.simibubi.create.content.kinetics.fan.AirCurrent;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;

public interface IFanProcessingTarget {
    void tgears$process(FanProcessingType segmentType, float speed, AirCurrent airCurrent);
}
