package ho.artisan.tgears.index;

import ho.artisan.tgears.TinkersGears;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

public final class TGPonderTags {
    public static final ResourceLocation SMELTERY = new ResourceLocation(TinkersGears.MOD_ID, "smeltery");
    public static final ResourceLocation COMPAT = new ResourceLocation(TinkersGears.MOD_ID, "compat");

    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper) {
        helper.registerTag(SMELTERY)
                .addToIndex()
                .item(TinkerSmeltery.smelteryController.asItem(), true, false)
                .title("Smeltery")
                .description("Tinker's components used to smelting and casting")
                .register();

        helper.registerTag(COMPAT)
                .addToIndex()
                .item(TGItems.CRUSHED_RAW_COBALT.asItem(), true, false)
                .title("Compat")
                .description("Tinker's Gears from the Create")
                .register();

        helper.addToTag(SMELTERY)
                .add(TinkerSmeltery.searedMelter.getId())
                .add(TinkerSmeltery.smelteryController.getId())
                .add(TinkerSmeltery.searedTable.getId());


        helper.addToTag(COMPAT)
                .add(TGBlocks.TINKER_DRILL.getId())
                .add(TGBlocks.TINKER_SILKTOUCH_DRILL.getId())
                .add(TGBlocks.TINKER_SPOUT.getId())
                .add(TGBlocks.TINKER_FAN.getId());

        TinkersGears.LOGGER.info("Ponder tags initialized");
    }
}
