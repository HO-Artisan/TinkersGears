package ho.artisan.tgears.datagen.provider.tag;

import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.core.Holder;

import java.util.function.Function;

public abstract class TGTagGen<T> {
    protected final TagGen.CreateTagsProvider<T> prov;

    public TGTagGen(RegistrateTagsProvider.IntrinsicImpl<T> provIn, Function<T, Holder.Reference<T>> builtInRegistryHolder) {
        prov = new TagGen.CreateTagsProvider<>(provIn, builtInRegistryHolder);
    }
}
