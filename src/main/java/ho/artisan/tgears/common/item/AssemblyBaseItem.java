package ho.artisan.tgears.common.item;

import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.tools.definition.ToolDefinition;

import java.util.List;

public class AssemblyBaseItem extends SequencedAssemblyItem {
    public AssemblyBaseItem(Properties properties) {
        super(properties);
    }

    public ToolDefinition findTool(ItemStack stack) {
        return ToolDefinition.EMPTY;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {


        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}
