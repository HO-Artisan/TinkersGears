package ho.artisan.tgears.datagen.provider.recipe.tconstruct;

import com.simibubi.create.AllItems;
import fr.lucreeper74.createmetallurgy.registries.CMItems;
import ho.artisan.tgears.datagen.provider.recipe.TGBaseRecipeProvider;
import ho.artisan.tgears.index.TGItems;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
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
                .setFluid(TGTagKeys.Fluids.MOLTEN_BRASS, FluidValues.INGOT * 4)
                .setCast(TGItems.HAND_CAST_WITH_PART, true)
                .setCoolingTime(82)
                .save(consumer, location(folder + "hand/brass"));

        ItemCastingRecipeBuilder.tableRecipe(TGItems.HAND_CAST_WITH_CHOCOLATE_HAND)
                .setFluid(TGTagKeys.Fluids.CHOCOLATE,  FluidValues.BOTTLE * 2)
                .setCast(TGItems.HAND_CAST_WITH_PART, true)
                .setCoolingTime(82)
                .save(consumer, location(folder + "hand/chocolate"));

        ItemCastingRecipeBuilder.tableRecipe(TGItems.HAND_CAST_WITH_BLAZING_CHOCOLATE_HAND)
                .setFluid(TGTagKeys.Fluids.BLAZING_CHOCOLATE,  FluidValues.BOTTLE * 2)
                .setCast(TGItems.HAND_CAST_WITH_PART, true)
                .setCoolingTime(82)
                .save(consumer, location(folder + "hand/blazing_chocolate"));

        ItemCastingRecipeBuilder.tableRecipe(AllItems.CHOCOLATE_BERRIES)
                .setFluid(TGTagKeys.Fluids.CHOCOLATE,  FluidValues.BOTTLE)
                .setCast(Items.SWEET_BERRIES, true)
                .setCoolingTime(57)
                .save(consumer, location(folder + "chocolate_berries"));

        ItemCastingRecipeBuilder.tableRecipe(TGItems.BLAZING_CHOCOLATE_BERRIES)
                .setFluid(TGTagKeys.Fluids.BLAZING_CHOCOLATE,  FluidValues.BOTTLE)
                .setCast(Items.SWEET_BERRIES, true)
                .setCoolingTime(57)
                .save(consumer, location(folder + "blazing_chocolate_berries"));

        ItemCastingRecipeBuilder.tableRecipe(AllItems.HONEYED_APPLE)
                .setFluid(TGTagKeys.Fluids.HONEY,  FluidValues.BOTTLE)
                .setCast(Items.SWEET_BERRIES, true)
                .setCoolingTime(57)
                .save(consumer, location(folder + "honeyed_apple"));

        ItemCastingRecipeBuilder.tableRecipe(TGItems.HAND_CAST_WITH_BRASS_HAND)
                .setFluid(TinkerFluids.moltenGold.get(), FluidValues.INGOT)
                .setCast(AllItems.BRASS_HAND, true)
                .setCoolingTime(57)
                .save(consumer, location(folder + "hand/cast"));

        ItemCastingRecipeBuilder.tableRecipe(TGItems.PROPELLER_CAST_WITH_PROPELLER)
                .setFluid(TinkerFluids.moltenIron.get(), FluidValues.INGOT * 4)
                .setCast(TGItems.PROPELLER_CAST_WITH_PART, true)
                .setCoolingTime(82)
                .save(consumer, location(folder + "propeller/iron"));

        ItemCastingRecipeBuilder.tableRecipe(TGItems.PROPELLER_CAST_WITH_PROPELLER)
                .setFluid(TinkerFluids.moltenGold.get(), FluidValues.INGOT)
                .setCast(AllItems.PROPELLER, true)
                .setCoolingTime(57)
                .save(consumer, location(folder + "propeller/cast"));

        ItemCastingRecipeBuilder.tableRecipe(TGItems.WHISK_CAST_WITH_WHISK)
                .setFluid(TinkerFluids.moltenIron.get(), FluidValues.INGOT * 5)
                .setCast(TGItems.WHISK_CAST_WITH_PART, true)
                .setCoolingTime(82)
                .save(consumer, location(folder + "whisk/iron"));

        ItemCastingRecipeBuilder.tableRecipe(TGItems.WHISK_CAST_WITH_WHISK)
                .setFluid(TinkerFluids.moltenGold.get(), FluidValues.INGOT)
                .setCast(AllItems.WHISK, true)
                .setCoolingTime(82)
                .save(consumer, location(folder + "whisk/cast"));

        Consumer<Consumer<FinishedRecipe>> sturdyWhiskRecipe = c ->
                ItemCastingRecipeBuilder.tableRecipe(TGItems.WHISK_CAST_WITH_STURDY_WHISK)
                        .setFluid(TinkerFluids.moltenGold.get(), FluidValues.INGOT)
                        .setCast(CMItems.STURDY_WHISK, true)
                        .setCoolingTime(82)
                        .save(c);

        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("createmetallurgy"))
                .addRecipe(sturdyWhiskRecipe)
                .build(consumer, location(folder + "whisk/sturdy"));

        ItemCastingRecipeBuilder.tableRecipe(AllItems.BLAZE_CAKE)
                .setFluid(Fluids.LAVA, FluidValues.BRICK)
                .setCast(AllItems.BLAZE_CAKE_BASE, true)
                .setCoolingTime(82)
                .save(consumer, location(folder + "blaze_cake"));
    }
}
