package ho.artisan.tgears.datagen.provider.material;

import ho.artisan.tgears.index.TGMaterialIds;
import ho.artisan.tgears.index.TGModifiers;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.tools.data.material.MaterialTraitsDataProvider;

public class TGearMaterialTraitsDataProvider extends MaterialTraitsDataProvider {
    public TGearMaterialTraitsDataProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    public String getName() {
        return "TGear's Material Traits";
    }

    @Override
    protected void addMaterialTraits() {

        addDefaultTraits(TGMaterialIds.ANDESITE_ALLOY, TGModifiers.Ids.TOPNOTCH);

    }
}
