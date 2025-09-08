package ho.artisan.tgears.common.modifier;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.EquipmentChangeModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.UUID;
import java.util.function.Supplier;

public class CreateExtendoModifier extends NoLevelsModifier implements EquipmentChangeModifierHook {
    public static final String EXTENDO_MARKER = "createExtendo";
    public static final AttributeModifier MODIFIER = new AttributeModifier(
            UUID.fromString("505501b0-7368-498e-89c3-1723ef0f73e6"), "Tinker Range modifier", 3,
            AttributeModifier.Operation.ADDITION
    );

    private static final Supplier<Multimap<Attribute, AttributeModifier>> MEMOIZED = Suppliers.memoize(() ->
            // Holding an ExtendoGrip
            ImmutableMultimap.of(ForgeMod.BLOCK_REACH.get(), MODIFIER));



    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.EQUIPMENT_CHANGE);
    }

    @Override
    public void onEquip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        if (context.getEntity() instanceof Player player) {
            player.getAttributes().addTransientAttributeModifiers(MEMOIZED.get());
            player.getPersistentData().putBoolean(EXTENDO_MARKER, true);
        }
    }

    @Override
    public void onUnequip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        if (context.getEntity() instanceof Player player) {
            player.getAttributes().removeAttributeModifiers(MEMOIZED.get());
            player.getPersistentData().remove(EXTENDO_MARKER);
        }
    }
}
