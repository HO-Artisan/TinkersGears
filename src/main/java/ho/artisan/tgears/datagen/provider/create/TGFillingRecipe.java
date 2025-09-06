package ho.artisan.tgears.datagen.provider.create;

import com.simibubi.create.api.data.recipe.FillingRecipeGen;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;

import slimeknights.tconstruct.library.recipe.FluidValues;

/**
 * Create's own Data Generation for Filling recipes
 * @see FillingRecipeGen
 */
@SuppressWarnings("unused")
public final class TGFillingRecipe extends FillingRecipeGen {

    public TGFillingRecipe(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
    }

    GeneratedRecipe

            BRASS_HAND = create("brass_hand", b -> b.require(FluidTags.create(new ResourceLocation("forge", "molten_brass")), FluidValues.INGOT * 4)
            .require(TGItems.HAND_CAST_WITH_PART)
            .output(TGItems.HAND_CAST_WITH_BRASS_HAND)),

            PROPELLER = create("propeller", b -> b.require(FluidTags.create(new ResourceLocation("forge", "molten_iron")), FluidValues.INGOT * 4)
            .require(TGItems.PROPELLER_CAST_WITH_PART)
            .output(TGItems.PROPELLER_CAST_WITH_PROPELLER_)),

            WHISK = create("whisk", b -> b.require(FluidTags.create(new ResourceLocation("forge", "molten_iron")), FluidValues.INGOT * 5)
            .require(TGItems.WHISK_CAST_WITH_PART)
            .output(TGItems.WHISK_CAST_WITH_WHISK));

}