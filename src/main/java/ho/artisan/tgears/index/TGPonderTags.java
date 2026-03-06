package ho.artisan.tgears.index;

import ho.artisan.tgears.TinkersGears;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public final class TGPonderTags {
    public static final ResourceLocation COMPAT = ResourceLocation.tryBuild(TinkersGears.MOD_ID, "compat");

    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper) {
        helper.registerTag(COMPAT)
                .addToIndex()
                .item(TGItems.CRUSHED_RAW_COBALT.asItem(), true, false)
                .title("Compat")
                .description("Tinker's Gears from the Create")
                .register();

        helper.addToTag(COMPAT)
                .add(TGBlocks.TINKER_DRILL.getId())
                .add(TGBlocks.TINKER_SILKTOUCH_DRILL.getId())
                .add(TGBlocks.TINKER_SPOUT.getId())
                .add(TGBlocks.TINKER_FAN.getId());

        TinkersGears.LOGGER.info("Ponder tags initialized");
    }
}
