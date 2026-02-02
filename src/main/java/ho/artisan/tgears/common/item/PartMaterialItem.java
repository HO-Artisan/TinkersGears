package ho.artisan.tgears.common.item;

import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.part.MaterialItem;

public class PartMaterialItem extends MaterialItem {
    private final MaterialStatsId id;

    public PartMaterialItem(Properties properties, MaterialStatsId id) {
        super(properties);
        this.id = id;
    }

    @Override
    public boolean canUseMaterial(MaterialId material) {
        return MaterialRegistry.getInstance()
                .getAllStats(material)
                .stream()
                .anyMatch(s -> s.getIdentifier().equals(id));
    }
}
