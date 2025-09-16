package ho.artisan.tgears.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

public class BlazingLeftoverFoodItem extends Item {
    private final ItemLike leftoverItem;

    public BlazingLeftoverFoodItem(Properties properties, ItemLike leftoverItem) {
        super(properties);
        this.leftoverItem = leftoverItem;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            if (!player.addItem(new ItemStack(leftoverItem))) {
                player.drop(new ItemStack(leftoverItem), false);
            }
        }
        if (!livingEntity.fireImmune()) {
            livingEntity.setSecondsOnFire(3);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
