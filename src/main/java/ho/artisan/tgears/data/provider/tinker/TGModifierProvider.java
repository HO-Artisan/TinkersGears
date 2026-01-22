package ho.artisan.tgears.data.provider.tinker;

import ho.artisan.tgears.index.TGModifiers;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.modules.behavior.RepairModule;
import slimeknights.tconstruct.library.modifiers.modules.build.StatBoostModule;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public final class TGModifierProvider extends AbstractModifierProvider {
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
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .build();
        buildModifier(TGModifiers.Ids.WRENCH)
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .build();
        buildModifier(TGModifiers.Ids.GOGGLES)
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .build();
        buildModifier(TGModifiers.Ids.ROSE_BLADE)
                .addModule(StatBoostModule.add(ToolStats.ATTACK_DAMAGE).eachLevel(0.1F))
                .addModule(StatBoostModule.add(ToolStats.PROJECTILE_DAMAGE).eachLevel(0.2F))
                .build();
        buildModifier(TGModifiers.Ids.TOPNOTCH)
                .addModule(StatBoostModule.add(ToolStats.DURABILITY).eachLevel(0.05F))
                .addModule(StatBoostModule.add(ToolStats.ATTACK_DAMAGE).eachLevel(0.05F))
                .addModule(StatBoostModule.add(ToolStats.PROJECTILE_DAMAGE).eachLevel(0.05F))
                .build();
        buildModifier(TGModifiers.Ids.PRECISION)
                .addModule(StatBoostModule.add(ToolStats.DURABILITY).eachLevel(0.1F))
                .addModule(RepairModule.builder().eachLevel(0.25f));
    }
}
