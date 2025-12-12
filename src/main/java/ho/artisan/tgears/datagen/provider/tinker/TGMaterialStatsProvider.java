package ho.artisan.tgears.datagen.provider.tinker;

import ho.artisan.tgears.index.TGMaterials;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ArmorItem;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.*;

import static net.minecraft.world.item.Tiers.IRON;
import static net.minecraft.world.item.Tiers.WOOD;

public final class TGMaterialStatsProvider extends AbstractMaterialStatsDataProvider {
    public TGMaterialStatsProvider(PackOutput packOutput) {
        super(packOutput, TGMaterialDataProvider.INSTANCE);
    }

    @Override
    public String getName() {
        return "TG Material Stats";
    }

    @Override
    protected void addMaterialStats() {
        addMaterialStats(TGMaterials.Ids.ANDESITE_ALLOY,
                new HeadMaterialStats(220, 5.5F, IRON, 1.5F),
                new LimbMaterialStats(220, -0.2F, 0.1F, 0.0F),
                new GripMaterialStats(0.1F, 0.0F, 1.5F),
                new HandleMaterialStats(0.15F, 0F, 0F, -0.05F),
                StatlessMaterialStats.BINDING
        );

        PlatingMaterialStats.Builder platingMaterialStats = PlatingMaterialStats.builder()
                .armor(1F, 1F, 1F, 1F).durabilityFactor(2);

        addMaterialStats(TGMaterials.Ids.CARDBOARD,
                new HeadMaterialStats(100, 7.0F, WOOD, 0.0F),
                new HandleMaterialStats(0.0F, 0.2F, 0.2F, -0.5F),
                platingMaterialStats.build(ArmorItem.Type.HELMET),
                platingMaterialStats.build(ArmorItem.Type.CHESTPLATE),
                platingMaterialStats.build(ArmorItem.Type.LEGGINGS),
                platingMaterialStats.build(ArmorItem.Type.BOOTS),
                platingMaterialStats.buildShield(),
                StatlessMaterialStats.BINDING,
                StatlessMaterialStats.MAILLE
        );

        addMaterialStats(TGMaterials.Ids.ROSE_QUARTZ,
                new HeadMaterialStats(165, 5.5F, IRON, 3.5F),
                new HandleMaterialStats(-0.25F, 0.0F, 0.1F, -0.1F),
                StatlessMaterialStats.BINDING
        );

        /*
        PlatingMaterialStats.Builder platingMaterialStats = PlatingMaterialStats.builder()
                .armor(2F, 7F, 5F, 2F).toughness(1.25F).durabilityFactor(35);

        addMaterialStats(TGMaterials.Ids.LUZZIUM,
                new HeadMaterialStats(1100, 7.5F, DIAMOND, 2.75F),
                new HandleMaterialStats(0.1F, 0.1F, 0.15F, 0F),
                new LimbMaterialStats(1100, 0.05F, 0.1F, -0.1F),
                new GripMaterialStats(0.1F, -0.1F, 2.5F),
                platingMaterialStats.build(ArmorItem.Type.HELMET),
                platingMaterialStats.build(ArmorItem.Type.CHESTPLATE),
                platingMaterialStats.build(ArmorItem.Type.LEGGINGS),
                platingMaterialStats.build(ArmorItem.Type.BOOTS),
                platingMaterialStats.buildShield(),
                StatlessMaterialStats.BINDING,
                StatlessMaterialStats.MAILLE
        );
         */
    }
}