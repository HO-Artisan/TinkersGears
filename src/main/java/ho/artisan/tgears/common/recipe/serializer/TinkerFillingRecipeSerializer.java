package ho.artisan.tgears.common.recipe.serializer;

import com.google.gson.JsonObject;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.Nullable;

public class TinkerFillingRecipeSerializer implements RecipeSerializer<FillingRecipe> {
    @Override
    public FillingRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
        return null;
    }

    @Override
    public @Nullable FillingRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf) {
        return null;
    }

    @Override
    public void toNetwork(FriendlyByteBuf friendlyByteBuf, FillingRecipe fillingRecipe) {

    }
}
