package ho.artisan.tgears.common.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

public class CastItem extends Item {
    private final ItemLike cast;
    private final ItemLike content;

    public CastItem(ItemLike cast, ItemLike content, Properties properties) {
        super(properties);
        this.cast = cast == null ? Items.AIR : cast;
        this.content = content == null ? Items.AIR : content;
    }

    public boolean isAvailable() {
        return cast.asItem() != Items.AIR && content.asItem() != Items.AIR;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!isAvailable()) {
            return InteractionResultHolder.fail(new ItemStack(this));
        }

        ItemStack stack = player.getItemInHand(usedHand);
        ItemStack contentStack = new ItemStack(content);
        ItemStack castStack = new ItemStack(cast);
        if (!player.addItem(contentStack.copy())) {
            player.drop(contentStack.copy(), false, false);
        }
        if (!player.addItem(castStack.copy())) {
            player.drop(castStack.copy(), false, false);
        }
        stack.shrink(1);
        player.playSound(SoundEvents.ITEM_PICKUP,0.5F, 1.0F);
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}
