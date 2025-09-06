package ho.artisan.tgears.datagen.provider.create;

import com.simibubi.create.AllItems;
import com.simibubi.create.api.data.recipe.PressingRecipeGen;
import com.simibubi.create.foundation.data.recipe.Mods;
import fr.lucreeper74.createmetallurgy.registries.CMItems;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * Create's own Data Generation for Pressing recipes
 * @see PressingRecipeGen
 */
@SuppressWarnings("unused")
public final class TGPressingRecipe extends PressingRecipeGen {

    public TGPressingRecipe(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
    }

    GeneratedRecipe

            HAND_CAST_WITH_PART = create("hand_cast_with_part", b -> b.require(Ingredient.of(TGItems.HAND_CAST_WITH_BRASS_HAND))
            .output(TGItems.HAND_CAST)
            .output(AllItems.BRASS_HAND)),

            PROPELLER_CAST_WITH_PART = create("propeller_cast_with_part", b -> b.require(Ingredient.of(TGItems.PROPELLER_CAST_WITH_PROPELLER_))
            .output(TGItems.PROPELLER_CAST)
            .output(AllItems.PROPELLER)),

            WHISK_CAST_WITH_PART = create("whisk_cast_with_part", b -> b.require(Ingredient.of(TGItems.WHISK_CAST_WITH_WHISK))
            .output(TGItems.WHISK_CAST)
            .output(AllItems.WHISK)),

            WHISK_CAST_WITH_STURDY_WHISK = create("whisk_cast_with_sturdy_whisk", b -> b.require(Ingredient.of(TGItems.WHISK_CAST_WITH_STURDY_WHISK))
            .whenModLoaded("createmetallurgy")
            .output(TGItems.WHISK_CAST)
            .output(CMItems.STURDY_WHISK));



    private GeneratedRecipe moddedPaths(Mods mod, String... blocks) {
        for(String block : blocks) {
            moddedCompacting(mod, block, block + "_path");
        }
        return null;
    }

    GeneratedRecipe moddedCompacting(Mods mod, String input, String output) {
        return create("compat/" + mod.getId() + "/" + output, b -> b.require(mod, input)
                .output(mod, output)
                .whenModLoaded(mod.getId()));
    }
}