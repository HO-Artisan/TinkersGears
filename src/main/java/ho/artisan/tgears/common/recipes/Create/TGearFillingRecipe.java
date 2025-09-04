package ho.artisan.tgears.common.recipes.Create;

import com.simibubi.create.AllFluids;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.AllTags.AllFluidTags;
import com.simibubi.create.Create;
import com.simibubi.create.api.data.recipe.FillingRecipeGen;
import com.simibubi.create.api.data.recipe.SequencedAssemblyRecipeGen;
import com.simibubi.create.content.fluids.potion.PotionFluidHandler;
import com.simibubi.create.foundation.data.recipe.Mods;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.material.Fluids;

import net.minecraftforge.common.Tags;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.FluidValues;

/**
 * Create's own Data Generation for Filling recipes
 * @see FillingRecipeGen
 */
@SuppressWarnings("unused")
public final class TGearFillingRecipe extends FillingRecipeGen {

    public TGearFillingRecipe(PackOutput output) {
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