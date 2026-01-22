package ho.artisan.tgears.data.provider.tinker;

import ho.artisan.tgears.index.TGMaterials;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.json.JsonRedirect;

import static ho.artisan.tgears.compat.TinkersGearsCompat.TI;

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
                new JsonRedirect(ResourceLocation.tryBuild(TI, "andesite_alloy"), new ModLoadedCondition(TI))
        );

        addMaterial(TGMaterials.Ids.BRASS, 2, ORDER_GENERAL, false, false,
                null,
                new JsonRedirect(ResourceLocation.tryBuild(TI, "brass"), new ModLoadedCondition(TI))
        );

        addMaterial(TGMaterials.Ids.CARDBOARD, 2, ORDER_GENERAL, true);

        // tier 3
        addMaterial(TGMaterials.Ids.ROSE_QUARTZ, 3, ORDER_GENERAL, true);
    }
}
