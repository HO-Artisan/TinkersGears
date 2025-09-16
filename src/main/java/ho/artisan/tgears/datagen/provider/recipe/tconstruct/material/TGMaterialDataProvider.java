package ho.artisan.tgears.datagen.provider.recipe.tconstruct.material;

import ho.artisan.tgears.index.TGMaterials;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.tools.data.material.MaterialDataProvider;

public final class TGMaterialDataProvider extends MaterialDataProvider {
    public TGMaterialDataProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public String getName() {
        return "TGears Materials";
    }

    @Override
    protected void addMaterials() {
        // tier 1
        addMaterial(TGMaterials.Ids.ANDESITE_ALLOY, 2, ORDER_GENERAL, true);



        // tier 4
        addMaterial(TGMaterials.Ids.LUZZIUM, 4, ORDER_GENERAL, false);
    }
}
