package ho.artisan.tgears.datagen.provider.create;

import com.simibubi.create.api.data.recipe.CompactingRecipeGen;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGFluids;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

import slimeknights.tconstruct.library.recipe.FluidValues;

/**
 * Create's own Data Generation for Compacting recipes
 * @see CompactingRecipeGen
 */
@SuppressWarnings("unused")
public final class TGearCompactingRecipe extends CompactingRecipeGen {

    public TGearCompactingRecipe(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
    }

    GeneratedRecipe

            BLAZING_CHOCOLATE = create("blazing_chocolate", b -> b.require(Items.SNOWBALL)
            .require(TGFluids.BLAZING_CHOCOLATE.get(), FluidValues.BRICK)
            .output(TGItems.BAR_OF_BLAZING_CHOCOLATE, 1));


}