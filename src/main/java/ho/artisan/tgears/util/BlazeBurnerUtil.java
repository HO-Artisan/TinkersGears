package ho.artisan.tgears.util;

import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.TinkersGearsConfig;
import net.minecraft.network.chat.Component;
import slimeknights.tconstruct.smeltery.block.entity.module.SolidFuelModule;

import java.util.List;

import static ho.artisan.tgears.compat.TinkersGearsCompat.LOW_HEATED_LOADED;

public final class BlazeBurnerUtil {

    private BlazeBurnerUtil() {
    }

    public static int getTemperature(BlazeBurnerBlock.HeatLevel level) {
        if (LOW_HEATED_LOADED){
            return switch (level.ordinal()){ //Create Low-Heated
                case 0, 1 -> 0; //None, Smouldering
                case 5 -> TinkersGearsConfig.server().extinguishedTemperature.get(); //Low
                case 2, 3 -> TinkersGearsConfig.server().kindledTemperature.get(); //Fading, Kindled
                case 4 -> TinkersGearsConfig.server().seethingTemperature.get(); // Seething
                default -> throw new IllegalStateException("Unexpected Temperature ordinal: " + level.ordinal());
            };
        }
        else{
            return switch (level) {
                case NONE -> 0;
                case SMOULDERING -> TinkersGearsConfig.server().extinguishedTemperature.get();
                case FADING, KINDLED -> TinkersGearsConfig.server().kindledTemperature.get();
                case SEETHING -> TinkersGearsConfig.server().seethingTemperature.get();
            };
        }
    }

    public static int getRate(BlazeBurnerBlock.HeatLevel level) {
        if (LOW_HEATED_LOADED){
            return switch (level.ordinal()){ //Create Low-Heated
                case 0, 1 -> 0; //None, Smouldering
                case 5 -> TinkersGearsConfig.server().extinguishedRate.get(); //Low
                case 2, 3 -> TinkersGearsConfig.server().kindledRate.get(); //Fading, Kindled
                case 4 -> TinkersGearsConfig.server().seethingRate.get(); // Seething
                default -> throw new IllegalStateException("Unexpected Rate ordinal: " + level.ordinal());
            };
        }
        else {
            return switch (level) {
                case NONE -> 0;
                case SMOULDERING -> TinkersGearsConfig.server().extinguishedRate.get();
                case FADING, KINDLED -> TinkersGearsConfig.server().kindledRate.get();
                case SEETHING -> TinkersGearsConfig.server().seethingRate.get();
            };
        }
    }

    public static void addToGoggleTooltip(List<Component> tooltip, SolidFuelModule module) {
        GogglesUtil.addTemperature(tooltip, module.getTemperature());

        GogglesUtil.addRate(tooltip, module.getRate() * 10);
    }
}
