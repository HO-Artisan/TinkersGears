package ho.artisan.tgears.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;
import ho.artisan.tgears.common.block.entity.TinkerDismantlerBlockEntity;
import ho.artisan.tgears.common.block.module.TinkerItemModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TinkerDismantlerRenderer extends SafeBlockEntityRenderer<TinkerDismantlerBlockEntity> {
    public TinkerDismantlerRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    protected void renderSafe(TinkerDismantlerBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        TinkerItemModule module = be.getTinkerBreakingBehaviour().getModule();
        if (module == null) return;

        ItemStack tinkerableStack = module.getTinkerableStack();
        if (tinkerableStack.isEmpty()) return;

        ms.pushPose();
        ms.translate(0.875F, 1.25F, 0.875F);
        ms.scale(0.75F, 0.75F, 0.75F);
        ms.mulPose(Axis.YP.rotationDegrees(be.getDirection().toYRot() + 90F));
        renderItem(module.getTinkerableStack(), ms, buffer, light, overlay, be.getLevel());
        ms.popPose();
    }

    public void renderItem(ItemStack itemStack, PoseStack ms, MultiBufferSource buffer, int light, int overlay, Level level) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        BakedModel bakedModel = itemRenderer.getModel(itemStack, level, null, 0);
        itemRenderer.render(itemStack, ItemDisplayContext.FIXED, false, ms, buffer, light, overlay, bakedModel);
    }
}
