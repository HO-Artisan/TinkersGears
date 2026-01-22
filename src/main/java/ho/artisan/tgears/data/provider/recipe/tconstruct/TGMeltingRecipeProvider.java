package ho.artisan.tgears.data.provider.recipe.tconstruct;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.block.CopperBlockSet;
import ho.artisan.tgears.data.provider.recipe.TGRecipeProvider;
import ho.artisan.tgears.index.TGItems;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.recipe.melting.IMeltingContainer;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;

import java.util.function.Consumer;

public final class TGMeltingRecipeProvider extends TGRecipeProvider implements ISmelteryRecipeHelper {

    public TGMeltingRecipeProvider(PackOutput generator) {
        super(generator);
    }

    @Override
    public String getType() {
        return "melting";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        this.crushedOreRecipes(consumer);
        this.copperRecipes(consumer);
        this.ironRecipes(consumer);
        this.cobaltRecipes(consumer);
        this.miscRecipes(consumer);
        this.armorRecipes(consumer);
    }

    private void armorRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "armor/";

        MeltingRecipeBuilder.melting(
                Ingredient.of(AllItems.COPPER_DIVING_BOOTS),
                TinkerFluids.moltenCopper.get(),
                360
        ).save(consumer, location(folder + "copper_diving_boots"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(AllItems.COPPER_DIVING_HELMET),
                TinkerFluids.moltenCopper.get(),
                450
        ).save(consumer, location(folder + "copper_diving_helmet"));
    }

    private void miscRecipes(Consumer<FinishedRecipe> consumer) {
        MeltingRecipeBuilder.melting(
                Ingredient.of(AllItems.BRASS_HAND),
                TinkerFluids.moltenBrass.get(),
                360
        ).save(consumer, location("brass_hand"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(AllBlocks.COPPER_VALVE_HANDLE),
                TinkerFluids.moltenCopper.get(),
                270
        ).save(consumer, location("copper_valve_handle"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(AllBlocks.BLAZE_BURNER),
                TinkerFluids.moltenIron.get(),
                360
        ).addByproduct(
                new FluidStack(TinkerFluids.blazingBlood.get(), 100)
        ).save(consumer, location("blazing_burner"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(AllItems.EMPTY_BLAZE_BURNER),
                TinkerFluids.moltenIron.get(),
                360
        ).save(consumer, location("empty_burner"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(AllItems.CRAFTER_SLOT_COVER),
                TinkerFluids.moltenBrass.get(),
                30
        ).save(consumer, location("crafter_slot_cover"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(AllBlocks.FLUID_PIPE),
                TinkerFluids.moltenCopper.get(),
                30
        ).save(consumer, location("fluid_pipe"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(AllBlocks.FLUID_TANK),
                TinkerFluids.moltenCopper.get(),
                180
        ).save(consumer, location("fluid_tank"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(AllBlocks.PECULIAR_BELL),
                TinkerFluids.moltenBrass.get(),
                900
        ).save(consumer, location("peculiar_bell"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(AllItems.PROPELLER),
                TinkerFluids.moltenIron.get(),
                360
        ).save(consumer, location("propeller"));

        MeltingRecipeBuilder.melting(
                Ingredient.of(AllItems.WHISK),
                TinkerFluids.moltenIron.get(),
                450
        ).save(consumer, location("whisk"));
    }

    private void cobaltRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "cobalt/";
        MeltingRecipeBuilder.melting(
                Ingredient.of(TGTagKeys.Items.COBALT_PLATE),
                TinkerFluids.moltenCobalt.get(),
                90
        ).save(consumer, location(folder + "plate"));
    }

    private void ironRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "iron/";
        MeltingRecipeBuilder.melting(
                Ingredient.of(AllBlocks.INDUSTRIAL_IRON_BLOCK),
                TinkerFluids.moltenIron.get(),
                40
        ).save(consumer, location(folder + "industrial_iron_block"));
        MeltingRecipeBuilder.melting(
                Ingredient.of(AllBlocks.WEATHERED_IRON_BLOCK),
                TinkerFluids.moltenIron.get(),
                40
        ).save(consumer, location(folder + "weathered_iron_block"));
    }

    private void copperRecipes(Consumer<FinishedRecipe> consumer) {
        copperRecipe(consumer, AllBlocks.COPPER_TILES);
        copperRecipe(consumer, AllBlocks.COPPER_SHINGLES);
    }

    private void copperRecipe(Consumer<FinishedRecipe> consumer, CopperBlockSet set) {
        String folder = "copper/";

        for (WeatheringCopper.WeatherState value : WeatheringCopper.WeatherState.values()) {
            for (CopperBlockSet.Variant<?> variant : CopperBlockSet.DEFAULT_VARIANTS) {
                boolean[] bl = {true, false};
                for (boolean waxed : bl) {
                    String prefix = waxed ? "waxed_" : "";
                    int amount;
                    if (variant == CopperBlockSet.DEFAULT_VARIANTS[0]) {
                        amount = 40;
                    } else if (variant == CopperBlockSet.DEFAULT_VARIANTS[1]) {
                        amount = 20;
                    } else {
                        amount = 30;
                    }
                    MeltingRecipeBuilder.melting(
                            Ingredient.of(set.get(variant, value, waxed)),
                            TinkerFluids.moltenCopper.get(),
                            amount
                    ).save(consumer, location(folder + prefix + set.getName() + "_" + value.name().toLowerCase() + variant.getSuffix()));
                }
            }
        }
    }

    private void crushedOreRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "crushed_ore/";

        crushedRecipe(
                TGItems.CRUSHED_RAW_COBALT.get(),
                TinkerFluids.moltenCobalt.get(),
                120,
                TinkerFluids.moltenDiamond.get(),
                25
        ).setOre(IMeltingContainer.OreRateType.METAL, IMeltingContainer.OreRateType.GEM)
                .save(consumer, location(folder + "cobalt"));

        crushedRecipe(
                AllItems.CRUSHED_COPPER.get(),
                TinkerFluids.moltenCopper.get(),
                120,
                TinkerFluids.moltenGold.get(),
                60
        ).save(consumer, location(folder + "copper"));

        crushedRecipe(
                AllItems.CRUSHED_IRON,
                TinkerFluids.moltenIron.get(),
                TinkerFluids.moltenSteel.get()
        ).save(consumer, location(folder + "iron"));

        crushedRecipe(
                AllItems.CRUSHED_GOLD,
                TinkerFluids.moltenGold.get(),
                TinkerFluids.moltenCobalt.get()
        ).save(consumer, location(folder + "gold"));
    }

    public MeltingRecipeBuilder crushedRecipe(ItemLike crushedOre, Fluid moltenFluid, int amount) {
        return MeltingRecipeBuilder.melting(
                Ingredient.of(crushedOre),
                new FluidStack(moltenFluid, amount),
                1.0F
        ).setOre(IMeltingContainer.OreRateType.METAL, IMeltingContainer.OreRateType.METAL);
    }

    public MeltingRecipeBuilder crushedRecipe(ItemLike crushedOre, Fluid moltenFluid, int amount, Fluid byproductFluid, int byproductAmount) {
        return crushedRecipe(crushedOre, moltenFluid, amount).addByproduct(new FluidStack(byproductFluid, byproductAmount));
    }

    public MeltingRecipeBuilder crushedRecipe(ItemLike crushedOre, Fluid moltenFluid, Fluid byproductFluid) {
        return crushedRecipe(crushedOre, moltenFluid, 120, byproductFluid, 120);
    }

}