package ho.artisan.tgears.plugin.jei;

import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.kinetics.fan.processing.HauntingRecipe;
import com.simibubi.create.content.kinetics.fan.processing.SplashingRecipe;
import com.simibubi.create.foundation.utility.CreateLang;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;

import java.util.function.Supplier;

@JeiPlugin
public class TGJEI implements IModPlugin {
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

        registerFanCatalyst(registration, "fan_blasting", AbstractCookingRecipe.class);
        registerFanCatalyst(registration, "fan_smoking", SmokingRecipe.class);
        registerFanCatalyst(registration, "fan_washing", SplashingRecipe.class);
        registerFanCatalyst(registration, "fan_haunting", HauntingRecipe.class);
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
}
