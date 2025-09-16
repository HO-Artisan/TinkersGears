package ho.artisan.tgears.index;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.ponder.scene.CastingScene;
import ho.artisan.tgears.ponder.scene.MelterScene;
import ho.artisan.tgears.ponder.scene.SmelteryScene;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

public final class TGPonderScenes {
    private TGPonderScenes() {}

    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(TinkerSmeltery.searedMelter.getId())
                .addStoryBoard("basic_melter", MelterScene::basic, TGPonderTags.SMELTERY)
                .addStoryBoard("basic_melter_burner", MelterScene::burner, TGPonderTags.SMELTERY)
                .addStoryBoard("basic_melter_casting", MelterScene::casting, TGPonderTags.SMELTERY)
                .addStoryBoard("basic_melter_pipe", MelterScene::pipe, TGPonderTags.SMELTERY)
                .addStoryBoard("basic_melter_spout", MelterScene::spout, TGPonderTags.SMELTERY);
        helper.forComponents(TinkerSmeltery.smelteryController.getId())
                .addStoryBoard("smeltery", SmelteryScene::basic, TGPonderTags.SMELTERY)
                .addStoryBoard("smeltery_mini", SmelteryScene::mini,  TGPonderTags.SMELTERY)
                .addStoryBoard("smeltery_transfer", SmelteryScene::transfer, TGPonderTags.SMELTERY);
        helper.forComponents(TinkerSmeltery.searedTable.getId())
                .addStoryBoard("table_cooling", CastingScene::table, TGPonderTags.SMELTERY);

        TinkersGears.LOGGER.info("Ponder scenes initialized");
    }
}
