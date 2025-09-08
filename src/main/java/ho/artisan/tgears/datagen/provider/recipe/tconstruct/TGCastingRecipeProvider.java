package ho.artisan.tgears.datagen.provider.recipe.tconstruct;

import com.simibubi.create.AllItems;
import fr.lucreeper74.createmetallurgy.registries.CMItems;
import ho.artisan.tgears.datagen.provider.recipe.TGBaseRecipeProvider;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;

import java.util.function.Consumer;

public final class TGCastingRecipeProvider extends TGBaseRecipeProvider {

    public TGCastingRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    public String getName() {
        return "TGears Casting Recipes";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "casting/";

        ItemCastingRecipeBuilder.tableRecipe(TGItems.HAND_CAST_WITH_BRASS_HAND)
                .setFluidAndTime(TinkerFluids.moltenBrass, FluidValues.INGOT * 4)
                .setCast(TGItems.HAND_CAST_WITH_PART, true)
                .setCoolingTime(82)
                .save(consumer, location(folder + "hand/brass"));

        ItemCastingRecipeBuilder.tableRecipe(TGItems.HAND_CAST_WITH_BRASS_HAND)
                .setFluidAndTime(TinkerFluids.moltenGold, FluidValues.INGOT)
                .setCast(AllItems.BRASS_HAND, true)
                .setCoolingTime(57)
                .save(consumer, location(folder + "hand/cast"));

        ItemCastingRecipeBuilder.tableRecipe(TGItems.PROPELLER_CAST_WITH_PROPELLER)
                .setFluidAndTime(TinkerFluids.moltenIron, FluidValues.INGOT * 4)
                .setCast(TGItems.PROPELLER_CAST_WITH_PART, true)
                .setCoolingTime(82)
                .save(consumer, location(folder + "propeller/iron"));

        ItemCastingRecipeBuilder.tableRecipe(TGItems.PROPELLER_CAST_WITH_PROPELLER)
                .setFluidAndTime(TinkerFluids.moltenGold, FluidValues.INGOT)
                .setCast(AllItems.PROPELLER, true)
                .setCoolingTime(57)
                .save(consumer, location(folder + "propeller/cast"));

        ItemCastingRecipeBuilder.tableRecipe(TGItems.WHISK_CAST_WITH_WHISK)
                .setFluidAndTime(TinkerFluids.moltenIron, FluidValues.INGOT * 5)
                .setCast(TGItems.WHISK_CAST_WITH_PART, true)
                .setCoolingTime(82)
                .save(consumer, location(folder + "whisk/iron"));

        ItemCastingRecipeBuilder.tableRecipe(TGItems.WHISK_CAST_WITH_WHISK)
                .setFluidAndTime(TinkerFluids.moltenGold, FluidValues.INGOT)
                .setCast(AllItems.WHISK, true)
                .setCoolingTime(82)
                .save(consumer, location(folder + "whisk/cast"));

        Consumer<Consumer<FinishedRecipe>> sturdyWhiskRecipe = c ->
                ItemCastingRecipeBuilder.tableRecipe(TGItems.WHISK_CAST_WITH_STURDY_WHISK)
                        .setFluidAndTime(TinkerFluids.moltenGold, FluidValues.INGOT)
                        .setCast(CMItems.STURDY_WHISK, true)
                        .setCoolingTime(82)
                        .save(c);

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("createmetallurgy"))
                .addRecipe(sturdyWhiskRecipe)
                .build(consumer, location(folder + "whisk/sturdy"));

        ItemCastingRecipeBuilder.tableRecipe(AllItems.BLAZE_CAKE)
                .setFluidAndTime(new FluidStack(Fluids.LAVA, FluidValues.BRICK))
                .setCast(AllItems.BLAZE_CAKE_BASE, true)
                .setCoolingTime(82)
                .save(consumer, location(folder + "blaze_cake"));
    }
}
