package ho.artisan.tgears.datagen.provider.recipe.create;

import com.simibubi.create.api.data.recipe.PressingRecipeGen;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGItems;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.crafting.Ingredient;

public final class TGPressingProvider extends PressingRecipeGen {

    public TGPressingProvider(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
        plateRecipes();
    }

    private void plateRecipes() {
        create("cobalt_sheet", b -> b
                .require(Ingredient.of(TGTagKeys.Items.COBALT_INGOT))
                .output(TGItems.COBALT_SHEET));

        create("rose_gold_sheet", b -> b
                .require(Ingredient.of(TGTagKeys.Items.ROSE_GOLD_INGOT))
                .output(TGItems.ROSE_GOLD_SHEET));
    }
}