package ho.artisan.tgears.data.provider.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public final class TGMaterialLang extends TGLang {
    public TGMaterialLang(RegistrateLangProvider provider) {
        super(provider);
        provider.add("material.tgears.andesite_alloy", "Andesite Alloy");
        provider.add("material.tgears.andesite_alloy.flavor", "Sturdier Rocks");
        provider.add("material.tgears.andesite_alloy.encyclopedia", "It is highly durable, increasing durability, attack damage and projectile damage by 5% per level.");

        provider.add("material.tgears.cardboard", "Cardboard");
        provider.add("material.tgears.cardboard.flavor", "Harmless but Ultra-light!");
        provider.add("material.tgears.cardboard.encyclopedia", "It takes No Damage with head part, but it has unmatched attack speed and knockback power.");

        provider.add("material.tgears.brass", "Brass");
        provider.add("material.tgears.brass.flavor", "Punk Manufacturing");
        provider.add("material.tgears.brass.encyclopedia", "It has 5% increasing durability  and 25% multiplying tool repair per level");

        provider.add("material.tgears.rose_quartz", "Rose Quartz");
        provider.add("material.tgears.rose_quartz.flavor", "A Unique Hard Crystal");
        provider.add("material.tgears.rose_quartz.encyclopedia", "With 10% multiplying attack damage per level (projectile double), it stands out.");
    }
}
