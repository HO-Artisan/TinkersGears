package ho.artisan.tgears.data.provider.tinker;

import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;

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

    }
}
