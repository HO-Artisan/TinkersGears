package ho.artisan.tgears.client.gui;

import com.simibubi.create.content.equipment.armor.CardboardArmorHandler;
import ho.artisan.tgears.index.TGMaterials;
import net.createmod.catnip.animation.LerpedFloat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import slimeknights.tconstruct.library.materials.definition.MaterialVariant;
import slimeknights.tconstruct.library.tools.item.armor.ModifiableArmorItem;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class TinkerCardboardArmorStealthOverlay extends Gui implements IClientItemExtensions {
    private final ResourceLocation packageBlurLocation;

    public TinkerCardboardArmorStealthOverlay(ResourceLocation packageBlurLocation) {
        super(Minecraft.getInstance(), Minecraft.getInstance().getItemRenderer());
        this.packageBlurLocation = packageBlurLocation;
    }

    private static final LerpedFloat opacity = LerpedFloat.linear()
            .startWithValue(0)
            .chase(0, 0.25f, LerpedFloat.Chaser.EXP);

    public static void clientTick() {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null)
            return;

        opacity.tickChaser();
        opacity.updateChaseTarget(CardboardArmorHandler.testForStealth(player) ? 1 : 0);
    }

    @Override
    public void renderHelmetOverlay(ItemStack stack, Player player, int width, int height, float partialTick) {
        if (stack.getItem() instanceof ModifiableArmorItem) {
            ToolStack tool = ToolStack.from(stack);
            boolean flag = true;
            for (MaterialVariant material : tool.getMaterials()) {
                flag = flag && material.matches(TGMaterials.Ids.CARDBOARD);
            }
            if (flag) {
                Minecraft mc = Minecraft.getInstance();
                float value = opacity.getValue(partialTick);
                if (value == 0)
                    return;
                screenWidth = width;
                screenHeight = height;
                renderTextureOverlay(new GuiGraphics(mc, mc.renderBuffers()
                        .bufferSource()), packageBlurLocation, value);
            }
        }
    }
}
