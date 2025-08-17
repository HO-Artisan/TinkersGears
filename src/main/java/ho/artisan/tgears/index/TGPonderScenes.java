package ho.artisan.tgears.index;

import ho.artisan.tgears.ponder.scene.MelterScene;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

public class TGPonderScenes {
    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(TinkerSmeltery.searedMelter.getId())
                .addStoryBoard("basic_melter", MelterScene::basic, TGPonderTags.SMELTERY)
                .addStoryBoard("basic_melter_casting", MelterScene::casting, TGPonderTags.SMELTERY)
                .addStoryBoard("basic_melter_pipe", MelterScene::pipe, TGPonderTags.SMELTERY);
    }
}
