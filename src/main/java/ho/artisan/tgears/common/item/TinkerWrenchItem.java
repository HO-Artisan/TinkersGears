package ho.artisan.tgears.common.item;

import com.simibubi.create.foundation.item.render.SimpleCustomRenderer;
import ho.artisan.tgears.client.renderer.TinkerWrenchItemRenderer;
import ho.artisan.tgears.index.TGToolDefinitions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import slimeknights.tconstruct.library.tools.item.ModifiableItem;

import java.util.function.Consumer;

public class TinkerWrenchItem extends ModifiableItem {
    public TinkerWrenchItem(Properties properties) {
        super(properties, TGToolDefinitions.WRENCH);

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(SimpleCustomRenderer.create(this, new TinkerWrenchItemRenderer()));
    }
}
