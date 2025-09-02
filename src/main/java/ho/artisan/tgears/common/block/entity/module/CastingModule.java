package ho.artisan.tgears.common.block.entity.module;

import com.simibubi.create.content.kinetics.fan.processing.AllFanProcessingTypes;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import ho.artisan.tgears.util.MathUtil;

public interface CastingModule {
    void tgears$process(int times);
    boolean tgears$hasRecipe();

    static void changeSpeedWithFan(CastingModule module, FanProcessingType segmentType, float speed) {
        if(module.tgears$hasRecipe()) {
            int n = MathUtil.findN(Math.abs((int) speed));
            if (segmentType == null) {
                // 128~255: process 1 more times
                // 256: process 2 more times
                if (n >= 7)
                    module.tgears$process(n-6);
            } else if (segmentType == AllFanProcessingTypes.SPLASHING) {
                // 32~63: process 1 more time
                // 64~127: process 2 more time
                // 128~255: process 3 more times
                // 256: process 4 more times
                if (n >= 5)
                    module.tgears$process(n-4);
            } else if (segmentType == AllFanProcessingTypes.BLASTING) {
                if (n >= 5)
                    module.tgears$process(-1);
            } else if (FanProcessingType.parse("freezing") != null) {
                if (segmentType == FanProcessingType.parse("freezing")) {
                    // 32~63: process 2 more time
                    // 64~127: process 4 more time
                    // 128~255: process 6 more times
                    // 256: process 8 more times
                    if (n >= 5)
                        module.tgears$process((n-4) * 2);
                }
            }
        }
    }
}
