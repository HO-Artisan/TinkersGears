package ho.artisan.tgears.mixin;

import com.simibubi.create.content.equipment.sandPaper.SandPaperPolishingRecipe;
import ho.artisan.tgears.TinkersGearsConfig;
import ho.artisan.tgears.common.modifier.CreatePolishedModifier;
import ho.artisan.tgears.index.TGModifiers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

@Mixin(SandPaperPolishingRecipe.class)
public class PolishingApplying {

    @Inject(
            remap = false,
            method = "applyPolish",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void applyPolishMixin(Level world, Vec3 position, ItemStack toPolish, ItemStack sandPaperStack, CallbackInfoReturnable<ItemStack> cir) {
        if (toPolish.getItem() instanceof IModifiable) {

            ToolStack tool = ToolStack.from(toPolish.copy());

            var modifier = tool.getModifier(TGModifiers.Ids.POLISHED);
            if (modifier.getLevel() < 1) {
                tool.addModifier(TGModifiers.Ids.POLISHED, 1);
            }

            CreatePolishedModifier polished = TGModifiers.POLISHED.get();
            polished.refreshShield(tool, tool.getModifier(TGModifiers.Ids.POLISHED));
            tool.setDamage((int) (tool.getDamage() + tool.getStats().getInt(ToolStats.DURABILITY) * TinkersGearsConfig.POLISHING_DAMAGE.get()));

            cir.setReturnValue(tool.copyStack(toPolish));
        }
    }

    @Inject(
            remap = false,
            method = "canPolish",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void canPolishMixin(Level world, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.getItem() instanceof IModifiable) {
            ToolStack tool = ToolStack.from(stack);
            if (tool.getModifier(TGModifiers.Ids.POLISHED).getLevel() < 1) {
                if (tool.hasTag(TinkerTags.Items.HELD)) {
                    cir.setReturnValue(true);
                }
            } else if (tool.getModifier(TGModifiers.Ids.POLISHED).getLevel() > 0) {
                CreatePolishedModifier polished = TGModifiers.POLISHED.get();
                if (polished.getShield(tool) < polished.getShieldCapacity(tool,  tool.getModifier(TGModifiers.Ids.POLISHED))) {
                    cir.setReturnValue(true);
                }
            }
            else {
                cir.setReturnValue(false);
            }
        }
    }
}
