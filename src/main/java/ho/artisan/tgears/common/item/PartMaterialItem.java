package ho.artisan.tgears.common.item;

import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.library.tools.part.MaterialItem;

public class PartMaterialItem extends MaterialItem {
    private final IMaterialStats stats;

    public PartMaterialItem(Properties properties, IMaterialStats stats) {
        super(properties);
        this.stats = stats;
    }


    @Override
    public ItemStack withMaterial(MaterialVariantId material) {
        return super.withMaterial(material);
    }

    @Override
    public boolean canUseMaterial(MaterialId material) {
        return MaterialRegistry.getInstance()
                .getAllStats(material)
                .stream()
                .anyMatch(s -> s == this.stats);
    }
}
