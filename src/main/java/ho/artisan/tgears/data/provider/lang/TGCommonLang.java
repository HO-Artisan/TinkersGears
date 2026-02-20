package ho.artisan.tgears.data.provider.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public final class TGCommonLang extends TGLang {
    public TGCommonLang(RegistrateLangProvider provider) {
        super(provider);
        provider.add("itemGroup.tgears.main", "Tinker's Gears");
        provider.add("gui.tgears.integrated_smeltery", "Integrated Smeltery");

        provider.add("create.generic.unit.percent", "%");
        provider.add("create.generic.unit.temperature", "℃");

        provider.add("create.tooltip.gears.tinker_stats", "Tinker Stats:");
        provider.add("create.tooltip.gears.tinker_stats.fluid", "Fluid:");
        provider.add("create.tooltip.gears.temperature", "Temperature:");
        provider.add("create.tooltip.gears.rate", "Rate:");
        provider.add("create.tooltip.gears.progress", "Progress:");
        provider.add("create.tooltip.gears.output", "Output:");

        provider.add("create.gui.goggles.at_current_heating", "at current heating");
        provider.add("create.gui.goggles.at_current_recipe", "at current recipe");
    }
}
