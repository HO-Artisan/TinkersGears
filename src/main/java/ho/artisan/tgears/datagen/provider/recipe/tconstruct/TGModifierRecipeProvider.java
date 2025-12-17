package ho.artisan.tgears.datagen.provider.recipe.tconstruct;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import ho.artisan.tgears.datagen.provider.recipe.TGRecipeProvider;
import ho.artisan.tgears.index.TGModifiers;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.function.Consumer;

public class TGModifierRecipeProvider extends TGRecipeProvider {

    public TGModifierRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    public String getType() {
        return "tinker/modifier";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        upgradeRecipes(consumer);
        slotlessRecipes(consumer);
        abilityRecipes(consumer);
    }

    private void upgradeRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "upgrades/";

        ModifierRecipeBuilder.modifier(TGModifiers.Ids.DIVING)
                .addInput(AllItems.ANDESITE_ALLOY)
                .addInput(AllItems.ANDESITE_ALLOY)
                .addInput(Items.IRON_NUGGET)
                .addInput(Items.IRON_NUGGET)
                .exactLevel(1)
                .setSlots(SlotType.UPGRADE, 1)
                .setTools(TinkerTags.Items.BOOTS)
                .disallowCrystal()
                .save(consumer, location(folder + "diving"));

        ModifierRecipeBuilder.modifier(TGModifiers.Ids.EXTENDO)
                .addInput(AllItems.EXTENDO_GRIP)
                .addInput(AllItems.EXTENDO_GRIP)
                .addInput(AllBlocks.COGWHEEL)
                .addInput(AllBlocks.LARGE_COGWHEEL)
                .exactLevel(1)
                .setSlots(SlotType.UPGRADE, 1)
                .setTools(TinkerTags.Items.CHESTPLATES)
                .disallowCrystal()
                .save(consumer, location(folder + "extendo"));
    }

    private void slotlessRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "slotless/";

        ModifierRecipeBuilder.modifier(TGModifiers.Ids.GOGGLES)
                .addInput(AllItems.GOGGLES)
                .exactLevel(1)
                .setTools(TinkerTags.Items.HELMETS)
                .disallowCrystal()
                .save(consumer, location(folder + "goggles"));
    }

    private void abilityRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "ability/";

        ModifierRecipeBuilder.modifier(TGModifiers.Ids.CRUSHING)
                .addInput(AllBlocks.CRUSHING_WHEEL)
                .addInput(AllBlocks.CRUSHING_WHEEL)
                .addInput(Tags.Items.INGOTS)
                .addInput(AllBlocks.COGWHEEL)
                .addInput(AllBlocks.LARGE_COGWHEEL)
                .exactLevel(1)
                .setTools(Ingredient.of(TinkerTools.pickaxe))
                .disallowCrystal()
                .save(consumer, location(folder + "crushing"));
    }
}
