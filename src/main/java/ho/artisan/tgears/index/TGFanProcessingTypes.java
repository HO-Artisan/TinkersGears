package ho.artisan.tgears.index;

import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import ho.artisan.tgears.TinkersGears;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public final class TGFanProcessingTypes {
    private TGFanProcessingTypes() {
    }

    private static final DeferredRegister<FanProcessingType> TYPE_REGISTER = DeferredRegister.create(CreateBuiltInRegistries.FAN_PROCESSING_TYPE.key(), TinkersGears.MOD_ID);

    public static void register(IEventBus eventBus) {
        TYPE_REGISTER.register(eventBus);
    }
}
