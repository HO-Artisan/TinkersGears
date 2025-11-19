package ho.artisan.tgears.plugin.jei.category;

import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import com.simibubi.create.content.processing.recipe.ProcessingOutput;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import ho.artisan.tgears.common.recipe.SilkyCrushingRecipe;
import ho.artisan.tgears.plugin.jei.category.animations.AnimatedSilkyCrushingWheels;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.createmod.catnip.layout.LayoutHelper;
import net.minecraft.client.gui.GuiGraphics;

import java.util.ArrayList;
import java.util.List;

public class SilkyCrushingCategory extends CreateRecipeCategory<SilkyCrushingRecipe> {
    private final AnimatedSilkyCrushingWheels crushingWheels = new AnimatedSilkyCrushingWheels();

    public SilkyCrushingCategory(Info<SilkyCrushingRecipe> info) {
        super(info);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SilkyCrushingRecipe recipe, IFocusGroup focuses) {
        builder
                .addSlot(RecipeIngredientRole.INPUT, 51, 3)
                .setBackground(getRenderedSlot(), -1, -1)
                .addIngredients(recipe.getIngredients().get(0));

        int xOffset = getBackground().getWidth() / 2;
        int yOffset = 86;

        layoutOutput(recipe).forEach(layoutEntry -> builder
                .addSlot(RecipeIngredientRole.OUTPUT, (xOffset) + layoutEntry.posX() + 1, yOffset + layoutEntry.posY() + 1)
                .setBackground(getRenderedSlot(layoutEntry.output()), -1, -1)
                .addItemStack(layoutEntry.output().getStack())
                .addRichTooltipCallback(addStochasticTooltip(layoutEntry.output()))
        );
    }

    private List<SilkyCrushingCategory.LayoutEntry> layoutOutput(ProcessingRecipe<?> recipe) {
        int size = recipe.getRollableResults().size();
        List<SilkyCrushingCategory.LayoutEntry> positions = new ArrayList<>(size);

        LayoutHelper layout = LayoutHelper.centeredHorizontal(size, 1, 18, 18, 1);
        for (ProcessingOutput result : recipe.getRollableResults()) {
            positions.add(new SilkyCrushingCategory.LayoutEntry(result, layout.getX(), layout.getY()));
            layout.next();
        }

        return positions;
    }

    private record LayoutEntry(
            ProcessingOutput output,
            int posX,
            int posY
    ) {
    }

    @Override
    public void draw(SilkyCrushingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
        AllGuiTextures.JEI_DOWN_ARROW.render(graphics, 72, 7);

        crushingWheels.draw(graphics, 62, 59);
    }
}
