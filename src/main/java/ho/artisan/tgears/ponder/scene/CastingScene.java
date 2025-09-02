package ho.artisan.tgears.ponder.scene;

import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import slimeknights.tconstruct.fluids.TinkerFluids;

public class CastingScene {
    private CastingScene() {}

    public static void table(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("table_cooling", "Use fans to accelerate cooling");
        scene.configureBasePlate(0, 0, 5);
        scene.world().showSection(util.select().layer(0), Direction.UP);

        BlockPos table = util.grid().at(2, 1, 2);
        BlockPos spout = util.grid().at(2, 3, 2);
        Selection selection0 = util.select().fromTo(table.west(), table.west(2));
        Selection selection1 = util.select().fromTo(table.east(), table.east(2));
        Selection selection2 = util.select().fromTo(table.south(), table.south(2));

        scene.idle(5);
        scene.world().showSection(util.select().position(table), Direction.UP);
        scene.idle(15);
        scene.overlay().showText(25)
                .text("Fans can speed up casting")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().topOf(table));

        scene.idle(35);
        scene.world().showSection(selection0, Direction.UP);
        scene.idle(5);
        scene.world().showSection(selection1, Direction.UP);
        scene.idle(5);
        scene.world().showSection(selection2, Direction.UP);

        scene.idle(35);
        scene.overlay().showText(25)
                .text("Acceleration is related to rotational speed of the fan")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().topOf(table.west()));

        scene.idle(35);
        scene.world().showSection(util.select().position(spout), Direction.UP);
        scene.world().setKineticSpeed(util.select().everywhere(), 64);

        scene.idle(5);
        scene.world().modifyBlockEntity(spout, SpoutBlockEntity.class, s -> {
            s.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                handler.fill(new FluidStack(TinkerFluids.moltenIron.get(), 90), IFluidHandler.FluidAction.EXECUTE);
            });
        });

        scene.idle(60);
        scene.markAsFinished();
    }
}
