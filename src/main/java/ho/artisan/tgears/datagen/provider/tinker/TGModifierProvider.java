package ho.artisan.tgears.datagen.provider.tinker;

import ho.artisan.tgears.index.TGModifiers;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.modules.build.StatBoostModule;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class TGModifierProvider extends AbstractModifierProvider {
    public TGModifierProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public String getName() {
        return "TG Modifiers";
    }

    @Override
    protected void addModifiers() {
        buildModifier(TGModifiers.Ids.DIVING)
                .levelDisplay(ModifierLevelDisplay.SINGLE_LEVEL)
                .build();
        buildModifier(TGModifiers.Ids.GOGGLES)
                .levelDisplay(ModifierLevelDisplay.SINGLE_LEVEL)
                .build();
        buildModifier(TGModifiers.Ids.ROSE_BLADE)
                .addModule(StatBoostModule.add(ToolStats.ATTACK_DAMAGE).eachLevel(0.1F))
                .build();
    }
}
