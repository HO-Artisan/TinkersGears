package ho.artisan.tgears.index;

import com.simibubi.create.content.equipment.goggles.GogglesItem;
import com.tterrag.registrate.util.entry.ItemEntry;
import ho.artisan.tgears.util.TinkerGogglesUtil;
import net.minecraft.world.item.Item;

import static ho.artisan.tgears.TinkersGears.REGISTRATE;

public class TGItems {
    static {
        REGISTRATE.setCreativeTab(TGCreativeModeTabs.MAIN_TAB);
    }

    public static final ItemEntry<Item> CRUSHED_RAW_COBALT =
            REGISTRATE.item("crushed_raw_cobalt", Item::new)
                    .register();

    public static void register() {
        GogglesItem.addIsWearingPredicate(TinkerGogglesUtil::isWearingGoggles);
    }
}
