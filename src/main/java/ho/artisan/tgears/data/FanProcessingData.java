package ho.artisan.tgears.data;

import com.simibubi.create.api.registry.SimpleRegistry;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import ho.artisan.tgears.config.data.FanProcessingFactors;

import java.util.function.DoubleSupplier;

/**
 * {@linkplain FanProcessingFactors}
 */
public class FanProcessingData {
    public static final SimpleRegistry<FanProcessingType, DoubleSupplier> FACTORS = SimpleRegistry.create();

    public static double getFactor(FanProcessingType type) {
        if (type == null)
            return 1.0f;
        DoubleSupplier supplier = FACTORS.get(type);
        return supplier == null ? 1.0f : supplier.getAsDouble();
    }

}
