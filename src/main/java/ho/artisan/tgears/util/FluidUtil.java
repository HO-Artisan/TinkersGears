package ho.artisan.tgears.util;

import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.object.FluidObject;

public final class FluidUtil {

    public static <F extends ForgeFlowingFluid> FluidObject<F> object(FluidEntry<F> entry) {
        return new FluidObject<>(entry.getId(), null, entry::getType, entry);
    }

    public static <F extends ForgeFlowingFluid> FluidObject<F> tag(FluidEntry<F> entry, String tag) {
        return new FluidObject<>(entry.getId(), tag, entry::getType, entry);
    }
}
