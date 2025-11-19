package ho.artisan.tgears.util;

import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltHelper;
import com.simibubi.create.content.kinetics.belt.transport.BeltInventory;
import com.simibubi.create.content.kinetics.belt.transport.TransportedItemStack;
import ho.artisan.tgears.common.block.AbstractTinkerCrushingWheelControllerBlock;
import ho.artisan.tgears.common.block.entity.TinkerCrushingWheelControllerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemHandlerHelper;

public class BeltUtil {
    public static boolean checkForCrushers(BeltInventory beltInventory, TransportedItemStack currentItem,
                                           float nextOffset, boolean beltMovementPositive, BeltBlockEntity belt) {
        int firstUpcomingSegment = (int) Math.floor(currentItem.beltPosition);
        int step = beltMovementPositive ? 1 : -1;
        firstUpcomingSegment = Mth.clamp(firstUpcomingSegment, 0, belt.beltLength - 1);

        for (int segment = firstUpcomingSegment; beltMovementPositive ? segment <= nextOffset
                : segment + 1 >= nextOffset; segment += step) {
            BlockPos crusherPos = BeltHelper.getPositionForOffset(belt, segment)
                    .above();
            Level world = belt.getLevel();
            BlockState crusherState = world.getBlockState(crusherPos);
            if (!(crusherState.getBlock() instanceof AbstractTinkerCrushingWheelControllerBlock))
                continue;
            Direction crusherFacing = crusherState.getValue(AbstractTinkerCrushingWheelControllerBlock.FACING);
            Direction movementFacing = belt.getMovementFacing();
            if (crusherFacing != movementFacing)
                continue;

            float crusherEntry = segment + .5f;
            crusherEntry += .399f * (beltMovementPositive ? -1 : 1);
            float postCrusherEntry = crusherEntry + .799f * (!beltMovementPositive ? -1 : 1);

            boolean hasCrossed = nextOffset > crusherEntry && nextOffset < postCrusherEntry && beltMovementPositive
                    || nextOffset < crusherEntry && nextOffset > postCrusherEntry && !beltMovementPositive;
            if (!hasCrossed)
                return false;
            currentItem.beltPosition = crusherEntry;

            BlockEntity be = world.getBlockEntity(crusherPos);
            if (!(be instanceof TinkerCrushingWheelControllerBlockEntity crusherBE))
                return true;

            ItemStack toInsert = currentItem.stack.copy();

            ItemStack remainder = ItemHandlerHelper.insertItemStacked(crusherBE.inventory, toInsert, false);
            if (toInsert.equals(remainder, false))
                return true;

            int notFilled = currentItem.stack.getCount() - toInsert.getCount();
            if (!remainder.isEmpty()) {
                remainder.grow(notFilled);
            } else if (notFilled > 0)
                remainder = ItemHandlerHelper.copyStackWithSize(currentItem.stack, notFilled);

            currentItem.stack = remainder;
            belt.notifyUpdate();
            return true;
        }

        return false;
    }
}
