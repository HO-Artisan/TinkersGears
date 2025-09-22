package ho.artisan.tgears.datagen.provider.recipe.tconstruct.material;

import ho.artisan.tgears.index.TGMaterials;
import ho.artisan.tgears.index.TGModifiers;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.tools.data.material.MaterialTraitsDataProvider;

public final class TGMaterialTraitsDataProvider extends MaterialTraitsDataProvider {
    public TGMaterialTraitsDataProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    public String getName() {
        return "TGears Material Traits";
    }

    @Override
    protected void addMaterialTraits() {
        addDefaultTraits(TGMaterials.Ids.ANDESITE_ALLOY, TGModifiers.Ids.TOPNOTCH);

        /*
        addDefaultTraits(TGMaterials.Ids.LUZZIUM, TGModifiers.Ids.LIGHTWEIGHT);

        addTraits(
                TGMaterials.Ids.LUZZIUM,
                ARMOR,
                TGModifiers.Ids.ADRENALINE
        );
         */
    }
}
