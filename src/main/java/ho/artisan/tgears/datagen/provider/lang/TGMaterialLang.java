package ho.artisan.tgears.datagen.provider.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public final class TGMaterialLang extends TGLang {
    public TGMaterialLang(RegistrateLangProvider provider) {
        super(provider);
        provider.add("material.tgears.andesite_alloy", "Andesite Alloy");
        provider.add("material.tgears.andesite_alloy.flavor", "Sturdier Rocks");
        provider.add("material.tgears.andesite_alloy.encyclopedia", "It is durable!");

        provider.add("material.tgears.cardboard", "Cardboard");
        provider.add("material.tgears.cardboard.flavor", "Unadulterated Lightweight");
        provider.add("material.tgears.cardboard.encyclopedia", "Harmless but ultra-light!");

        provider.add("material.tgears.rose_quartz", "Rose Quartz");
        provider.add("material.tgears.rose_quartz.flavor", "A Unique Crystal");
        provider.add("material.tgears.rose_quartz.encyclopedia", "A unique crystal with a sharp quality.");
    }
}
