package ho.artisan.tgears.datagen.provider.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.ponder.TGPonderPlugin;
import net.createmod.ponder.foundation.PonderIndex;

public final class TGPonderLang extends TGLang {
    public TGPonderLang(RegistrateLangProvider provider) {
        super(provider);
        PonderIndex.addPlugin(new TGPonderPlugin());
        PonderIndex.getLangAccess().provideLang(TinkersGears.MOD_ID, provider::add);
    }
}
