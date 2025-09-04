package ho.artisan.tgears.common.recipes.Create;

import com.simibubi.create.AllFluids;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags.AllFluidTags;
import com.simibubi.create.Create;
import com.simibubi.create.api.data.recipe.CompactingRecipeGen;

import com.simibubi.create.api.data.recipe.SequencedAssemblyRecipeGen;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGFluids;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;

import net.minecraftforge.common.Tags;
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