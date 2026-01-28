package ho.artisan.tgears.mixin;

import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ProcessingRecipe.class)
public abstract class ProcessingRecipeSupport {

    @Shadow(remap = false)
    protected NonNullList<Ingredient> ingredients;

    @Shadow(remap = false)
    protected NonNullList<ProcessingOutput> results;
}
