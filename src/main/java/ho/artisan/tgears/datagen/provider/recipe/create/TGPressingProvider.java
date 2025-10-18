package ho.artisan.tgears.datagen.provider.recipe.create;

import com.simibubi.create.AllItems;
import com.simibubi.create.api.data.recipe.PressingRecipeGen;
import fr.lucreeper74.createmetallurgy.registries.CMItems;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public final class TGPressingProvider extends PressingRecipeGen {

    public TGPressingProvider(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
        createRecipes();
    }

    private void createRecipes() {
        separatingRecipe(
                "hand/brass",
                TGItems.HAND_CAST_WITH_BRASS_HAND,
                TGItems.HAND_CAST,
                AllItems.BRASS_HAND
        );

        separatingRecipe(
                "hand/blazing_chocolate",
                TGItems.HAND_CAST_WITH_BLAZING_CHOCOLATE_HAND,
                TGItems.HAND_CAST,
                TGItems.BLAZING_CHOCOLATE_HAND
        );

        separatingRecipe(
                "hand/chocolate",
                TGItems.HAND_CAST_WITH_CHOCOLATE_HAND,
                TGItems.HAND_CAST,
                TGItems.CHOCOLATE_HAND
        );

        separatingRecipe(
                "propeller/iron",
                TGItems.PROPELLER_CAST_WITH_PROPELLER,
                TGItems.PROPELLER_CAST,
                AllItems.PROPELLER
        );

        separatingRecipe(
                "propeller/cobalt",
                TGItems.PROPELLER_CAST_WITH_COBALT_PROPELLER,
                TGItems.PROPELLER_CAST,
                TGItems.COBALT_PROPELLER
        );

        separatingRecipe(
                "whisk/iron",
                TGItems.WHISK_CAST_WITH_WHISK,
                TGItems.WHISK_CAST,
                AllItems.WHISK
        );

        if (TinkersGears.METALLURGY_LOADED)
            separatingRecipe(
                    "whisk/sturdy",
                    TGItems.WHISK_CAST_WITH_STURDY_WHISK,
                    TGItems.WHISK_CAST,
                    CMItems.STURDY_WHISK
            );

        create("cobalt_sheet", b -> b.require(Ingredient.of(ItemTags.create(new ResourceLocation("forge", "ingots/cobalt"))))
                .output(TGItems.COBALT_SHEET));
    }

    private void separatingRecipe(String id, ItemLike castWithItem, ItemLike cast, ItemLike item) {
        create("separating/" + id, b -> b.require(Ingredient.of(castWithItem))
                .output(cast)
                .output(item));
    }
}