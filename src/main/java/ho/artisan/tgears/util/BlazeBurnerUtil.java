package ho.artisan.tgears.util;

import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import ho.artisan.tgears.TinkersGearsConfig;
import net.minecraft.network.chat.Component;
import slimeknights.tconstruct.smeltery.block.entity.module.SolidFuelModule;

import java.util.List;

public class BlazeBurnerUtil {
    public static float getFactor(BlazeBurnerBlock.HeatLevel level) {
        return switch (level) {
            case NONE -> 0F;
            case SMOULDERING -> TinkersGearsConfig.EXTINGUISHED_FACTOR.get(); // 400
            case FADING, KINDLED -> TinkersGearsConfig.KINDLED_FACTOR.get(); // 800
            case SEETHING -> TinkersGearsConfig.SEETHING_FACTOR.get(); // 1600
        };
    }

    public static int getTemperature(BlazeBurnerBlock.HeatLevel level) {
        return (int) (getFactor(level) * 800);
    }

    public static int getRate(BlazeBurnerBlock.HeatLevel level) {
        return (int) (getFactor(level) * 10);
    }

    public static void addToGoggleTooltip(List<Component> tooltip, SolidFuelModule module) {
        TinkerGogglesUtil.addTemperature(tooltip, module.getTemperature());

        TinkerGogglesUtil.addRate(tooltip, module.getRate() * 10);
    }
}
