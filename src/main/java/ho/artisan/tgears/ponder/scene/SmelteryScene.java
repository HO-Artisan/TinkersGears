package ho.artisan.tgears.ponder.scene;

import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class SmelteryScene {
    private SmelteryScene() {}

    public static void basic(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("smeltery", "Building the smeltery");
        scene.configureBasePlate(0, 0, 7);
        scene.scaleSceneView(0.75F);
        scene.world().showSection(util.select().layer(0), Direction.UP);

        Selection body = util.select().fromTo(1, 2, 1, 5, 2 , 5);
        Selection floor = util.select().fromTo(2, 1, 2, 4, 1 , 4);
        BlockPos center = util.grid().at(3, 1, 3);
        BlockPos controller = util.grid().at(3, 2, 1);

        scene.world().showSection(floor, Direction.UP);
        scene.idle(10);
        scene.overlay().showOutline(PonderPalette.GREEN, floor, floor, 30);
        scene.idle(5);
        scene.overlay().showText(25)
                .text("Usually 3x3 size")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().topOf(center));

        scene.idle(35);
        scene.world().showSection(util.select().layer(2), Direction.UP);

        scene.idle(5);
        scene.overlay().showOutline(PonderPalette.GREEN, body, body, 30);

        scene.idle(5);
        scene.overlay().showText(25)
                .text("It is necessary to form a space with only an upper opening")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().topOf(center.above()));

        scene.idle(35);
        scene.overlay().showOutline(PonderPalette.GREEN, util.select().position(controller), util.select().position(controller), 30);
        scene.idle(5);
        scene.overlay().showText(25)
                .text("Right-click the controller to open the GUI and work")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().topOf(controller));

        scene.idle(60);
        scene.markAsFinished();
    }

    public static void mini(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("smeltery_mini", "Building the mini smeltery");
        scene.configureBasePlate(0, 0, 7);
        scene.scaleSceneView(0.75F);
        scene.world().showSection(util.select().everywhere(), Direction.UP);

        scene.idle(20);
        scene.rotateCameraY(90);
        scene.idle(35);
        scene.rotateCameraY(90);
        scene.idle(35);
        scene.rotateCameraY(90);
        scene.idle(35);
        scene.rotateCameraY(90);
        scene.idle(35);

        BlockPos center = util.grid().at(3, 2, 3);

        scene.overlay().showText(25)
                .text("It may not be economical, but it is interesting")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().topOf(center));

        scene.idle(60);
        scene.markAsFinished();
    }
}
