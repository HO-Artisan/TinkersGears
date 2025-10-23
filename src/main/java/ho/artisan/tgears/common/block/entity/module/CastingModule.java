package ho.artisan.tgears.common.block.entity.module;

import com.simibubi.create.content.kinetics.fan.processing.AllFanProcessingTypes;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import ho.artisan.tgears.util.MathUtil;

public interface CastingModule {
    void tgears$process(int times);
    boolean tgears$hasRecipe();
    int tgears$getTimer();
    int tgears$getTicker();
    void tgears$setTicker(int ticker);

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
                // > 32: stop process
                if (n >= 5)
                    module.tgears$process(-1);
            } else if (segmentType == AllFanProcessingTypes.SMOKING) {
                // > 32: half process
                if (n >= 5) {
                    if (module.tgears$getTicker() % 2 == 1) {
                        module.tgears$process(-1);
                        module.tgears$setTicker(0);
                    } else {
                        module.tgears$setTicker(module.tgears$getTicker() + 1);
                    }
                }
            } else if (FanProcessingType.parse("create_henry:freezing") != null) {
                if (segmentType == FanProcessingType.parse("create_henry:freezing")) {
                    // 32~63: process 2 more time
                    // 64~127: process 4 more time
                    // 128~255: process 6 more times
                    // 256: process 8 more times
                    if (n >= 5)
                        module.tgears$process((n-4) * 2);
                }
            } else if (FanProcessingType.parse("create_henry:seething") != null) {
                if (segmentType == FanProcessingType.parse("create_henry:seething")) {
                    // > 32: process -1 more time
                    // 32 ~ 8: stop process
                    if (n >= 5)
                        module.tgears$process(-2);
                    else if (n >= 3)
                        module.tgears$process(-1);
                }
            }
        }
    }
}
