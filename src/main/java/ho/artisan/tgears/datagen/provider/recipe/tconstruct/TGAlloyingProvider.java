package ho.artisan.tgears.datagen.provider.recipe.tconstruct;

import ho.artisan.tgears.datagen.provider.recipe.TGRecipeProvider;
import ho.artisan.tgears.index.TGFluids;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;

import java.util.function.Consumer;

public final class TGAlloyingProvider extends TGRecipeProvider {

    public TGAlloyingProvider(PackOutput generator) {
        super(generator);
    }

    @Override
    public String getType() {
        return "alloying";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        AlloyRecipeBuilder.alloy(new FluidStack(TGFluids.BLAZING_CHOCOLATE.getSource(), 20))
                .addInput(TGTagKeys.Fluids.CHOCOLATE, 10)
                .addInput(TinkerFluids.blazingBlood.get(), 10)
                .save(consumer, location("blazing_chocolate"));
    }
}
