package ho.artisan.tgears.datagen.provider.recipe.create;

import com.simibubi.create.AllItems;
import com.simibubi.create.api.data.recipe.DeployingRecipeGen;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;

public class TGDeployingProvider extends DeployingRecipeGen {
    public TGDeployingProvider(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
        createRecipes();
    }

    private void createRecipes() {
        applyPart("hand", TGItems.HAND_CAST, TGItems.HAND_CAST_WITH_PART);
        applyPart("propeller", TGItems.PROPELLER_CAST, TGItems.PROPELLER_CAST_WITH_PART);
        applyPart("whisk", TGItems.WHISK_CAST, TGItems.WHISK_CAST_WITH_PART);
    }

    private void applyPart(String id, ItemLike cast, ItemLike castWithPart) {
        create("part/" + id, b -> b.require(cast)
                .require(AllItems.ANDESITE_ALLOY)
                .output(castWithPart)
        );
    }
}
