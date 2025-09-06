package ho.artisan.tgears.datagen.provider.tconstruct;

import ho.artisan.tgears.index.TGModifiers;
import ho.artisan.tgears.index.TGearMaterialIds;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.tools.data.material.MaterialTraitsDataProvider;

public class TGMaterialTraitsDataProvider extends MaterialTraitsDataProvider {
    public TGMaterialTraitsDataProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    public String getName() {
        return "TGear's Material Traits";
    }

    @Override
    protected void addMaterialTraits() {

        addDefaultTraits(TGearMaterialIds.AndesiteAlloy, TGModifiers.Ids.TOPNOTCH);

    }
}
