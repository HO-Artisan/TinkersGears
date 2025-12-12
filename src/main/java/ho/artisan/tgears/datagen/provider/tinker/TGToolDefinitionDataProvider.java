package ho.artisan.tgears.datagen.provider.tinker;

import com.google.common.collect.ImmutableMap;
import ho.artisan.tgears.index.TGModifiers;
import ho.artisan.tgears.index.TGToolDefinitions;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.definition.module.build.SetStatsModule;
import slimeknights.tconstruct.library.tools.definition.module.build.ToolSlotsModule;
import slimeknights.tconstruct.library.tools.definition.module.build.ToolTraitsModule;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;

import static slimeknights.tconstruct.library.tools.stat.ToolStats.ATTACK_DAMAGE;

public final class TGToolDefinitionDataProvider extends AbstractToolDefinitionDataProvider {
    public TGToolDefinitionDataProvider(PackOutput packOutput) {
        super(packOutput, "tgears");
    }

    @Override
    public String getName() {
        return "TGears Tool Definition Recipes";
    }

    @Override
    protected void addToolDefinitions() {
        define(TGToolDefinitions.WRENCH)
                .module(new SetStatsModule(StatsNBT.builder().set(ATTACK_DAMAGE, 4.0F).build()))
                .module(new ToolSlotsModule(ImmutableMap.of(SlotType.UPGRADE, 1)))
                .module(ToolTraitsModule.builder().trait(TGModifiers.Ids.TOPNOTCH).build());
    }
}
