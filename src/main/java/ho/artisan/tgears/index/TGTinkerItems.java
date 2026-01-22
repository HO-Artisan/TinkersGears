package ho.artisan.tgears.index;

import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.common.item.PartMaterialItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static ho.artisan.tgears.tools.PartMaterialStats.*;

public class TGTinkerItems {
    public static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(TinkersGears.MOD_ID);

    public static final Item.Properties ITEM_PROPS = new Item.Properties();

    public static final ItemObject<PartMaterialItem> HAND_PART = ITEMS.register("hand_part", () -> new PartMaterialItem(ITEM_PROPS, HAND));
    public static final ItemObject<PartMaterialItem> PROPELLER_PART = ITEMS.register("propeller_part", () -> new PartMaterialItem(ITEM_PROPS, PROPELLER));
    public static final ItemObject<PartMaterialItem> WHISK_PART = ITEMS.register("whisk_part", () -> new PartMaterialItem(ITEM_PROPS, WHISK));

    public static final CastItemObject HAND_CAST = ITEMS.registerCast(HAND_PART, ITEM_PROPS);
    public static final CastItemObject PROPELLER_CAST = ITEMS.registerCast(PROPELLER_PART, ITEM_PROPS);
    public static final CastItemObject WHISK_CAST = ITEMS.registerCast(WHISK_PART, ITEM_PROPS);

    protected static void addTabItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output tab) {
        Consumer<ItemStack> output = tab::accept;
        accept(output, HAND_PART);
        accept(output, PROPELLER_PART);
        accept(output, WHISK_PART);

        accept(output, HAND_CAST);
        accept(output, PROPELLER_CAST);
        accept(output, WHISK_CAST);
    }

    private static void accept(Consumer<ItemStack> output, Supplier<? extends IMaterialItem> item) {
        item.get().addVariants(output, "");
    }

    private static void accept(Consumer<ItemStack> output, CastItemObject cast) {
        output.accept(new ItemStack(cast.get()));
        output.accept(new ItemStack(cast.getSand()));
        output.accept(new ItemStack(cast.getRedSand()));
    }
}
