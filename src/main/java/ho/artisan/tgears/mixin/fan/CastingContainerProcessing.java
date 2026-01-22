package ho.artisan.tgears.mixin.fan;

import com.chemiofitor.tgearbox.api.IFanProcessingTarget;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import ho.artisan.tgears.data.FanProcessingData;
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
    private int coolingTime = -1;

    @Shadow(remap = false)
    private ICastingRecipe currentRecipe;

    @Unique
    private double tgears$remainder;

    @Unique
    private void tgears$process(int times) {
        if (timer + times < 0) {
            timer = 0;
        } else if (timer + times > coolingTime) {
            timer = coolingTime;
        } else if (timer > 0) {
            timer += times;
        }
    }

    @Override
    public boolean canProcess(FanProcessingType fanProcessingType) {
        return currentRecipe != null;
    }

    @Override
    public void process(FanProcessingType segmentType, float speed) {
        double factor = FanProcessingData.getFactor(segmentType);
        double tick = Math.sqrt(speed / 64.0f) * factor;

        int sign = tick < 0 ? -1 : 1;

        tick = Math.abs(tick);

        int integer = (int) tick; // 整数部分
        double remainer = tick - integer; // 小数部分

        tgears$remainder += remainer; // 累加小数部分

        if (tgears$remainder > 1) { // 累计进度超过1，额外处理1次
            tgears$remainder--;
            integer++;
        }

        tgears$process(sign * integer);
    }
}
