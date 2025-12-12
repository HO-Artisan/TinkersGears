package ho.artisan.tgears.datagen.provider.recipe.create;

import com.simibubi.create.api.data.recipe.DeployingRecipeGen;
import ho.artisan.tgears.TinkersGears;
import net.minecraft.data.PackOutput;

public final class TGDeployingProvider extends DeployingRecipeGen {
    public TGDeployingProvider(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
    }
}
