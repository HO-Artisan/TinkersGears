package ho.artisan.tgears.datagen.provider.recipe.create;

import com.simibubi.create.api.data.recipe.WashingRecipeGen;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import slimeknights.tconstruct.shared.TinkerMaterials;

public final class TGSplashingProvider extends WashingRecipeGen {
    public TGSplashingProvider(PackOutput output) {
        super(output, TinkersGears.MOD_ID);

        rawOreRecipes();
    }

    private void rawOreRecipes() {
        create("crushed_raw_cobalt", b -> b.require(TGItems.CRUSHED_RAW_COBALT)
                .output(TinkerMaterials.cobalt.getNugget(), 9)
                .output(0.25F, Items.NETHERITE_SCRAP));
    }
}
