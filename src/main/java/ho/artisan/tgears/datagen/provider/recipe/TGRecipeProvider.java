package ho.artisan.tgears.datagen.provider.recipe;

import ho.artisan.tgears.TinkersGears;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.mantle.recipe.data.IRecipeHelper;

public abstract class TGRecipeProvider extends RecipeProvider implements IConditionBuilder, IRecipeHelper {
    public TGRecipeProvider(PackOutput output) {
        super(output);
    }

    public void log(String message) {
        LOGGER.info(message);
    }

    @Override
    public ResourceLocation location(String id) {
        return TinkersGears.asResource(getType() + "/" + id);
    }

    @Override
    public String getName() {
        return "TG Recipe[" + getType() + "]";
    }

    @Override
    public String getModId() {
        return TinkersGears.MOD_ID;
    }

    public abstract String getType();
}
