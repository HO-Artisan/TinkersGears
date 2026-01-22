package ho.artisan.tgears.tools;

import ho.artisan.tgears.TinkersGears;
import net.minecraft.network.chat.Component;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatType;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import java.util.List;

public enum PartMaterialStats implements IMaterialStats {
    HAND("hand_part"),
    PROPELLER("propeller_part"),
    WHISK("whisk_part"),
    ;
    private static final List<Component> LOCALIZED = List.of(IMaterialStats.makeTooltip(TConstruct.getResource("extra.no_stats")));
    private static final List<Component> DESCRIPTION = List.of(Component.empty());

    public static void register() {
        for (PartMaterialStats value : PartMaterialStats.values()) {
            MaterialRegistry.getInstance().registerStatType(value.getType());
        }
    }

    private final MaterialStatType<PartMaterialStats> type;

    PartMaterialStats(String name) {
        this.type = MaterialStatType.singleton(new MaterialStatsId(TinkersGears.asResource(name)), this);
    }

    @Override
    public MaterialStatType<?> getType() {
        return this.type;
    }

    @Override
    public List<Component> getLocalizedInfo() {
        return LOCALIZED;
    }

    @Override
    public List<Component> getLocalizedDescriptions() {
        return DESCRIPTION;
    }

    @Override
    public void apply(ModifierStatsBuilder builder, float scale) {
    }
}
