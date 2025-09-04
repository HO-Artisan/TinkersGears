package ho.artisan.tgears.common.recipes.Create;

import com.simibubi.create.AllFluids;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags.AllItemTags;
import com.simibubi.create.Create;
import com.simibubi.create.api.data.recipe.CompactingRecipeGen;
import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import com.simibubi.create.foundation.data.recipe.Mods;


import com.simibubi.create.api.data.recipe.SequencedAssemblyRecipeGen;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.recipe.BlockTagIngredient;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGFluids;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;

import net.minecraftforge.common.Tags;
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