package ho.artisan.tgears.data.provider.recipe.create;

import com.simibubi.create.api.data.recipe.ItemApplicationRecipeGen;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGBlocks;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

public final class TGItemApplicationRecipeGen extends ItemApplicationRecipeGen {
    public TGItemApplicationRecipeGen(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
        casingRecipe();
    }

    private void casingRecipe() {
        create("tinker_casing", b -> b.output(TGBlocks.TINKER_CASING)
                .require(TinkerSmeltery.scorchedStone)
                .require(TGTagKeys.Items.BRASS_INGOT));

        create("cobalt_casing", b -> b.output(TGBlocks.COBALT_CASING)
                .require(TinkerSmeltery.searedStone)
                .require(TGTagKeys.Items.COBALT_INGOT));
    }
}
