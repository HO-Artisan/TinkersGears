package ho.artisan.tgears.common.modifier;

import ho.artisan.tgears.TinkersGearsConfig;
import net.minecraft.network.chat.Component;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.impl.DurabilityShieldModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

import javax.annotation.Nullable;

public class CreatePolishedModifier extends DurabilityShieldModifier {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
    }

    @Override
    public Component getDisplayName(int level) {
        // display name without the level
        return super.getDisplayName();
    }

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public int getShieldCapacity(IToolStackView tool, ModifierEntry modifier) {
        return (int) (tool.getStats().getInt(ToolStats.DURABILITY) * TinkersGearsConfig.POLISHING_SHIELD.get());
    }

    @Nullable
    @Override
    public Boolean showDurabilityBar(IToolStackView tool, ModifierEntry modifier) {
        // only show if we have any shield
        return getShield(tool) > 0 ? true : null;
    }

    @Override
    public int getDurabilityRGB(IToolStackView tool, ModifierEntry modifier) {
        if (getShield(tool) > 0) {
            // just always display light blue, not much point in color changing really
            return 0xe0e0e0;
        }
        return -1;
    }

    public void refreshShield(IToolStackView tool, ModifierEntry modifier) {
        setShield(tool, modifier, getShieldCapacity(tool, modifier));
    }
}
