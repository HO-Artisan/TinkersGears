package ho.artisan.tgears.datagen.provider.recipe.create;

import com.simibubi.create.AllItems;
import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGFluids;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.shared.TinkerCommons;
import slimeknights.tconstruct.shared.block.SlimeType;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

public final class TGMixingProvider extends MixingRecipeGen {

    public TGMixingProvider(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
        createRecipes();
    }

    private void createRecipes() {
        create("blazing_chocolate", b -> b.require(TinkerFluids.blazingBlood.get(), FluidValues.BRICK)
                .require(FluidTags.create(new ResourceLocation("forge", "chocolate")), FluidValues.BRICK)
                .output(TGFluids.BLAZING_CHOCOLATE.get(), FluidValues.BRICK * 2)
                .requiresHeat(HeatCondition.HEATED));

        create("slime/ender", b -> b.require(AllItems.DOUGH)
                .require(TinkerFluids.moltenEnder.get(), 25)
                .require(Tags.Items.DYES_PURPLE)
                .output(TinkerCommons.slimeball.get(SlimeType.ENDER), 2)
        );

        create("slime/ichor", b -> b.require(AllItems.DOUGH)
                .require(TinkerFluids.blazingBlood.get(), 20)
                .require(Tags.Items.DYES_ORANGE)
                .output(TinkerCommons.slimeball.get(SlimeType.ICHOR), 2)
        );

        create("slime/sky", b -> b.require(AllItems.DOUGH)
                .require(Fluids.WATER, 250)
                .require(Tags.Items.DYES_CYAN)
                .output(TinkerCommons.slimeball.get(SlimeType.SKY), 2)
        );

        create("grout", b -> b.require(Fluids.WATER, 250)
                .require(Items.GRAVEL)
                .require(Items.SAND)
                .require(Items.SAND)
                .output(TinkerSmeltery.grout, 2)
        );
    }
}