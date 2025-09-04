package ho.artisan.tgears.common.register;

import ho.artisan.tgears.TinkersGears;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

import static slimeknights.tconstruct.library.materials.definition.MaterialVariantId.create;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TGearMaterialIds {
  public static final MaterialId AndesiteAlloy = id("andesite_alloy");

  /**
   * Creates a new material ID
   * @param name  ID name
   * @return  Material ID object
   */
  private static MaterialId id(String name) {
    return new MaterialId(TinkersGears.MOD_ID, name);
  }
}
