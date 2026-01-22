package ho.artisan.tgears.data.provider.tinker;

import ho.artisan.tgears.index.TGMaterials;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;

public final class TGMaterialRenderInfoProvider extends AbstractMaterialRenderInfoProvider {
    public TGMaterialRenderInfoProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void addMaterialRenderInfo() {
        buildRenderInfo(TGMaterials.Ids.ANDESITE_ALLOY).color(0xB3C9BC).fallbacks("metal");
        buildRenderInfo(TGMaterials.Ids.BRASS).color(0xF2D16A).fallbacks("metal");

        buildRenderInfo(TGMaterials.Ids.CARDBOARD).color(0xEDC090);
        buildRenderInfo(TGMaterials.Ids.ROSE_QUARTZ).color(0xF97E8A);
    }

    @Override
    public String getName() {
        return "TG Material Render Info";
    }
}
