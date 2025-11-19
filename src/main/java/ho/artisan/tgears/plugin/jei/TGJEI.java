package ho.artisan.tgears.plugin.jei;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.compat.jei.*;
import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.kinetics.crusher.AbstractCrushingRecipe;
import com.simibubi.create.content.kinetics.fan.processing.HauntingRecipe;
import com.simibubi.create.content.kinetics.fan.processing.SplashingRecipe;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import com.simibubi.create.foundation.utility.CreateLang;
import com.simibubi.create.infrastructure.config.CRecipes;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.common.recipe.SilkyCrushingRecipe;
import ho.artisan.tgears.index.TGBlocks;
import ho.artisan.tgears.index.TGItems;
import ho.artisan.tgears.index.TGRecipeTypes;
import ho.artisan.tgears.plugin.jei.category.SilkyCrushingCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import net.createmod.catnip.config.ConfigBase;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.simibubi.create.compat.jei.CreateJEI.*;

@JeiPlugin
public class TGJEI implements IModPlugin {
    private final List<CreateRecipeCategory<?>> allCategories = new ArrayList<>();

    private IIngredientManager ingredientManager;

    private void loadCategories() {
        allCategories.clear();

        CreateRecipeCategory<?> crushing = builder(SilkyCrushingRecipe.class)
                .addTypedRecipes(TGRecipeTypes.SILKY_CRUSHING)


                .catalyst(TGBlocks.TINKER_SILKY_CRUSHING_WHEEL::get)
                .doubleItemIcon(TGBlocks.TINKER_SILKY_CRUSHING_WHEEL.get(), TGItems.CRUSHED_RAW_COBALT.get())
                .emptyBackground(177, 100)
                .build("silky_crushing", SilkyCrushingCategory::new);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        loadCategories();
        registration.addRecipeCategories(allCategories.toArray(IRecipeCategory[]::new));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ingredientManager = registration.getIngredientManager();

        allCategories.forEach(c -> c.registerRecipes(registration));

        registration.addRecipes(RecipeTypes.CRAFTING, ToolboxColoringRecipeMaker.createRecipes().toList());
    }


    @Override
    public ResourceLocation getPluginUid() {
        return TinkersGears.asResource("jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(
                TGBlocks.TINKER_SPOUT.asItem(),
                RecipeType.create("create", "spout_filling", FillingRecipe.class)
        );

        registration.addRecipeCatalyst(
                TGBlocks.TINKER_CRUSHING_WHEEL.asItem(),
                RecipeType.create("create", "crushing", AbstractCrushingRecipe.class)
        );

        registration.addRecipeCatalyst(
                TGBlocks.TINKER_SILKY_CRUSHING_WHEEL.asItem(),
                RecipeType.create("create", "crushing", AbstractCrushingRecipe.class)
        );

        registerFanCatalyst(registration, "fan_blasting", AbstractCookingRecipe.class);
        registerFanCatalyst(registration, "fan_smoking", SmokingRecipe.class);
        registerFanCatalyst(registration, "fan_washing", SplashingRecipe.class);
        registerFanCatalyst(registration, "fan_haunting", HauntingRecipe.class);

        allCategories.forEach(c -> c.registerCatalysts(registration));
    }

    public static void registerFanCatalyst(IRecipeCatalystRegistration registration, String name, Class<?> type) {
        registration.addRecipeCatalyst(
                getFan(name).get(),
                RecipeType.create("create", name, type)
        );
    }

    public static Supplier<ItemStack> getFan(String name) {
        return () -> TGBlocks.TINKER_FAN.asStack()
                .setHoverName(CreateLang.translateDirect("recipe." + name + ".fan").withStyle(style -> style.withItalic(false)));
    }

    private <T extends Recipe<?>> TGJEI.CategoryBuilder<T> builder(Class<? extends T> recipeClass) {
        return new TGJEI.CategoryBuilder<>(recipeClass);
    }

    private class CategoryBuilder<T extends Recipe<?>> {
        private final Class<? extends T> recipeClass;
        private Predicate<CRecipes> predicate = cRecipes -> true;

        private IDrawable background;
        private IDrawable icon;

        private final List<Consumer<List<T>>> recipeListConsumers = new ArrayList<>();
        private final List<Supplier<? extends ItemStack>> catalysts = new ArrayList<>();

        public CategoryBuilder(Class<? extends T> recipeClass) {
            this.recipeClass = recipeClass;
        }

        public TGJEI.CategoryBuilder<T> enableIf(Predicate<CRecipes> predicate) {
            this.predicate = predicate;
            return this;
        }

        public TGJEI.CategoryBuilder<T> enableWhen(Function<CRecipes, ConfigBase.ConfigBool> configValue) {
            predicate = c -> configValue.apply(c).get();
            return this;
        }

        public TGJEI.CategoryBuilder<T> addRecipeListConsumer(Consumer<List<T>> consumer) {
            recipeListConsumers.add(consumer);
            return this;
        }

        public TGJEI.CategoryBuilder<T> addRecipes(Supplier<Collection<? extends T>> collection) {
            return addRecipeListConsumer(recipes -> recipes.addAll(collection.get()));
        }

        @SuppressWarnings("unchecked")
        public TGJEI.CategoryBuilder<T> addAllRecipesIf(Predicate<Recipe<?>> pred) {
            return addRecipeListConsumer(recipes -> consumeAllRecipes(recipe -> {
                if (pred.test(recipe))
                    recipes.add((T) recipe);
            }));
        }

        public TGJEI.CategoryBuilder<T> addAllRecipesIf(Predicate<Recipe<?>> pred, Function<Recipe<?>, T> converter) {
            return addRecipeListConsumer(recipes -> consumeAllRecipes(recipe -> {
                if (pred.test(recipe)) {
                    recipes.add(converter.apply(recipe));
                }
            }));
        }

        public TGJEI.CategoryBuilder<T> addTypedRecipes(IRecipeTypeInfo recipeTypeEntry) {
            return addTypedRecipes(recipeTypeEntry::getType);
        }

        public TGJEI.CategoryBuilder<T> addTypedRecipes(Supplier<net.minecraft.world.item.crafting.RecipeType<? extends T>> recipeType) {
            return addRecipeListConsumer(recipes -> CreateJEI.<T>consumeTypedRecipes(recipes::add, recipeType.get()));
        }

        public TGJEI.CategoryBuilder<T> addTypedRecipes(Supplier<net.minecraft.world.item.crafting.RecipeType<? extends T>> recipeType, Function<Recipe<?>, T> converter) {
            return addRecipeListConsumer(recipes -> CreateJEI.<T>consumeTypedRecipes(recipe -> recipes.add(converter.apply(recipe)), recipeType.get()));
        }

        public TGJEI.CategoryBuilder<T> addTypedRecipesIf(Supplier<net.minecraft.world.item.crafting.RecipeType<? extends T>> recipeType, Predicate<Recipe<?>> pred) {
            return addRecipeListConsumer(recipes -> CreateJEI.<T>consumeTypedRecipes(recipe -> {
                if (pred.test(recipe)) {
                    recipes.add(recipe);
                }
            }, recipeType.get()));
        }

        public TGJEI.CategoryBuilder<T> addTypedRecipesExcluding(Supplier<net.minecraft.world.item.crafting.RecipeType<? extends T>> recipeType,
                                                                 Supplier<net.minecraft.world.item.crafting.RecipeType<? extends T>> excluded) {
            return addRecipeListConsumer(recipes -> {
                List<Recipe<?>> excludedRecipes = getTypedRecipes(excluded.get());
                CreateJEI.<T>consumeTypedRecipes(recipe -> {
                    for (Recipe<?> excludedRecipe : excludedRecipes) {
                        if (doInputsMatch(recipe, excludedRecipe)) {
                            return;
                        }
                    }
                    recipes.add(recipe);
                }, recipeType.get());
            });
        }

        public TGJEI.CategoryBuilder<T> removeRecipes(Supplier<net.minecraft.world.item.crafting.RecipeType<? extends T>> recipeType) {
            return addRecipeListConsumer(recipes -> {
                List<Recipe<?>> excludedRecipes = getTypedRecipes(recipeType.get());
                recipes.removeIf(recipe -> {
                    for (Recipe<?> excludedRecipe : excludedRecipes)
                        if (doInputsMatch(recipe, excludedRecipe) && doOutputsMatch(recipe, excludedRecipe))
                            return true;
                    return false;
                });
            });
        }

        public TGJEI.CategoryBuilder<T> removeNonAutomation() {
            return addRecipeListConsumer(recipes -> recipes.removeIf(AllRecipeTypes.CAN_BE_AUTOMATED.negate()));
        }

        public TGJEI.CategoryBuilder<T> catalystStack(Supplier<ItemStack> supplier) {
            catalysts.add(supplier);
            return this;
        }

        public TGJEI.CategoryBuilder<T> catalyst(Supplier<ItemLike> supplier) {
            return catalystStack(() -> new ItemStack(supplier.get()
                    .asItem()));
        }

        public TGJEI.CategoryBuilder<T> icon(IDrawable icon) {
            this.icon = icon;
            return this;
        }

        public TGJEI.CategoryBuilder<T> itemIcon(ItemLike item) {
            icon(new ItemIcon(() -> new ItemStack(item)));
            return this;
        }

        public TGJEI.CategoryBuilder<T> doubleItemIcon(ItemLike item1, ItemLike item2) {
            icon(new DoubleItemIcon(() -> new ItemStack(item1), () -> new ItemStack(item2)));
            return this;
        }

        public TGJEI.CategoryBuilder<T> background(IDrawable background) {
            this.background = background;
            return this;
        }

        public TGJEI.CategoryBuilder<T> emptyBackground(int width, int height) {
            background(new EmptyBackground(width, height));
            return this;
        }

        public CreateRecipeCategory<T> build(String name, CreateRecipeCategory.Factory<T> factory) {
            Supplier<List<T>> recipesSupplier = () -> {
                List<T> recipes = new ArrayList<>();
                for (Consumer<List<T>> consumer : recipeListConsumers)
                    consumer.accept(recipes);
                return recipes;
            };


            CreateRecipeCategory.Info<T> info = new CreateRecipeCategory.Info<>(
                    new mezz.jei.api.recipe.RecipeType<>(TinkersGears.asResource(name), recipeClass),
                    Component.translatable("recipe.tgears." + name), background, icon, recipesSupplier, catalysts);
            CreateRecipeCategory<T> category = factory.create(info);
            allCategories.add(category);
            return category;
        }
    }
}
