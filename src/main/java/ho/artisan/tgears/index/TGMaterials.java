package ho.artisan.tgears.index;

import ho.artisan.tgears.TinkersGears;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TGMaterials {

    public static final class Ids {
        public static final MaterialId ANDESITE_ALLOY = id("andesite_alloy");
        public static final MaterialId BRASS = id("brass");
        public static final MaterialId CARDBOARD = id("cardboard");
        public static final MaterialId ROSE_QUARTZ = id("rose_quartz");

        public static final MaterialId CHOCOLATE = id("chocolate");
        public static final MaterialId BLAZING_CHOCOLATE = id("blazing_chocolate");

        private static MaterialId id(String name) {
            return new MaterialId(TinkersGears.MOD_ID, name);
        }
    }
}
