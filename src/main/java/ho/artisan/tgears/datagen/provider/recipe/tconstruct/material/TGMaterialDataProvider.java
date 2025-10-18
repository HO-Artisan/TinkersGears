package ho.artisan.tgears.datagen.provider.recipe.tconstruct.material;

import ho.artisan.tgears.index.TGMaterials;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import slimeknights.tconstruct.library.json.JsonRedirect;
import slimeknights.tconstruct.tools.data.material.MaterialDataProvider;

public final class TGMaterialDataProvider extends MaterialDataProvider {
    public TGMaterialDataProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public String getName() {
        return "TGears Materials";
    }

    @Override
    protected void addMaterials() {
        // tier 2
        addMaterial(TGMaterials.Ids.ANDESITE_ALLOY, 2, ORDER_GENERAL, true, false,
                null,
                new JsonRedirect(new ResourceLocation("tinkersinnovation", "andesite_alloy"), new ModLoadedCondition("tinkersinnovation"))
        );

        addMaterial(
                TGMaterials.Ids.CARDBOARD, 2, ORDER_GENERAL, true);

        // tier 3
        addMaterial(TGMaterials.Ids.ROSE_QUARTZ, 3, ORDER_GENERAL, true);

        // tier 4
        //addMaterial(TGMaterials.Ids.LUZZIUM, 4, ORDER_GENERAL, false);
    }
}
