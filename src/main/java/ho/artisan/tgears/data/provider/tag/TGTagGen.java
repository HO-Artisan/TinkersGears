package ho.artisan.tgears.data.provider.tag;

import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;

import java.util.function.Function;

public abstract class TGTagGen<T> {
    protected final Provider<T> prov;

    public TGTagGen(RegistrateTagsProvider.IntrinsicImpl<T> provIn, Function<T, ResourceKey<T>> resourceKeyFunction) {
        prov = new Provider<>(provIn, resourceKeyFunction);
    }

    public record Provider<T>(RegistrateTagsProvider<T> provider, Function<T, ResourceKey<T>> keyExtractor) {
        public TagGen.CreateTagAppender<T> tag(TagKey<T> tag) {
            TagBuilder tagbuilder = getOrCreateRawBuilder(tag);
            return new TagGen.CreateTagAppender<>(tagbuilder, keyExtractor, Create.ID);
        }

        public TagBuilder getOrCreateRawBuilder(TagKey<T> tag) {
            return provider.addTag(tag).getInternalBuilder();
        }
    }
}
