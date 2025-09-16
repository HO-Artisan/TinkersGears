package ho.artisan.tgears.util;

import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.mantle.registration.object.FluidObject;

public final class RegistryUtil {

    private RegistryUtil() {}

    public static <T> T find(IForgeRegistry<T> registry, ResourceLocation id) {
        return registry.getValue(id);
    }

    public static <F extends ForgeFlowingFluid> FluidObject<F> transform(FluidEntry<F> entry) {
        return new FluidObject<>(
                entry.getId(),
                null,
                entry::getType,
                entry::getSource
        );
    }
}
