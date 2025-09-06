package ho.artisan.tgears.datagen.provider.tconstruct;

import com.simibubi.create.AllItems;
import fr.lucreeper74.createmetallurgy.registries.CMItems;
import ho.artisan.tgears.index.TGItems;
import ho.artisan.tgears.datagen.provider.TGearBaseRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import  net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.mantle.recipe.data.ICommonRecipeHelper;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.registration.GeodeItemObject;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.shared.block.SlimeType;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import java.util.function.Consumer;

public class TGearSmelteryRecipeProvider extends TGearBaseRecipeProvider implements ISmelteryRecipeHelper, ICommonRecipeHelper {

    public TGearSmelteryRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    public String getName() {
        return "TGear's Smeltery Recipes";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        this.addCraftingRecipes(consumer);
        this.addSmelteryRecipes(consumer);
        this.addFoundryRecipes(consumer);
        this.addTagRecipes(consumer);
        this.addMeltingRecipes(consumer);
        this.addCastingRecipes(consumer);
        this.addAlloyRecipes(consumer);
        this.addEntityMeltingRecipes(consumer);

        this.addCompatRecipes(consumer);
    }

    private void addCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TinkerSmeltery.copperCan, 3)
                .define('c', Tags.Items.INGOTS_COPPER)
                .pattern("c c")
                .pattern(" c ")
                .unlockedBy("has_item", has(Tags.Items.INGOTS_COPPER))
                .save(consumer, prefix(TinkerSmeltery.copperCan, "smeltery/"));
    }

    private void addSmelteryRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "smeltery/seared/";
        // grout crafting


    }

    private void addFoundryRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "smeltery/scorched/";
        // grout crafting


    }

    private void addCastingRecipes(Consumer<FinishedRecipe> consumer) {
        // Pure Fluid Recipes
        String folder = "smeltery/casting/";
        //cast
        ItemCastingRecipeBuilder.tableRecipe(TGItems.HAND_CAST_WITH_BRASS_HAND)
                .setFluidAndTime(TinkerFluids.moltenBrass, FluidValues.INGOT * 4)
                .setCast(TGItems.HAND_CAST_WITH_PART, false)
                .setCoolingTime(82)
                .save(consumer, location(folder + "brass_hand_1"));
        ItemCastingRecipeBuilder.tableRecipe(TGItems.HAND_CAST)
                .setFluidAndTime(TinkerFluids.moltenGold, FluidValues.INGOT)
                .setCast(AllItems.BRASS_HAND, true)
                .setCoolingTime(57)
                .save(consumer, location(folder + "brass_hand_2"));


        ItemCastingRecipeBuilder.tableRecipe(TGItems.PROPELLER_CAST_WITH_PROPELLER_)
                .setFluidAndTime(TinkerFluids.moltenIron, FluidValues.INGOT * 4)
                .setCast(TGItems.PROPELLER_CAST_WITH_PART, false)
                .setCoolingTime(82)
                .save(consumer, location(folder + "propeller_1"));
        ItemCastingRecipeBuilder.tableRecipe(TGItems.PROPELLER_CAST)
                .setFluidAndTime(TinkerFluids.moltenGold, FluidValues.INGOT)
                .setCast(AllItems.PROPELLER, true)
                .setCoolingTime(57)
                .save(consumer, location(folder + "propeller_2"));


        ItemCastingRecipeBuilder.tableRecipe(TGItems.WHISK_CAST_WITH_WHISK)
                .setFluidAndTime(TinkerFluids.moltenIron, FluidValues.INGOT * 5)
                .setCast(TGItems.WHISK_CAST_WITH_PART, false)
                .setCoolingTime(82)
                .save(consumer, location(folder + "whisk_1"));
        ItemCastingRecipeBuilder.tableRecipe(TGItems.WHISK_CAST)
                .setFluidAndTime(TinkerFluids.moltenGold, FluidValues.INGOT)
                .setCast(AllItems.WHISK, true)
                .setCoolingTime(82)
                .save(consumer, location(folder + "whisk_2"));
        Consumer<Consumer<FinishedRecipe>> defaultWhiskRecipe = c ->
                ItemCastingRecipeBuilder.tableRecipe(TGItems.WHISK_CAST)
                        .setFluidAndTime(TinkerFluids.moltenGold, FluidValues.INGOT)
                        .setCast(CMItems.STURDY_WHISK, true)
                        .setCoolingTime(82)
                        .save(c);
        ConditionalRecipe.builder()
                .addCondition(new ModLoadedCondition("createmetallurgy"))
                .addRecipe(defaultWhiskRecipe)
                .generateAdvancement()
                .build(consumer, location(folder + "whisk_3"));


        ItemCastingRecipeBuilder.tableRecipe(AllItems.BLAZE_CAKE)
                .setFluidAndTime(new FluidStack(Fluids.LAVA, FluidValues.BRICK))
                .setCast(AllItems.BLAZE_CAKE_BASE, true)
                .setCoolingTime(82)
                .save(consumer, location(folder + "blaze_cake"));
    }

    private void addMeltingRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "smeltery/melting/";
    }

    private void addAlloyRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "smeltery/alloys/";


    }

    private void addEntityMeltingRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "smeltery/entity_melting/";
        String headFolder = "smeltery/entity_melting/heads/";


    }

    @Override
    public SmelteryRecipeBuilder fluid(Consumer<FinishedRecipe> consumer, String name, FluidObject<?> fluid) {
        return ISmelteryRecipeHelper.super.fluid(consumer, name, fluid).castingFolder("smeltery/casting").meltingFolder("smeltery/melting");
    }

    /** Creates a metal from a tag */
    public SmelteryRecipeBuilder metal(Consumer<FinishedRecipe> consumer, String name, TagKey<Fluid> fluid) {
        return SmelteryRecipeBuilder.fluid(consumer, location(name), fluid).castingFolder("smeltery/casting/metal").meltingFolder("smeltery/melting/metal");
    }

    /** Creates a smeltery builder for a metal fluid */
    public SmelteryRecipeBuilder metal(Consumer<FinishedRecipe> consumer, FluidObject<?> fluid) {
        return molten(consumer, fluid).castingFolder("smeltery/casting/metal").meltingFolder("smeltery/melting/metal");
    }

    /** Handles tag based melting and casting recipes using the builder */
    private void addTagRecipes(Consumer<FinishedRecipe> consumer) {

        }

    private void addCompatRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "compat/";

    }


    /* Seared casting */

    /**
     * Adds a stonecutting recipe with automatic name and criteria
     * @param consumer  Recipe consumer
     * @param output    Recipe output
     * @param folder    Recipe folder path
     */
    private void searedStonecutter(Consumer<FinishedRecipe> consumer, ItemLike output, String folder) {

    }

    /**
     * Adds a recipe to create the given seared block using molten clay on stone
     * @param consumer  Recipe consumer
     * @param block     Output block
     * @param cast      Cast item
     * @param location  Recipe location
     */
    private void searedCasting(Consumer<FinishedRecipe> consumer, ItemLike block, Ingredient cast, String location) {

    }

    /**
     * Adds a recipe to create the given seared slab block using molten clay on stone
     * @param consumer  Recipe consumer
     * @param block     Output block
     * @param cast      Cast item
     * @param location  Recipe location
     */
    private void searedSlabCasting(Consumer<FinishedRecipe> consumer, ItemLike block, Ingredient cast, String location) {

    }

    /**
     * Adds a recipe to create the given seared block using molten clay on stone
     * @param consumer  Recipe consumer
     * @param block     Output block
     * @param cast      Cast item
     * @param amount    Amount of fluid needed
     * @param location  Recipe location
     */
    private void searedCasting(Consumer<FinishedRecipe> consumer, ItemLike block, Ingredient cast, int amount, String location) {

    }


    /* Scorched casting */

    /**
     * Adds a stonecutting recipe with automatic name and criteria
     * @param consumer  Recipe consumer
     * @param output    Recipe output
     * @param folder    Recipe folder path
     */
    private void scorchedStonecutter(Consumer<FinishedRecipe> consumer, ItemLike output, String folder) {

    }

    /**
     * Adds a recipe to create the given seared block using molten clay on stone
     * @param consumer  Recipe consumer
     * @param block     Output block
     * @param cast      Cast item
     * @param location  Recipe location
     */
    private void scorchedCasting(Consumer<FinishedRecipe> consumer, ItemLike block, Ingredient cast, String location) {

    }

    /**
     * Adds a recipe to create the given seared block using molten clay on stone
     * @param consumer  Recipe consumer
     * @param block     Output block
     * @param cast      Cast item
     * @param amount    Amount of fluid needed
     * @param location  Recipe location
     */
    private void scorchedCasting(Consumer<FinishedRecipe> consumer, ItemLike block, Ingredient cast, int amount, String location) {

    }


    /* Casting */

    /**
     * Adds melting recipes for slime
     * @param consumer  Consumer
     * @param fluid     Fluid
     * @param type      Slime type
     * @param folder    Output folder
     */
    private void slimeMelting(Consumer<FinishedRecipe> consumer, FluidObject<?> fluid, SlimeType type, String folder) {

    }

    /**
     * Adds slime related casting recipes
     * @param consumer    Recipe consumer
     * @param fluid       Fluid matching the slime type
     * @param slimeType   SlimeType for this recipe
     * @param folder      Output folder
     */
    private void slimeCasting(Consumer<FinishedRecipe> consumer, FluidObject<?> fluid, SlimeType slimeType, String folder) {
        String colorFolder = folder + slimeType.getSerializedName() + "/";

    }

    /** Adds recipes for melting slime crystals */
    private void crystalMelting(Consumer<FinishedRecipe> consumer, GeodeItemObject geode, FluidObject<?> fluid, String folder) {

    }
}
