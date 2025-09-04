package ho.artisan.tgears.common.register;

import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.tools.data.material.MaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.*;

import static net.minecraft.world.item.Tiers.IRON;

public class TGearMaterialStatsProvider extends MaterialStatsDataProvider {
    public TGearMaterialStatsProvider(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    protected void addMaterialStats() {
        addMaterialStats(TGearMaterialIds.AndesiteAlloy,
                new HeadMaterialStats(220, 5.5F, IRON, 1.5F),
                new LimbMaterialStats(220, -0.2F, 0.1F, 0.0F),
                new GripMaterialStats(0.1F, 0.0F, 1.5F),
                new HandleMaterialStats(0.15F, 0F, 0F, -0.05F),
                StatlessMaterialStats.BINDING
        );
    }

    @Override
    public String getName() {
        return "TGear's Material Stats";
    }
}