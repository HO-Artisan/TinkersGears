package ho.artisan.tgears.datagen.provider.tconstruct;

import ho.artisan.tgears.index.TGearMaterialIds;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.tools.data.material.MaterialDataProvider;

public class TGMaterialDataProvider extends MaterialDataProvider {
  public TGMaterialDataProvider(PackOutput packOutput) {
    super(packOutput);
  }

  @Override
  public String getName() {
    return "TGear's Materials";
  }

  @Override
  protected void addMaterials() {
    // tier 1
    addMaterial(TGearMaterialIds.AndesiteAlloy,  2, ORDER_GENERAL, true);

  }
}
