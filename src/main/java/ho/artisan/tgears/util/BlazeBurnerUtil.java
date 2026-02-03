package ho.artisan.tgears.util;

import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import ho.artisan.tgears.TinkersGearsConfig;
import net.minecraft.network.chat.Component;
import slimeknights.tconstruct.smeltery.block.entity.module.SolidFuelModule;

import java.util.List;

public final class BlazeBurnerUtil {

    private BlazeBurnerUtil() {
    }

    public static int getTemperature(BlazeBurnerBlock.HeatLevel level) {
        return switch (level) {
            case NONE -> 0;
            case SMOULDERING -> TinkersGearsConfig.server().extinguishedTemperature.get();
            case FADING, KINDLED -> TinkersGearsConfig.server().kindledTemperature.get();
            case SEETHING -> TinkersGearsConfig.server().seethingTemperature.get();
        };
    }

    public static int getRate(BlazeBurnerBlock.HeatLevel level) {
        return switch (level) {
            case NONE -> 0;
            case SMOULDERING -> TinkersGearsConfig.server().extinguishedRate.get();
            case FADING, KINDLED -> TinkersGearsConfig.server().kindledRate.get();
            case SEETHING -> TinkersGearsConfig.server().seethingRate.get();
        };
    }

    public static void addToGoggleTooltip(List<Component> tooltip, SolidFuelModule module) {
        GogglesUtil.addTemperature(tooltip, module.getTemperature());

        GogglesUtil.addRate(tooltip, module.getRate() * 10);
    }
}
