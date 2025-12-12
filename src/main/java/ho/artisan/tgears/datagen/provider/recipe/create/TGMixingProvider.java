package ho.artisan.tgears.datagen.provider.recipe.create;

import com.simibubi.create.AllItems;
import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGFluids;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.block.SlimeType;

public final class TGMixingProvider extends MixingRecipeGen {

    public TGMixingProvider(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
        fluidRecipes();
        slimeRecipes();
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