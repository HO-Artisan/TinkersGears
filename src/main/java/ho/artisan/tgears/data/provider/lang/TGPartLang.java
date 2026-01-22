package ho.artisan.tgears.data.provider.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import ho.artisan.tgears.index.TGTinkerItems;
import slimeknights.tconstruct.common.registration.CastItemObject;

public final class TGPartLang extends TGLang {
    public TGPartLang(RegistrateLangProvider provider) {
        super(provider);

        provider.add(TGTinkerItems.HAND_PART.get(), "Hand Part");
        provider.add(TGTinkerItems.PROPELLER_PART.get(), "Propeller Part");
        provider.add(TGTinkerItems.WHISK_PART.get(), "Whisk Part");

        provider.add("pattern.tgears.hand_part", "Hand Part");
        provider.add("pattern.tgears.propeller_part", "Propeller Part");
        provider.add("pattern.tgears.whisk_part", "Whisk Part");

        provider.add("pattern.tgears.tool.hand_part", "Hand Part");
        provider.add("pattern.tgears.tool.propeller_part", "Propeller Part");
        provider.add("pattern.tgears.tool.whisk_part", "Whisk Part");

        cast(provider, TGTinkerItems.HAND_CAST, "Hand");
        cast(provider, TGTinkerItems.PROPELLER_CAST, "Propeller");
        cast(provider, TGTinkerItems.WHISK_CAST, "Whisk");
    }

    private void cast(RegistrateLangProvider provider, CastItemObject cast, String name) {
        provider.add(cast.get(), name + " Gold Cast");
        provider.add(cast.getSand(), name + " Sand Cast");
        provider.add(cast.getRedSand(), name + " Red Sand Cast");
    }
}
