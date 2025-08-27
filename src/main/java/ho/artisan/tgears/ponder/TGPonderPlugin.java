package ho.artisan.tgears.ponder;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGPonderScenes;
import ho.artisan.tgears.index.TGPonderTags;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class TGPonderPlugin implements PonderPlugin {
    @Override
    public String getModId() {
        return TinkersGears.MOD_ID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        TGPonderScenes.register(helper);
    }

    @Override
    public void registerTags(PonderTagRegistrationHelper<ResourceLocation> helper) {
        TGPonderTags.register(helper);
    }
}
