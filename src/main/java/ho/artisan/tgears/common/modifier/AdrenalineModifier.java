package ho.artisan.tgears.common.modifier;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import ho.artisan.tgears.TinkersGearsConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.EquipmentChangeModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InventoryTickModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.UUID;

public class AdrenalineModifier extends NoLevelsModifier implements
        OnAttackedModifierHook, EquipmentChangeModifierHook, InventoryTickModifierHook {

    public static final UUID MOVEMENT_SPEED_UUID = UUID.fromString("3dd339d8-0701-46a5-8845-176e53a8907f");

    public static final UUID ATTACK_SPEED_UUID = UUID.fromString("3ce5c381-62e4-4b36-af49-7aa7a61d8fda");

    public static final UUID ATTACK_DAMAGE_UUID = UUID.fromString("3e391f64-f011-45f4-8a89-2c07b2c02c08");

    private Multimap<Attribute, AttributeModifier> attributeModifiers(LivingEntity entity, int level) {
        var builder = ImmutableMultimap.<Attribute, AttributeModifier>builder()
                .put(Attributes.MOVEMENT_SPEED, new AttributeModifier(
                        MOVEMENT_SPEED_UUID,
                        "Adrenaline movement speed modifier",
                        entity.getAttributeValue(Attributes.MOVEMENT_SPEED) * TinkersGearsConfig.ADRENALINE_MULTIPLIER_PER_LEVEL.get() * level,
                        AttributeModifier.Operation.ADDITION
                ))
                .put(Attributes.ATTACK_SPEED, new AttributeModifier(
                        ATTACK_SPEED_UUID,
                        "Adrenaline attack speed modifier",
                        entity.getAttributeValue(Attributes.ATTACK_SPEED) * TinkersGearsConfig.ADRENALINE_MULTIPLIER_PER_LEVEL.get() * level,
                        AttributeModifier.Operation.ADDITION
                ));
        if (TinkersGearsConfig.ADRENALINE_DAMAGE_FLAG.get()) {
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                    ATTACK_DAMAGE_UUID,
                    "Adrenaline attack damage modifier",
                    entity.getAttributeValue(Attributes.ATTACK_DAMAGE) * TinkersGearsConfig.ADRENALINE_MULTIPLIER_PER_LEVEL.get() * level,
                    AttributeModifier.Operation.ADDITION
            ));
        }
        return builder.build();
    }

    @Override
    public int getPriority() {
        return 90;
    }

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(
                this,
                ModifierHooks.ON_ATTACKED,
                ModifierHooks.EQUIPMENT_CHANGE
        );
    }

    @Override
    public void onAttacked(IToolStackView tool, ModifierEntry modifier, EquipmentContext context, EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        LivingEntity entity = context.getEntity();
        CompoundTag data = entity.getPersistentData();
        int adrenaline = data.getInt("Adrenaline");
        boolean flag = data.getBoolean("AdrenalineFlag");

        if (entity.getRandom().nextFloat() < TinkersGearsConfig.ADRENALINE_CHANCE.get()) {
            adrenaline = Math.min(adrenaline + 1, TinkersGearsConfig.ADRENALINE_MAX_LEVEL.get());

            data.putInt("Adrenaline", adrenaline);
        }

        if (adrenaline > 0) {
            entity.getAttributes().addTransientAttributeModifiers(attributeModifiers(entity, adrenaline));
        }
    }

    @Override
    public void onUnequip(IToolStackView tool, ModifierEntry modifier, EquipmentChangeContext context) {
        LivingEntity entity = context.getEntity();
        int adrenaline = entity.getPersistentData().getInt("Adrenaline");

        entity.getAttributes().removeAttributeModifiers(attributeModifiers(entity, adrenaline));
    }


    @Override
    public void onInventoryTick(IToolStackView tool, ModifierEntry modifier, Level world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        CompoundTag data = holder.getPersistentData();
        int adrenaline = data.getInt("Adrenaline");

        if (adrenaline > 0) {
            int timer = data.getInt("AdrenalineTimer");

            timer++;
            if (timer >= 60) {
                data.putInt("AdrenalineTimer", 0);

                adrenaline--;

                if (adrenaline <= 0) {
                    holder.getAttributes().removeAttributeModifiers(attributeModifiers(holder, adrenaline));
                }

                data.putInt("Adrenaline", adrenaline);
            }
        }
    }
}
