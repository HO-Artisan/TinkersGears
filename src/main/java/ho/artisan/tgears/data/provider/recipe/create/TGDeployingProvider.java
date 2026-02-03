package ho.artisan.tgears.data.provider.recipe.create;

import com.simibubi.create.AllItems;
import com.simibubi.create.api.data.recipe.DeployingRecipeGen;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGItems;
import ho.artisan.tgears.index.TGMaterials;
import ho.artisan.tgears.index.TGTinkerItems;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.recipe.ingredient.MaterialIngredient;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

public final class TGDeployingProvider extends DeployingRecipeGen {
    public TGDeployingProvider(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
        create("brass_hand", b -> b.require(MaterialIngredient.of(TGTinkerItems.HAND_PART, TGMaterials.Ids.BRASS))
                .require(AllItems.ANDESITE_ALLOY)
                .output(AllItems.BRASS_HAND));

        create("chocolate_hand", b -> b.require(MaterialIngredient.of(TGTinkerItems.HAND_PART, TGMaterials.Ids.CHOCOLATE))
                .require(AllItems.ANDESITE_ALLOY)
                .output(TGItems.CHOCOLATE_HAND));

        create("blazing_chocolate_hand", b -> b.require(MaterialIngredient.of(TGTinkerItems.HAND_PART, TGMaterials.Ids.BLAZING_CHOCOLATE))
                .require(AllItems.ANDESITE_ALLOY)
                .output(TGItems.BLAZING_CHOCOLATE_HAND));

        create("propeller", b -> b.require(MaterialIngredient.of(TGTinkerItems.PROPELLER_PART, MaterialIds.iron))
                .require(AllItems.ANDESITE_ALLOY)
                .output(AllItems.PROPELLER));

        create("cobalt_propeller", b -> b.require(MaterialIngredient.of(TGTinkerItems.PROPELLER_PART, MaterialIds.cobalt))
                .require(AllItems.ANDESITE_ALLOY)
                .output(TGItems.COBALT_PROPELLER));

        create("whisk", b -> b.require(MaterialIngredient.of(TGTinkerItems.WHISK_PART, MaterialIds.iron))
                .require(AllItems.ANDESITE_ALLOY)
                .output(AllItems.WHISK));
    }
}
