package ho.artisan.tgears.datagen.provider.tinker;

import ho.artisan.tgears.index.TGMaterials;
import ho.artisan.tgears.index.TGModifiers;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.tools.TinkerModifiers;

import static slimeknights.tconstruct.library.materials.MaterialRegistry.MELEE_HARVEST;

public final class TGMaterialTraitsDataProvider extends AbstractMaterialTraitDataProvider {
    public TGMaterialTraitsDataProvider(PackOutput packOutput) {
        super(packOutput, TGMaterialDataProvider.INSTANCE);
    }

    @Override
    public String getName() {
        return "TG Material Traits";
    }

    @Override
    protected void addMaterialTraits() {
        addDefaultTraits(TGMaterials.Ids.ANDESITE_ALLOY, TGModifiers.Ids.TOPNOTCH);

        addTraits(
                TGMaterials.Ids.CARDBOARD,
                MELEE_HARVEST,
                TinkerModifiers.knockback.getId()
        );

        addTraits(
                TGMaterials.Ids.ROSE_QUARTZ,
                MELEE_HARVEST,
                new ModifierEntry(TGModifiers.Ids.ROSE_BLADE, 1)
        );

        /*
        addDefaultTraits(TGMaterials.Ids.LUZZIUM, TGModifiers.Ids.LIGHTWEIGHT);

        addTraits(
                TGMaterials.Ids.LUZZIUM,
                ARMOR,
                TGModifiers.Ids.ADRENALINE
        );
         */
    }
}
