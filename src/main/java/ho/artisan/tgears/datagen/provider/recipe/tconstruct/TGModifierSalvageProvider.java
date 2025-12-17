package ho.artisan.tgears.datagen.provider.recipe.tconstruct;

import ho.artisan.tgears.datagen.provider.recipe.TGRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import slimeknights.mantle.data.predicate.IJsonPredicate;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.json.predicate.modifier.ModifierPredicate;
import slimeknights.tconstruct.library.modifiers.ModifierId;

import java.util.function.Consumer;

public class TGModifierSalvageProvider extends TGRecipeProvider {
    public TGModifierSalvageProvider(PackOutput output) {
        super(output);
    }

    @Override
    public String getType() {
        return "tinker/modifier/salvage";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        IJsonPredicate<ModifierId> extractBlacklist = ModifierPredicate.tag(TinkerTags.Modifiers.EXTRACT_MODIFIER_BLACKLIST).inverted();
    }
}
