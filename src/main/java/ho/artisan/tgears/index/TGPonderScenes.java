package ho.artisan.tgears.index;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.client.ponder.scene.*;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

public final class TGPonderScenes {
    private TGPonderScenes() {
    }

    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(TinkerSmeltery.searedMelter.getId())
            .addStoryBoard("basic_melter", MelterScene::basic, TGPonderTags.SMELTERY)
            .addStoryBoard("basic_melter_burner", MelterScene::burner, TGPonderTags.SMELTERY)
            .addStoryBoard("basic_melter_casting", MelterScene::casting, TGPonderTags.SMELTERY)
            .addStoryBoard("basic_melter_pipe", MelterScene::pipe, TGPonderTags.SMELTERY)
            .addStoryBoard("basic_melter_spout", MelterScene::spout, TGPonderTags.SMELTERY);
        
        helper.forComponents(TinkerSmeltery.smelteryController.getId(), TinkerSmeltery.searedDrain.getId(),
            TinkerSmeltery.searedDuct.getId(), TinkerSmeltery.searedChute.getId())
            .addStoryBoard("smeltery_new", SmelteryScene::newBasic, TGPonderTags.SMELTERY)
            //.addStoryBoard("smeltery", SmelteryScene::basic, TGPonderTags.SMELTERY)
            .addStoryBoard("smeltery_mini", SmelteryScene::mini, TGPonderTags.SMELTERY)
            .addStoryBoard("smeltery_transfer", SmelteryScene::transfer, TGPonderTags.SMELTERY);
        
        helper.forComponents(TinkerSmeltery.foundryController.getId(), TinkerSmeltery.scorchedDrain.getId(),
            TinkerSmeltery.scorchedDuct.getId(), TinkerSmeltery.scorchedChute.getId())
            .addStoryBoard("foundry_new", ScorchedPonder::basic, TGPonderTags.SMELTERY);
        
        helper.forComponents(TinkerSmeltery.searedTable.getId(), TinkerSmeltery.scorchedTable.getId(),
            TinkerSmeltery.searedBasin.getId(), TinkerSmeltery.scorchedBasin.getId())
            .addStoryBoard("table_cooling", CastingScene::table, TGPonderTags.SMELTERY);


        helper.forComponents(TGBlocks.TINKER_FAN.getId())
            .addStoryBoard("tinker_fan", TinkerMachineScene::fan, TGPonderTags.COMPAT);
        helper.forComponents(TGBlocks.TINKER_SPOUT.getId())
            .addStoryBoard("tinker_spout", TinkerMachineScene::spout, TGPonderTags.COMPAT);
        helper.forComponents(TGBlocks.TINKER_DRILL.getId(), TGBlocks.TINKER_SILKTOUCH_DRILL.getId())
            .addStoryBoard("tinker_drill", TinkerMachineScene::drill, TGPonderTags.COMPAT);
        helper.forComponents(TGBlocks.TINKER_FORTUNE_DRILL.getId())
            .addStoryBoard("tinker_fortune_drill", TinkerMachineScene::fortuneDrill, TGPonderTags.COMPAT);

        helper.forComponents(TGBlocks.TINKER_CRUSHING_WHEEL.getId())
            .addStoryBoard("tinker_crushing_wheels", TinkerMachineScene::crushing);

        TinkersGears.LOGGER.info("Ponder scenes initialized");
    }
}
