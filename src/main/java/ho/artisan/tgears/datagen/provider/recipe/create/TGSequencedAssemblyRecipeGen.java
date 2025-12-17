package ho.artisan.tgears.datagen.provider.recipe.create;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.api.data.recipe.SequencedAssemblyRecipeGen;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGBlocks;
import net.minecraft.data.PackOutput;

public class TGSequencedAssemblyRecipeGen extends SequencedAssemblyRecipeGen {
    public TGSequencedAssemblyRecipeGen(PackOutput output) {
        super(output, TinkersGears.MOD_ID);

        create("fortune_crushing_wheel", b -> b.loops(8).addOutput(TGBlocks.TINKER_FORTUNE_CRUSHING_WHEEL, 1.0F)
                .transitionTo(TGBlocks.TINKER_CRUSHING_WHEEL)
                .require(TGBlocks.TINKER_CRUSHING_WHEEL)
                .addStep(DeployerApplicationRecipe::new, c -> c.require(AllBlocks.EXPERIENCE_BLOCK))
        );

        create("fortune_drill", b -> b.loops(4).addOutput(TGBlocks.TINKER_FORTUNE_DRILL, 1.0F)
                .transitionTo(TGBlocks.TINKER_DRILL)
                .require(TGBlocks.TINKER_DRILL)
                .addStep(DeployerApplicationRecipe::new, c -> c.require(AllBlocks.EXPERIENCE_BLOCK))
        );
    }
}
