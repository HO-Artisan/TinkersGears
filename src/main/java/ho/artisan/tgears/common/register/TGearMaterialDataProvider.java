package ho.artisan.tgears.register;

import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.tools.data.material.MaterialDataProvider;

public class TGearMaterialDataProvider extends MaterialDataProvider {
  public TGearMaterialDataProvider(PackOutput packOutput) {
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
