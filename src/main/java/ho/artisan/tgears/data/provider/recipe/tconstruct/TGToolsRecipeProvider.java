package ho.artisan.tgears.data.provider.recipe.tconstruct;

import ho.artisan.tgears.data.provider.recipe.TGRecipeProvider;
import ho.artisan.tgears.index.TGTinkerItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;

import java.util.function.Consumer;

public final class TGToolsRecipeProvider extends TGRecipeProvider implements IMaterialRecipeHelper, IToolRecipeHelper {
    public TGToolsRecipeProvider(PackOutput generator) {
        super(generator);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        String partFolder = "tinker/parts/";
        String castFolder = "casting/casts/";

        partRecipes(consumer, TGTinkerItems.HAND_PART, TGTinkerItems.HAND_CAST, 4, partFolder, castFolder);
        partRecipes(consumer, TGTinkerItems.PROPELLER_PART, TGTinkerItems.PROPELLER_CAST, 4, partFolder, castFolder);
        partRecipes(consumer, TGTinkerItems.WHISK_PART, TGTinkerItems.WHISK_CAST, 5, partFolder, castFolder);
    }

    @Override
    public String getType() {
        return "tool";
    }
}
