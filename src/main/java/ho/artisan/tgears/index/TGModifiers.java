package ho.artisan.tgears.index;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.common.modifier.*;
import net.minecraftforge.eventbus.api.IEventBus;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public final class TGModifiers {

    private TGModifiers() {}

    public static final class Ids {
        public static final ModifierId ADRENALINE = id("adrenaline");
        public static final ModifierId TOPNOTCH = id("topnotch");
        public static final ModifierId LIGHTWEIGHT = id("lightweight");
        public static final ModifierId CARDBOARD = id("cardboard");

        public static final ModifierId TAPERING = id("tapering");
        public static final ModifierId POLISHED = id("polished");

        public static final ModifierId GOGGLES = id("goggles");
        public static final ModifierId CRUSHING = id("crushing");
        public static final ModifierId EXTENDO = id("extendo");
        public static final ModifierId DIVING = id("diving_weights");

        public static final ModifierId STRESS_DRIVEN = id("stress_driven");
        public static final ModifierId SHARPNESS = id("sharpness");

        private static ModifierId id(String id) {
            return ModifierId.tryBuild(TinkersGears.MOD_ID, id);
        }
    }

    private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(TinkersGears.MOD_ID);

    public static final StaticModifier<CreatePolishedModifier> POLISHED = MODIFIERS.register("polished", CreatePolishedModifier::new);
    public static final StaticModifier<CreateCrushingModifier> CRUSHING =  MODIFIERS.register("crushing", CreateCrushingModifier::new);
    public static final StaticModifier<CreateExtendoModifier> EXTENDO = MODIFIERS.register("extendo", CreateExtendoModifier::new);

    public static final StaticModifier<CreateCardboardModifier> CARDBOARD = MODIFIERS.register("cardboard", CreateCardboardModifier::new);

    //public static final StaticModifier<AdrenalineModifier> ADRENALINE = MODIFIERS.register("adrenaline", AdrenalineModifier::new);

    public static void register(IEventBus bus) {
        MODIFIERS.register(bus);
    }
}
