package ho.artisan.tgears.datagen.provider.recipe.create;

import com.mrh0.createaddition.CreateAddition;
import com.mrh0.createaddition.index.CAItems;
import com.simibubi.create.api.data.recipe.FillingRecipeGen;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGBlocks;
import ho.artisan.tgears.index.TGItems;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.gadgets.TinkerGadgets;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.shared.block.SlimeType;
import slimeknights.tconstruct.world.block.FoliageType;

public final class TGFillingProvider extends FillingRecipeGen {

    public TGFillingProvider(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
        createRecipes();
    }

    private void createRecipes() {
        create("blazing_chocolate_glazed_berries", b -> b.require(TGTagKeys.Fluids.BLAZING_CHOCOLATE, FluidValues.BOTTLE)
                .require(Items.SWEET_BERRIES)
                .output(TGItems.BLAZING_CHOCOLATE_BERRIES));

        create("silktouch_drill", b -> b.require(TinkerFluids.moltenRoseGold.getTag(), FluidValues.INGOT * 4)
                .require(TGBlocks.TINKER_DRILL)
                .output(TGBlocks.TINKER_SILKTOUCH_DRILL));

        cakeRecipe("earth", TinkerFluids.slime.get(SlimeType.EARTH), new ItemStack(TinkerGadgets.cake.get(FoliageType.EARTH)));
        cakeRecipe("sky", TinkerFluids.slime.get(SlimeType.SKY), new ItemStack(TinkerGadgets.cake.get(FoliageType.SKY)));
        cakeRecipe("ender", TinkerFluids.slime.get(SlimeType.ENDER), new ItemStack(TinkerGadgets.cake.get(FoliageType.ENDER)));
        cakeRecipe("ichor", TinkerFluids.ichor.get(), new ItemStack(TinkerGadgets.cake.get(FoliageType.ICHOR)));

        handRecipe(
                "brass",
                "molten_brass",
                FluidValues.INGOT * 4,
                new ItemStack(TGItems.HAND_CAST_WITH_BRASS_HAND)
        );

        handRecipe(
                "blazing_chocolate",
                "blazing_chocolate",
                FluidValues.BOTTLE * 2,
                new ItemStack(TGItems.HAND_CAST_WITH_BLAZING_CHOCOLATE_HAND)
        );

        handRecipe(
                "chocolate",
                "chocolate",
                FluidValues.BOTTLE * 2,
                new ItemStack(TGItems.HAND_CAST_WITH_CHOCOLATE_HAND)
        );

        propellerRecipe(
                "iron",
                "molten_iron",
                FluidValues.INGOT * 4,
                new ItemStack(TGItems.PROPELLER_CAST_WITH_PROPELLER)
        );

        propellerRecipe(
                "cobalt",
                "molten_cobalt",
                FluidValues.INGOT * 4,
                new ItemStack(TGItems.PROPELLER_CAST_WITH_COBALT_PROPELLER)
        );

        whiskRecipe(
                "iron",
                "molten_iron",
                FluidValues.INGOT * 5,
                new ItemStack(TGItems.WHISK_CAST_WITH_WHISK)
        );
    }

    private void cakeRecipe(String id, Fluid fluid, ItemStack output) {
        create("cake/" + id, b -> b.require(fluid, 1000)
                .require(CAItems.CAKE_BASE)
                .output(output).withCondition(new ModLoadedCondition(CreateAddition.MODID)));
    }

    private void handRecipe(String id, String fluid, int amount, ItemStack output) {
        create("hand/" + id, b -> b.require(
                        FluidTags.create(new ResourceLocation("forge", fluid)), amount)
                .require(TGItems.HAND_CAST_WITH_PART)
                .output(output));
    }

    private void propellerRecipe(String id, String fluid, int amount, ItemStack output) {
        create("propeller/" + id, b -> b.require(
                        FluidTags.create(new ResourceLocation("forge", fluid)), amount)
                .require(TGItems.PROPELLER_CAST_WITH_PART)
                .output(output));
    }

    private void whiskRecipe(String id, String fluid, int amount, ItemStack output) {
        create("whisk/" + id, b -> b.require(
                        FluidTags.create(new ResourceLocation("forge", fluid)), amount)
                .require(TGItems.WHISK_CAST_WITH_PART)
                .output(output));
    }
}