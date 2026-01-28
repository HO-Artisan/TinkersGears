package ho.artisan.tgears.data.provider.tinker;

import ho.artisan.tgears.index.TGMaterials;
import ho.artisan.tgears.index.TGModifiers;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;

import static slimeknights.tconstruct.library.materials.MaterialRegistry.*;

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
        addDefaultTraits(TGMaterials.Ids.BRASS, TGModifiers.Ids.PRECISION);

        addDefaultTraits(TGMaterials.Ids.CHOCOLATE, TGModifiers.Ids.NOURISHING);
        addDefaultTraits(TGMaterials.Ids.BLAZING_CHOCOLATE, TGModifiers.Ids.NOURISHING);

        addTraits(
                TGMaterials.Ids.BLAZING_CHOCOLATE,
                MELEE_HARVEST,
                ModifierIds.fiery
        );

        addDefaultTraits(TGMaterials.Ids.CARDBOARD, ModifierIds.writable);
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
        addTraits(
                TGMaterials.Ids.ROSE_QUARTZ,
                AMMO,
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
