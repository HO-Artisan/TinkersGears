package ho.artisan.tgears.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;
import ho.artisan.tgears.old.block.entity.TinkerDismantlerBlockEntity;
import ho.artisan.tgears.common.block.module.TinkerItemModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TinkerDismantlerRenderer extends SafeBlockEntityRenderer<TinkerDismantlerBlockEntity> {
    public TinkerDismantlerRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    protected void renderSafe(TinkerDismantlerBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        TinkerItemModule module = be.getTinkerBreakingBehaviour().getModule();
        Level level = be.getLevel();
        if (module == null) return;

        ItemStack stack = module.getTinkerableStack();
        if (stack.isEmpty()) return;

        ms.pushPose();
        ms.translate(0.5F, 1.25F, 0.5F);
        ms.scale(0.75F, 0.75F, 0.75F);
        ms.mulPose(Axis.YP.rotationDegrees(be.getDirection().toYRot() + 90F));
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, light, overlay, ms, buffer, level, 0);
        ms.popPose();
    }
}
