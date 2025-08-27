package ho.artisan.tgears.util;

import com.simibubi.create.foundation.utility.CreateLang;
import ho.artisan.tgears.index.TGModifiers;
import net.createmod.catnip.lang.LangBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.List;

import static net.minecraft.ChatFormatting.GRAY;

public class TinkerGogglesUtil {

    public static boolean isWearingGoggles(Player player) {
        ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
        if (head.getItem() instanceof IModifiable) {
            ToolStack tool = ToolStack.from(head.copy());
            return tool.getModifier(TGModifiers.Ids.GOGGLES).getLevel() > 0;
        }
        return false;
    }

    public static void addStats(List<Component> tooltip) {
        CreateLang.translate("tooltip.gears.tinker_stats")
                .forGoggles(tooltip);
    }

    public static void addFluidStats(List<Component> tooltip) {
        CreateLang.translate("tooltip.gears.tinker_stats.fluid")
                .style(GRAY)
                .forGoggles(tooltip);
    }

    public static void addTemperature(List<Component> tooltip, int temperature) {
        CreateLang.translate("tooltip.gears.temperature")
                .style(GRAY)
                .forGoggles(tooltip);

        CreateLang.number(temperature)
                .translate("generic.unit.temperature")
                .style(ChatFormatting.AQUA)
                .space()
                .add(CreateLang.translate("gui.goggles.at_current_heating")
                        .style(ChatFormatting.DARK_GRAY))
                .forGoggles(tooltip, 1);
    }

    public static void addRate(List<Component> tooltip, int rate) {
        CreateLang.translate("tooltip.gears.rate")
                .style(GRAY)
                .forGoggles(tooltip);

        CreateLang.number(rate)
                .translate("generic.unit.percent")
                .style(ChatFormatting.AQUA)
                .space()
                .add(CreateLang.translate("gui.goggles.at_current_heating")
                        .style(ChatFormatting.DARK_GRAY))
                .forGoggles(tooltip, 1);
    }

    public static void addFluid(List<Component> tooltip, FluidStack fluidStack) {
        if (fluidStack.isEmpty())
            return;
        LangBuilder mb = CreateLang.translate("generic.unit.millibuckets");
        CreateLang.text("")
                .add(CreateLang.fluidName(fluidStack)
                        .add(CreateLang.text(" "))
                        .style(ChatFormatting.GRAY)
                        .add(CreateLang.number(fluidStack.getAmount())
                                .add(mb)
                                .style(ChatFormatting.BLUE)))
                .forGoggles(tooltip, 1);
    }

    public static void addProgress(List<Component> tooltip, int progress, int maxProgress) {
        CreateLang.translate("tooltip.gears.progress")
                .style(GRAY)
                .forGoggles(tooltip);
        int d = (int) (((double) progress / (double)maxProgress) * 100);

        CreateLang.number(d)
                .translate("generic.unit.percent")
                .style(ChatFormatting.AQUA)
                .space()
                .add(CreateLang.translate("gui.goggles.at_current_recipe")
                        .style(ChatFormatting.DARK_GRAY))
                .forGoggles(tooltip, 1);
    }

    public static void addOutput(List<Component> tooltip, ItemStack output) {
        CreateLang.translate("tooltip.gears.output")
                .style(GRAY)
                .forGoggles(tooltip);

        CreateLang.itemName(output)
                .style(ChatFormatting.GRAY)
                .forGoggles(tooltip, 1);
    }
}
