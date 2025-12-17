package ho.artisan.tgears.datagen.provider.recipe.create;

import com.simibubi.create.AllItems;
import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGFluids;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.TinkerMaterials;
import slimeknights.tconstruct.shared.block.SlimeType;

public final class TGMixingProvider extends MixingRecipeGen implements IConditionBuilder {

    public TGMixingProvider(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
        alloyRecipes();
        fluidRecipes();
        slimeRecipes();
    }

    private void alloyRecipes() {
        String folder = "alloy/";

        create(folder + "amethyst_bronze_ingot", b -> b.output(TinkerMaterials.amethystBronze.getIngot(), 1)
                .require(Tags.Items.INGOTS_COPPER)
                .require(Tags.Items.GEMS_AMETHYST)
                .requiresHeat(HeatCondition.HEATED)
        );

        create(folder + "rose_gold", b -> b.output(TinkerMaterials.roseGold.getIngot(), 2)
                .require(Tags.Items.INGOTS_COPPER)
                .require(Tags.Items.INGOTS_GOLD)
                .requiresHeat(HeatCondition.HEATED)
        );

        create(folder + "slimesteel_ingot", b -> b.output(TinkerMaterials.slimesteel.getIngot(), 2)
                .require(Tags.Items.INGOTS_IRON)
                .require(FluidIngredient.fromFluid(TinkerFluids.searedStone.get(), 250))
                .require(TinkerCommons.slimeball.get(SlimeType.SKY))
                .requiresHeat(HeatCondition.HEATED)
        );

        create(folder + "hepatizon_ingot", b -> b.output(TinkerMaterials.hepatizon.getIngot(), 2)
                .require(Tags.Items.INGOTS_COPPER)
                .require(Tags.Items.INGOTS_COPPER)
                .require(TinkerMaterials.cobalt.getIngotTag())
                .require(Tags.Items.GEMS_QUARTZ)
                .requiresHeat(HeatCondition.SUPERHEATED)
        );

        create(folder + "queens_slime_ingot", b -> b.output(TinkerMaterials.queensSlime.getIngot(), 2)
                .require(Tags.Items.INGOTS_COPPER)
                .require(Tags.Items.INGOTS_GOLD)
                .require(FluidIngredient.fromFluid(TinkerFluids.magma.get(), 250))
                .requiresHeat(HeatCondition.SUPERHEATED)
        );

        create(folder + "manyullyn_ingot", b -> b.output(TinkerMaterials.manyullyn.getIngot(), 4)
                .require(TinkerMaterials.cobalt.getIngotTag())
                .require(TinkerMaterials.cobalt.getIngotTag())
                .require(TinkerMaterials.cobalt.getIngotTag())
                .require(Items.NETHERITE_SCRAP)
                .requiresHeat(HeatCondition.SUPERHEATED)
        );


    }

    private void fluidRecipes() {
        create("blazing_chocolate", b -> b.output(TGFluids.BLAZING_CHOCOLATE.get(), FluidValues.BRICK * 2)
                .require(TGTagKeys.Fluids.CHOCOLATE, FluidValues.BRICK)
                .require(TinkerFluids.blazingBlood.get(), FluidValues.BRICK)
                .requiresHeat(HeatCondition.HEATED)
        );
    }

    private void slimeRecipes() {
        create("slime/ender", b -> b.output(TinkerCommons.slimeball.get(SlimeType.ENDER), 2)
                .require(AllItems.DOUGH)
                .require(TinkerFluids.moltenEnder.get(), 25)
                .require(Tags.Items.DYES_PURPLE)
        );

        create("slime/ichor", b -> b.output(TinkerCommons.slimeball.get(SlimeType.ICHOR), 2)
                .require(AllItems.DOUGH)
                .require(TinkerFluids.blazingBlood.get(), 20)
                .require(Tags.Items.DYES_ORANGE)
        );

        create("slime/sky", b -> b.output(TinkerCommons.slimeball.get(SlimeType.SKY), 2)
                .require(AllItems.DOUGH)
                .require(Fluids.WATER, 250)
                .require(Tags.Items.DYES_CYAN)
        );
    }
}