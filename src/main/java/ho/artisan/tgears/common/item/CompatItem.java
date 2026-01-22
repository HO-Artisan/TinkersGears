package ho.artisan.tgears.common.item;

import net.minecraftforge.fml.ModList;

public interface CompatItem {
    default boolean shouldLoad() {
        return ModList.get().isLoaded(getModid());
    }

    String getModid();
}
