package ho.artisan.tgears.datagen.provider.create;

import com.simibubi.create.api.data.recipe.MixingRecipeGen;


import com.simibubi.create.content.processing.recipe.HeatCondition;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGFluids;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;

import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.FluidValues;

/**
 * Create's own Data Generation for Mixing recipes
 * @see MixingRecipeGen
 */
@SuppressWarnings("unused")
public final class TGearMixingRecipe extends MixingRecipeGen {

    public TGearMixingRecipe(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
    }

    GeneratedRecipe

            TEMP_LAVA = create("blazing_chocolate", b -> b.require(TinkerFluids.blazingBlood.get(), FluidValues.BRICK)
            .require(FluidTags.create(new ResourceLocation("forge", "chocolate")), FluidValues.BRICK)
            .output(TGFluids.BLAZING_CHOCOLATE.get(),  FluidValues.BRICK * 2)
            .requiresHeat(HeatCondition.SUPERHEATED));

}