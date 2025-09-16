package ho.artisan.tgears.common.modifier;

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ModifierRemovalHook;
import slimeknights.tconstruct.library.modifiers.hook.display.DurabilityDisplayModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import static ho.artisan.tgears.util.StressUtil.*;

public class StressDrivenModifier extends NoLevelsModifier
        implements ModifierRemovalHook, DurabilityDisplayModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(
                this,
                ModifierHooks.REMOVE,
                ModifierHooks.DURABILITY_DISPLAY
        );
    }

    @Override
    public @Nullable Component onRemoved(IToolStackView tool, Modifier modifier) {
        removeStress(tool.getPersistentData());
        return null;
    }

    @Override
    public int getPriority() {
        return 130;
    }

    @Override
    public @Nullable Boolean showDurabilityBar(IToolStackView tool, ModifierEntry modifier) {
        return getStress(tool.getPersistentData()) < getStressCapacity(tool.getPersistentData()) ? true : null;
    }

    @Override
    public int getDurabilityRGB(IToolStackView tool, ModifierEntry modifier) {
        if (getStress(tool.getPersistentData()) > 0) {
            return 0xFFD000;
        }
        return -1;
    }

    @Override
    public int getDurabilityWidth(IToolStackView tool, ModifierEntry modifier) {
        int stress = getStress(tool.getPersistentData());
        if (stress > 0) {
            return DurabilityDisplayModifierHook.getWidthFor(stress, getStressCapacity(tool.getPersistentData()));
        }
        return 0;
    }
}
