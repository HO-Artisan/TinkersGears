package ho.artisan.tgears.datagen.provider.recipe.tconstruct;

import com.simibubi.create.AllItems;
import ho.artisan.tgears.datagen.provider.recipe.TGBaseRecipeProvider;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;

import java.util.function.Consumer;

public final class TGMeltingRecipeProvider extends TGBaseRecipeProvider implements ISmelteryRecipeHelper {

    public TGMeltingRecipeProvider(PackOutput generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "TGears Melting Recipes";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        crushedOreRecipes(consumer);
    }

    private void crushedOreRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "melting/crushed_ore/";

        crushedOreRecipeBuilder(
                TGItems.CRUSHED_RAW_COBALT.get(),
                TinkerFluids.moltenCobalt.get(),
                950,
                57
        ).save(consumer, location(folder + "cobalt"));

        crushedOreRecipeBuilder(
                AllItems.CRUSHED_COPPER.get(),
                TinkerFluids.moltenCopper.get(),
                500,
                50,
                TinkerFluids.moltenGold.get(),
                30
        ).save(consumer, location(folder + "copper"));

        crushedOreRecipeBuilder(
                AllItems.CRUSHED_IRON.get(),
                TinkerFluids.moltenIron.get(),
                800,
                60,
                TinkerFluids.moltenSteel.get(),
                30
        ).save(consumer, location(folder + "iron"));


        crushedOreRecipeBuilder(
                AllItems.CRUSHED_GOLD.get(),
                TinkerFluids.moltenGold.get(),
                700,
                59,
                TinkerFluids.moltenCobalt.get(),
                30
        ).save(consumer, location(folder + "gold"));
    }

    public MeltingRecipeBuilder crushedOreRecipeBuilder(ItemLike crushedOre, Fluid moltenFluid, int temperature, int time) {
        return MeltingRecipeBuilder.melting(
                Ingredient.of(crushedOre),
                new FluidStack(moltenFluid, FluidValues.INGOT + FluidValues.NUGGET * 6),
                temperature,
                time
        );
    }

    public MeltingRecipeBuilder crushedOreRecipeBuilder(ItemLike crushedOre, Fluid moltenFluid, int temperature, int time, Fluid byproductFluid, int byproductAmount) {
        return MeltingRecipeBuilder.melting(
                Ingredient.of(crushedOre),
                new FluidStack(moltenFluid, FluidValues.INGOT + FluidValues.NUGGET * 6),
                temperature,
                time
        ).addByproduct(new FluidStack(byproductFluid, byproductAmount));
    }
}