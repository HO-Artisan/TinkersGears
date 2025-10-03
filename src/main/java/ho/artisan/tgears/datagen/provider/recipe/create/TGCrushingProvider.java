package ho.artisan.tgears.datagen.provider.recipe.create;

import com.simibubi.create.AllItems;
import com.simibubi.create.api.data.recipe.CrushingRecipeGen;
import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import slimeknights.tconstruct.world.TinkerWorld;

public class TGCrushingProvider extends CrushingRecipeGen {
    public TGCrushingProvider(PackOutput generator) {
        super(generator, TinkersGears.MOD_ID);
        createRecipes();
    }

    private void createRecipes() {
        oreCrushingRecipe("cobalt", TinkerWorld.cobaltOre, TGItems.CRUSHED_RAW_COBALT, Items.NETHERRACK);

        create("scorchia", b -> b.require(AllPaletteStoneTypes.SCORCHIA.baseBlock.get())
                .duration(100)
                .output(TGItems.CRUSHED_SCORCHIA)
        );
    }

    protected void oreCrushingRecipe(String id, ItemLike ore, ItemLike crushedOre, ItemLike rock) {
        create(id + "/ore", b -> b.require(ore)
                .duration(250)
                .output(crushedOre)
                .output(0.75F, crushedOre)
                .output(0.75F, AllItems.EXP_NUGGET)
                .output(0.125F, rock)
        );

        create(id + "/raw_ore", b -> b.require(TagKey.create(Registries.ITEM, new ResourceLocation("forge", "raw_materials/" + id)))
                .duration(400)
                .output(crushedOre)
                .output(0.75F, AllItems.EXP_NUGGET)
        );

        create(id + "/raw_ore_block", b -> b.require(TagKey.create(Registries.ITEM, new ResourceLocation("forge", "storage_blocks/raw_" + id)))
                .duration(400)
                .output(crushedOre, 9)
                .output(0.75F, AllItems.EXP_NUGGET, 9)
        );
    }
}
