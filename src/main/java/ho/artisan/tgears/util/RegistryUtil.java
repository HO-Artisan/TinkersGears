package ho.artisan.tgears.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;

public class RegistryUtil {
    public static <T> T find(IForgeRegistry<T> registry, ResourceLocation id) {
        return registry.getValue(id);
    }
}
