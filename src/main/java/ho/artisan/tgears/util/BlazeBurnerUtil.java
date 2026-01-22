package ho.artisan.tgears.util;

import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import ho.artisan.tgears.TinkersGearsConfig;
import net.minecraft.network.chat.Component;
import slimeknights.tconstruct.smeltery.block.entity.module.SolidFuelModule;

import java.util.List;

public final class BlazeBurnerUtil {

    private BlazeBurnerUtil() {
    }

    public static float getFactor(BlazeBurnerBlock.HeatLevel level) {
        return switch (level) {
            case NONE -> 0F;
            case SMOULDERING -> TinkersGearsConfig.server().extinguishedFactor.getF(); // 400
            case FADING, KINDLED -> TinkersGearsConfig.server().kindledFactor.getF(); // 800
            case SEETHING -> TinkersGearsConfig.server().seethingFactor.getF(); // 1600
        };
    }

    public static int getTemperature(BlazeBurnerBlock.HeatLevel level) {
        return (int) (getFactor(level) * 800);
    }

    public static int getRate(BlazeBurnerBlock.HeatLevel level) {
        return (int) (getFactor(level) * 10);
    }

    public static void addToGoggleTooltip(List<Component> tooltip, SolidFuelModule module) {
        GogglesUtil.addTemperature(tooltip, module.getTemperature());

        GogglesUtil.addRate(tooltip, module.getRate() * 10);
    }
}
