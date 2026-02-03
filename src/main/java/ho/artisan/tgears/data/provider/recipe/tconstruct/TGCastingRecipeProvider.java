package ho.artisan.tgears.data.provider.recipe.tconstruct;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import ho.artisan.tgears.data.provider.recipe.TGRecipeProvider;
import ho.artisan.tgears.index.*;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.material.MaterialFluidRecipeBuilder;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import java.util.function.Consumer;

public final class TGCastingRecipeProvider extends TGRecipeProvider {

    public TGCastingRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public String getType() {
        return "casting";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        casingRecipes(consumer);
        foodRecipes(consumer);
        drillRecipes(consumer);
        metalRecipes(consumer);

        ItemCastingRecipeBuilder.tableRecipe(TGItems.SOUL_CARDBOARD)
                .setFluidAndTime(TinkerFluids.liquidSoul, FluidValues.BOTTLE)
                .setCast(AllItems.CARDBOARD, true)
                .save(consumer, location("soul_cardboard"));
    }

    private void metalRecipes(Consumer<FinishedRecipe> consumer) {
        ItemCastingRecipeBuilder.tableRecipe(TGTagKeys.Items.COBALT_PLATE)
                .setFluidAndTime(TinkerFluids.moltenCobalt, FluidValues.INGOT)
                .setCast(TinkerSmeltery.plateCast.getMultiUseTag(), false)
                .save(consumer, location("cobalt/plate_gold_cast"));

        ItemCastingRecipeBuilder.tableRecipe(TGTagKeys.Items.COBALT_PLATE)
                .setFluidAndTime(TinkerFluids.moltenCobalt, FluidValues.INGOT)
                .setCast(TinkerSmeltery.plateCast.getSingleUseTag(), true)
                .save(consumer, location("cobalt/plate_sand_cast"));

        ItemCastingRecipeBuilder.tableRecipe(TGTagKeys.Items.ROSE_GOLD_PLATE)
                .setFluidAndTime(TinkerFluids.moltenRoseGold, FluidValues.INGOT)
                .setCast(TinkerSmeltery.plateCast.getMultiUseTag(), false)
                .save(consumer, location("rose_gold/plate_gold_cast"));

        ItemCastingRecipeBuilder.tableRecipe(TGTagKeys.Items.ROSE_GOLD_PLATE)
                .setFluidAndTime(TinkerFluids.moltenRoseGold, FluidValues.INGOT)
                .setCast(TinkerSmeltery.plateCast.getSingleUseTag(), true)
                .save(consumer, location("rose_gold/plate_sand_cast"));
    }

    private void casingRecipes(Consumer<FinishedRecipe> consumer) {
        ItemCastingRecipeBuilder.basinRecipe(AllBlocks.BRASS_CASING)
                .setFluidAndTime(TinkerFluids.moltenBrass, FluidValues.INGOT)
                .setCast(AllTags.AllItemTags.STRIPPED_LOGS.tag, true)
                .save(consumer, location("brass_casing"));

        ItemCastingRecipeBuilder.basinRecipe(AllBlocks.COPPER_CASING)
                .setFluidAndTime(TinkerFluids.moltenCopper, FluidValues.INGOT)
                .setCast(AllTags.AllItemTags.STRIPPED_LOGS.tag, true)
                .save(consumer, location("copper_casing"));

        ItemCastingRecipeBuilder.basinRecipe(AllBlocks.RAILWAY_CASING)
                .setFluidAndTime(TinkerFluids.moltenObsidian, FluidValues.BOTTLE)
                .setCast(AllBlocks.BRASS_CASING, true)
                .save(consumer, location("railway_casing"));

        ItemCastingRecipeBuilder.basinRecipe(TGBlocks.COBALT_CASING)
                .setFluidAndTime(TinkerFluids.moltenCobalt, FluidValues.INGOT)
                .setCast(TinkerSmeltery.searedStone, true)
                .save(consumer, location("cobalt_casing"));

        ItemCastingRecipeBuilder.basinRecipe(TGBlocks.TINKER_CASING)
                .setFluidAndTime(TinkerFluids.moltenBrass, FluidValues.INGOT)
                .setCast(TinkerSmeltery.scorchedStone, true)
                .save(consumer, location("tinker_casing"));
    }

    private void drillRecipes(Consumer<FinishedRecipe> consumer) {
        ItemCastingRecipeBuilder.tableRecipe(TGBlocks.TINKER_SILKTOUCH_DRILL)
                .setFluidAndTime(TinkerFluids.moltenRoseGold, FluidValues.INGOT * 4)
                .setCast(TGBlocks.TINKER_DRILL, true)
                .save(consumer, location("silktouch_drill"));
    }

    private void foodRecipes(Consumer<FinishedRecipe> consumer) {
        ItemCastingRecipeBuilder.tableRecipe(TGItems.BLAZING_CHOCOLATE_BERRIES)
                .setFluidAndTime(new FluidStack(TGFluids.BLAZING_CHOCOLATE.getSource(), FluidValues.BOTTLE))
                .setCast(Items.SWEET_BERRIES, true)
                .save(consumer, location("blazing_chocolate_berries"));

        ItemCastingRecipeBuilder.tableRecipe(AllItems.BLAZE_CAKE)
                .setFluidAndTime(new FluidStack(Fluids.LAVA, FluidValues.BOTTLE))
                .setCast(AllItems.BLAZE_CAKE_BASE, true)
                .save(consumer, location("blaze_cake"));

        ItemCastingRecipeBuilder.tableRecipe(AllItems.CHOCOLATE_BERRIES)
                .setFluid(TGTagKeys.Fluids.CHOCOLATE, FluidValues.BOTTLE)
                .setCast(Items.SWEET_BERRIES, true)
                .setCoolingTime(52)
                .save(consumer, location("chocolate_berries"));

        ItemCastingRecipeBuilder.tableRecipe(AllItems.HONEYED_APPLE)
                .setFluid(TGTagKeys.Fluids.HONEY, FluidValues.BOTTLE)
                .setCast(Items.APPLE, true)
                .setCoolingTime(52)
                .save(consumer, location("honeyed_apple"));

        MaterialFluidRecipeBuilder.material(TGMaterials.Ids.CHOCOLATE)
                .setFluid(TGTagKeys.Fluids.CHOCOLATE, FluidValues.BOTTLE)
                .setTemperature(400)
                .save(consumer, location("chocolate"));

        MaterialFluidRecipeBuilder.material(TGMaterials.Ids.BLAZING_CHOCOLATE)
                .setFluid(TGTagKeys.Fluids.BLAZING_CHOCOLATE, FluidValues.BOTTLE)
                .setTemperature(400)
                .save(consumer, location("blaze_chocolate"));

    }
}
