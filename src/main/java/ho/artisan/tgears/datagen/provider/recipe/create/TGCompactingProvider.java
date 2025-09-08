package ho.artisan.tgears.datagen.provider.recipe.create;

import com.simibubi.create.api.data.recipe.CompactingRecipeGen;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGFluids;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

import slimeknights.tconstruct.library.recipe.FluidValues;

public final class TGCompactingProvider extends CompactingRecipeGen {

    public TGCompactingProvider(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
        createRecipes();
    }

    private void createRecipes() {
        create("blazing_chocolate", b -> b.require(Items.SNOWBALL)
                .require(TGFluids.BLAZING_CHOCOLATE.get(), FluidValues.BRICK)
                .output(TGItems.BAR_OF_BLAZING_CHOCOLATE, 1));
    }
}