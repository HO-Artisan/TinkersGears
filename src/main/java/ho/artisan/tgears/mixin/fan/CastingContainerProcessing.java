package ho.artisan.tgears.mixin.fan;

import com.simibubi.create.content.kinetics.fan.AirCurrent;
import com.simibubi.create.content.kinetics.fan.processing.AllFanProcessingTypes;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import ho.artisan.tgears.api.block.entity.IFanProcessingTarget;
import ho.artisan.tgears.util.MathUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import slimeknights.tconstruct.library.recipe.casting.ICastingRecipe;
import slimeknights.tconstruct.smeltery.block.entity.CastingBlockEntity;

@Mixin(CastingBlockEntity.class)
public class CastingContainerProcessing implements IFanProcessingTarget {
    @Shadow(remap = false)
    private int timer;

    @Shadow(remap = false)
    private ICastingRecipe currentRecipe;

    @Unique
    private int tgears$ticker;

    @Unique
    public void tgears$process(int times) {
        if (timer > 0 && timer + times > 0)
            timer += times;
    }

    @Override
    public void tgears$process(FanProcessingType segmentType, float speed, AirCurrent airCurrent) {
        if(currentRecipe != null) {
            int n = MathUtil.findN(Math.abs((int) speed));
            if (segmentType == null) {
                // 128~255: process 1 more times
                // 256: process 2 more times
                if (n >= 7)
                    tgears$process(n - 6);
            } else if (segmentType == AllFanProcessingTypes.SPLASHING) {
                // 32~63: process 1 more time
                // 64~127: process 2 more time
                // 128~255: process 3 more times
                // 256: process 4 more times
                if (n >= 5)
                    tgears$process(n - 4);
            } else if (segmentType == AllFanProcessingTypes.BLASTING) {
                // > 32: stop process
                if (n >= 5)
                    tgears$process(-1);
            } else if (segmentType == AllFanProcessingTypes.SMOKING) {
                // > 32: half process
                if (n >= 5) {
                    if (tgears$ticker % 2 == 1) {
                        tgears$process(-1);
                        tgears$ticker = 0;
                    } else {
                        tgears$ticker += 1;
                    }
                }
            } else if (FanProcessingType.parse("create_henry:freezing") != null) {
                if (segmentType == FanProcessingType.parse("create_henry:freezing")) {
                    // 32~63: process 2 more time
                    // 64~127: process 4 more time
                    // 128~255: process 6 more times
                    // 256: process 8 more times
                    if (n >= 5)
                        tgears$process((n - 4) * 2);
                }
            }
        }
    }
}
