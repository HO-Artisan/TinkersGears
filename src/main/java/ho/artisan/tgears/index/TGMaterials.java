package ho.artisan.tgears.index;

import ho.artisan.tgears.TinkersGears;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TGMaterials {

    public static final class Ids {
        public static final MaterialId ANDESITE_ALLOY = id("andesite_alloy");

        private static MaterialId id(String name) {
            return new MaterialId(TinkersGears.MOD_ID, name);
        }
    }
}
