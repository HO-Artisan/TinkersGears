package ho.artisan.tgears.common.item;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.util.RegistryUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class SturdyWhiskCastItem extends WhiskCastItem implements CompatItem {
    public SturdyWhiskCastItem(Properties properties) {
        super(RegistryUtil.find(ForgeRegistries.ITEMS, new ResourceLocation("createmetallurgy", "sturdy_whisk")), properties);
    }

    @Override
    public boolean shouldLoad() {
        return TinkersGears.METALLURGY_LOADED;
    }
}
