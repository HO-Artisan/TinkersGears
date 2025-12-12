package ho.artisan.tgears.datagen.provider.tinker;

import ho.artisan.tgears.index.TGMaterials;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.json.JsonRedirect;

public final class TGMaterialDataProvider extends AbstractMaterialDataProvider {
    public static TGMaterialDataProvider INSTANCE;

    public TGMaterialDataProvider(PackOutput packOutput) {
        super(packOutput);
        INSTANCE = this;
    }

    @Override
    public String getName() {
        return "TG Materials";
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
