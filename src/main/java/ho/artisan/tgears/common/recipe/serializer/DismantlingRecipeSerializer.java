package ho.artisan.tgears.common.recipe.serializer;

import com.google.gson.JsonObject;
import ho.artisan.tgears.common.recipe.DismantlingRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DismantlingRecipeSerializer implements RecipeSerializer<DismantlingRecipe> {
    @Override
    public DismantlingRecipe fromJson(ResourceLocation id, JsonObject object) {
        List<DismantlingRecipe.PartStack> parts = new ArrayList<>();

        object.getAsJsonArray("parts").getAsJsonArray().forEach(part -> {
            ItemStack itemstack = ShapedRecipe.itemStackFromJson(part.getAsJsonObject());
            float chance = GsonHelper.getAsFloat(object, "chance", 1.0F);
            parts.add(new DismantlingRecipe.PartStack(itemstack, chance));
        });

        Ingredient ingredient = Ingredient.fromJson(object.get("tool"));
        int primary = GsonHelper.getAsInt(object, "primary", 0);
        int breakTime = GsonHelper.getAsInt(object, "time", 200);

        return new DismantlingRecipe(id, parts, ingredient, primary, breakTime);
    }

    @Override
    public @Nullable DismantlingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
        int partsSize = buffer.readVarInt();
        List<DismantlingRecipe.PartStack> parts = new ArrayList<>();
        for (int i = 0; i < partsSize; i++) {
            ItemStack itemstack = buffer.readItem();
            float chance = buffer.readFloat();
            parts.add(new DismantlingRecipe.PartStack(itemstack, chance));
        }
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        int primary = buffer.readVarInt();
        int breakTime = buffer.readVarInt();
        return new DismantlingRecipe(id, parts, ingredient, primary, breakTime);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, DismantlingRecipe recipe) {
        buf.writeVarInt(recipe.getParts().size());
        recipe.getParts().forEach(part -> {
            buf.writeItem(part.stack());
            buf.writeFloat(part.chance());
        });
        recipe.getIngredient().toNetwork(buf);
        buf.writeVarInt(recipe.getPrimary());
        buf.writeVarInt(recipe.getTime());
    }
}
