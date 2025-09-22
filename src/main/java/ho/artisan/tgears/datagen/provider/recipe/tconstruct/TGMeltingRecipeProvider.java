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
import slimeknights.tconstruct.library.recipe.melting.IMeltingContainer;
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
        this.crushedOreRecipes(consumer);
        this.luzziumRecipes(consumer);
    }

    private void luzziumRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "melting/luzzium/";

        /*
        MeltingRecipeBuilder.melting(
                Ingredient.of(TGBlocks.LUZZIUM_BLOCK.get()),
                TGFluids.MOLTEN_LUZZIUM.getSource(),
                FluidValues.METAL_BLOCK
        ).save(consumer, location(folder + "block"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(TGItems.LUZZIUM_INGOT.get()),
                TGFluids.MOLTEN_LUZZIUM.getSource(),
                FluidValues.INGOT
        ).save(consumer, location(folder + "ingot"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(TGItems.LUZZIUM_NUGGET.get()),
                TGFluids.MOLTEN_LUZZIUM.getSource(),
                FluidValues.NUGGET
        ).save(consumer, location(folder + "nugget"));

         */
    }

    private void crushedOreRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "melting/crushed_ore/";

        crushedOreRecipeBuilder(
                TGItems.CRUSHED_RAW_COBALT.get(),
                TinkerFluids.moltenCobalt.get(),
                120,
                TinkerFluids.moltenDiamond.get(),
                25
        ).setOre(IMeltingContainer.OreRateType.METAL, IMeltingContainer.OreRateType.GEM)
        .save(consumer, location(folder + "cobalt"));

        crushedOreRecipeBuilder(
                AllItems.CRUSHED_COPPER.get(),
                TinkerFluids.moltenCopper.get(),
                120,
                TinkerFluids.moltenGold.get(),
                60
        ).save(consumer, location(folder + "copper"));

        crushedOreRecipeBuilder(
                AllItems.CRUSHED_IRON,
                TinkerFluids.moltenIron.get(),
                TinkerFluids.moltenSteel.get()
        ).save(consumer, location(folder + "iron"));

        crushedOreRecipeBuilder(
                AllItems.CRUSHED_GOLD,
                TinkerFluids.moltenGold.get(),
                TinkerFluids.moltenCobalt.get()
        ).save(consumer, location(folder + "gold"));
    }

    public MeltingRecipeBuilder crushedOreRecipeBuilder(ItemLike crushedOre, Fluid moltenFluid, int amount) {
        return MeltingRecipeBuilder.melting(
                Ingredient.of(crushedOre),
                new FluidStack(moltenFluid, amount),
                1.0F
        ).setOre(IMeltingContainer.OreRateType.METAL, IMeltingContainer.OreRateType.METAL);
    }

    public MeltingRecipeBuilder crushedOreRecipeBuilder(ItemLike crushedOre, Fluid moltenFluid, int amount, Fluid byproductFluid, int byproductAmount) {
        return crushedOreRecipeBuilder(crushedOre, moltenFluid, amount).addByproduct(new FluidStack(byproductFluid, byproductAmount));
    }

    public MeltingRecipeBuilder crushedOreRecipeBuilder(ItemLike crushedOre, Fluid moltenFluid, Fluid byproductFluid) {
        return crushedOreRecipeBuilder(crushedOre, moltenFluid, 120, byproductFluid, 120);
    }

}