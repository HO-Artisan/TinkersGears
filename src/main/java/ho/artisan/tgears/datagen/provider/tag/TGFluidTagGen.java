package ho.artisan.tgears.datagen.provider.tag;

import com.tterrag.registrate.providers.RegistrateTagsProvider;
import ho.artisan.tgears.index.TGFluids;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.world.level.material.Fluid;

public class TGFluidTagGen extends TGTagGen<Fluid> {
    public TGFluidTagGen(RegistrateTagsProvider.IntrinsicImpl<Fluid> provIn) {
        super(provIn, fluid -> fluid.builtInRegistryHolder().key());

        prov.tag(TGTagKeys.Fluids.BLAZING_CHOCOLATE)
                .add(
                        TGFluids.BLAZING_CHOCOLATE.getSource(),
                        TGFluids.BLAZING_CHOCOLATE.get()
                );
    }
}
