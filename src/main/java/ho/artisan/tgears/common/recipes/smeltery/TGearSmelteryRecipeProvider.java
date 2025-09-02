package ho.artisan.tgears.common.recipes.smeltery;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.DifferenceIngredient;
import net.minecraftforge.common.crafting.conditions.AndCondition;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.ItemExistsCondition;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.common.crafting.conditions.TrueCondition;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import slimeknights.mantle.datagen.MantleTags;
import slimeknights.mantle.recipe.condition.TagFilledCondition;
import slimeknights.mantle.recipe.crafting.ShapedRetexturedRecipeBuilder;
import slimeknights.mantle.recipe.data.ConsumerWrapperBuilder;
import slimeknights.mantle.recipe.data.ICommonRecipeHelper;
import slimeknights.mantle.recipe.data.ItemNameIngredient;
import slimeknights.mantle.recipe.data.ItemNameOutput;
import slimeknights.mantle.recipe.helper.ItemOutput;
import slimeknights.mantle.recipe.ingredient.EntityIngredient;
import slimeknights.mantle.recipe.ingredient.FluidIngredient;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.common.data.BaseRecipeProvider;
import slimeknights.tconstruct.common.json.ConfigEnabledCondition;
import slimeknights.tconstruct.common.registration.GeodeItemObject;
import slimeknights.tconstruct.common.registration.GeodeItemObject.BudSize;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.fluids.fluids.PotionFluidType;
import slimeknights.tconstruct.gadgets.TinkerGadgets;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder;
import slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder.CommonRecipe;
import slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder.ToolItemMelting;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.PotionCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.container.ContainerFillingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.entitymelting.EntityMeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.fuel.MeltingFuelBuilder;
import slimeknights.tconstruct.library.recipe.ingredient.BlockTagIngredient;
import slimeknights.tconstruct.library.recipe.ingredient.NoContainerIngredient;
import slimeknights.tconstruct.library.recipe.melting.IMeltingContainer.OreRateType;
import slimeknights.tconstruct.library.recipe.melting.IMeltingRecipe;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.molding.MoldingRecipeBuilder;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.TinkerMaterials;
import slimeknights.tconstruct.shared.block.SlimeType;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.component.SearedTankBlock.TankType;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.data.material.MaterialIds;
import slimeknights.tconstruct.world.TinkerHeadType;
import slimeknights.tconstruct.world.TinkerWorld;
import slimeknights.tconstruct.world.block.FoliageType;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static slimeknights.mantle.Mantle.COMMON;
import static slimeknights.mantle.Mantle.commonResource;
import static slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder.ARMOR;
import static slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder.ARMOR_PLUS;
import static slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder.AXES;
import static slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder.BOOTS;
import static slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder.CHESTPLATE;
import static slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder.HELMET;
import static slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder.LEGGINGS_PLUS;
import static slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder.SHOVEL_PLUS;
import static slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder.SWORD;
import static slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder.TOOLS;

public class TGSmelteryRecipeProvider extends BaseRecipeProvider implements ISmelteryRecipeHelper, ICommonRecipeHelper {
    public TGSmelteryRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
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
        SingleItemRecipeBuilder.stonecutting(
                        CompoundIngredient.of(
                                Ingredient.of(TinkerSmeltery.searedStone),
                                DifferenceIngredient.of(Ingredient.of(TinkerTags.Items.SEARED_BRICKS), Ingredient.of(output))), RecipeCategory.BUILDING_BLOCKS, output, 1)
                .unlockedBy("has_stone", has(TinkerSmeltery.searedStone))
                .unlockedBy("has_bricks", has(TinkerTags.Items.SEARED_BRICKS))
                .save(consumer, wrap(id(output), folder, "_stonecutting"));
    }

    /**
     * Adds a recipe to create the given seared block using molten clay on stone
     * @param consumer  Recipe consumer
     * @param block     Output block
     * @param cast      Cast item
     * @param location  Recipe location
     */
    private void searedCasting(Consumer<FinishedRecipe> consumer, ItemLike block, Ingredient cast, String location) {
        searedCasting(consumer, block, cast, FluidValues.SLIMEBALL * 2, location);
    }

    /**
     * Adds a recipe to create the given seared slab block using molten clay on stone
     * @param consumer  Recipe consumer
     * @param block     Output block
     * @param cast      Cast item
     * @param location  Recipe location
     */
    private void searedSlabCasting(Consumer<FinishedRecipe> consumer, ItemLike block, Ingredient cast, String location) {
        searedCasting(consumer, block, cast, FluidValues.SLIMEBALL, location);
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
        ItemCastingRecipeBuilder.basinRecipe(block)
                .setFluidAndTime(TinkerFluids.moltenClay, amount)
                .setCast(cast, true)
                .save(consumer, location(location));
    }


    /* Scorched casting */

    /**
     * Adds a stonecutting recipe with automatic name and criteria
     * @param consumer  Recipe consumer
     * @param output    Recipe output
     * @param folder    Recipe folder path
     */
    private void scorchedStonecutter(Consumer<FinishedRecipe> consumer, ItemLike output, String folder) {
        SingleItemRecipeBuilder.stonecutting(DifferenceIngredient.of(Ingredient.of(TinkerTags.Items.SCORCHED_BLOCKS), Ingredient.of(output)), RecipeCategory.BUILDING_BLOCKS, output, 1)
                .unlockedBy("has_block", has(TinkerTags.Items.SCORCHED_BLOCKS))
                .save(consumer, wrap(id(output), folder, "_stonecutting"));
    }

    /**
     * Adds a recipe to create the given seared block using molten clay on stone
     * @param consumer  Recipe consumer
     * @param block     Output block
     * @param cast      Cast item
     * @param location  Recipe location
     */
    private void scorchedCasting(Consumer<FinishedRecipe> consumer, ItemLike block, Ingredient cast, String location) {
        scorchedCasting(consumer, block, cast, FluidValues.SLIMEBALL * 2, location);
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
        ItemCastingRecipeBuilder.basinRecipe(block)
                .setFluidAndTime(TinkerFluids.magma, amount)
                .setCast(cast, true)
                .save(consumer, location(location));
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
        String slimeFolder = folder + type.getSerializedName() + "/";
        MeltingRecipeBuilder.melting(Ingredient.of(type.getSlimeballTag()), fluid, FluidValues.SLIMEBALL, 1.0f)
                .save(consumer, location(slimeFolder + "ball"));
        ItemLike item = TinkerWorld.congealedSlime.get(type);
        MeltingRecipeBuilder.melting(Ingredient.of(item), fluid, FluidValues.SLIME_CONGEALED, 2.0f)
                .save(consumer, location(slimeFolder + "congealed"));
        item = TinkerWorld.slime.get(type);
        MeltingRecipeBuilder.melting(Ingredient.of(item), fluid, FluidValues.SLIME_BLOCK, 3.0f)
                .save(consumer, location(slimeFolder + "block"));
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
        ItemCastingRecipeBuilder.basinRecipe(TinkerWorld.congealedSlime.get(slimeType))
                .setFluidAndTime(fluid, FluidValues.SLIME_CONGEALED)
                .save(consumer, location(colorFolder + "congealed"));
        ItemCastingRecipeBuilder.basinRecipe(TinkerWorld.slimyEnderbarkRoots.get(slimeType))
                .setFluidAndTime(fluid, FluidValues.SLIME_CONGEALED)
                .setCast(TinkerWorld.enderbarkRoots, true)
                .save(consumer, location(colorFolder + "roots"));
        ItemCastingRecipeBuilder.basinRecipe(TinkerWorld.slime.get(slimeType))
                .setFluidAndTime(fluid, FluidValues.SLIME_BLOCK - FluidValues.SLIME_CONGEALED)
                .setCast(TinkerWorld.congealedSlime.get(slimeType), true)
                .save(consumer, location(colorFolder + "block"));
        ItemCastingRecipeBuilder.tableRecipe(TinkerCommons.slimeball.get(slimeType))
                .setFluidAndTime(fluid, FluidValues.SLIMEBALL)
                .save(consumer, location(colorFolder + "slimeball"));
        ItemCastingRecipeBuilder.tableRecipe(TinkerFluids.slimeBottle.get(slimeType))
                .setFluid(fluid.ingredient(FluidValues.SLIMEBALL))
                .setCoolingTime(1)
                .setCast(Items.GLASS_BOTTLE, true)
                .save(consumer, location(colorFolder + "bottle"));
        ItemCastingRecipeBuilder.basinRecipe(TinkerWorld.slimeDirt.get(slimeType.asDirt()))
                .setFluidAndTime(fluid, FluidValues.SLIMEBALL * 2)
                .setCast(Blocks.DIRT, true)
                .save(consumer, location(colorFolder + "dirt"));
    }

    /** Adds recipes for melting slime crystals */
    private void crystalMelting(Consumer<FinishedRecipe> consumer, GeodeItemObject geode, FluidObject<?> fluid, String folder) {
        MeltingRecipeBuilder.melting(Ingredient.of(geode), fluid, FluidValues.SLIMEBALL, 1.0f).save(consumer, location(folder + "crystal"));
        MeltingRecipeBuilder.melting(Ingredient.of(geode.getBlock()), fluid, FluidValues.SLIMEBALL * 4, 2.0f).save(consumer, location(folder + "crystal_block"));
        for (BudSize bud : BudSize.values()) {
            int size = bud.getSize();
            MeltingRecipeBuilder.melting(Ingredient.of(geode.getBud(bud)), fluid, FluidValues.SLIMEBALL * size, (size + 1) / 2f)
                    .setOre(OreRateType.GEM)
                    .save(consumer, location(folder + "bud_" + bud.getName()));
        }
    }
}
