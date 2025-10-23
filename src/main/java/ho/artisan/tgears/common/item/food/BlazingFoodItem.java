package ho.artisan.tgears.common.item.food;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlazingFoodItem extends Item {
    public BlazingFoodItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!livingEntity.fireImmune()) {
            livingEntity.setSecondsOnFire(3);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
