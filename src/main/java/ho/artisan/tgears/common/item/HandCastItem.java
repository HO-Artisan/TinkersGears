package ho.artisan.tgears.common.item;

import ho.artisan.tgears.index.TGItems;
import net.minecraft.world.level.ItemLike;

public class HandCastItem extends CastItem {
    public HandCastItem(ItemLike content, Properties properties) {
        super(TGItems.HAND_CAST, content, properties);
    }
}
