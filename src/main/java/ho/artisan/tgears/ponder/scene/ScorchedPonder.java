package ho.artisan.tgears.ponder.scene;

import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import java.util.List;

@SuppressWarnings("ALL")
public class ScorchedPonder {
    private ScorchedPonder() {
    }

    public static void basic(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        Object o = new Object();

        scene.title("new_foundry", "The Foundry");
        scene.showBasePlate();
        scene.idle(20);
        scene.scaleSceneView(0.7f);

        Selection bottom = util.select().fromTo(6, 1, 2, 2, 1, 6);
        scene.world().showSection(bottom, Direction.DOWN);
        scene.idle(20);
        scene.overlay().showText(50)
                .text("The bottom consists of a maximum of 16x16\nRectangular Scorched Bricks composition")
                .pointAt(util.vector().centerOf(util.grid().at(3, 3, 3)));
        scene.overlay().showOutline(PonderPalette.RED, o, bottom, 60);
        scene.idle(60);

        Selection secondLayer = util.select().fromTo(4, 2, 2, 2, 2, 2);
        scene.world().showSection(secondLayer, Direction.DOWN);
        scene.overlay().showText(35)
                .text("Put the second layer in any direction\nFoundry Controller\nFuel Tank\nScorched Drain")
                .attachKeyFrame();
        scene.idle(30);

        Selection controller = util.select().position(4, 2, 2);
        scene.overlay().showOutline(PonderPalette.RED, o, controller, 30);
        scene.overlay().showText(20)
                .text("Foundry Controller")
                .pointAt(util.vector().topOf(util.grid().at(4, 2, 2)))
                .placeNearTarget();
        scene.idle(30);

        Selection fuelTank = util.select().position(3, 2, 2);
        scene.overlay().showOutline(PonderPalette.RED, o, fuelTank, 30);
        scene.overlay().showText(20)
                .text("Fuel Tank")
                .pointAt(util.vector().topOf(util.grid().at(3, 2, 2)))
                .placeNearTarget();
        scene.idle(30);

        Selection drain = util.select().position(2, 2, 2);
        scene.overlay().showOutline(PonderPalette.RED, o, drain, 30);
        scene.overlay().showText(20)
                .text("Scorched Drain")
                .pointAt(util.vector().topOf(util.grid().at(2, 2, 2)))
                .placeNearTarget();
        scene.idle(30);

        scene.overlay().showText(30)
                .text("The rest of the surface we fill with bricks\nAt the same time, put faucet in front of drain.")
                .attachKeyFrame();

        List<BlockPos> bricks1 = List.of(
                util.grid().at(2, 2, 3), util.grid().at(2, 2, 4), util.grid().at(2, 2, 5), util.grid().at(2, 2, 6),
                util.grid().at(3, 2, 6), util.grid().at(4, 2, 6), util.grid().at(5, 2, 6), util.grid().at(6, 2, 6),
                util.grid().at(6, 2, 5), util.grid().at(6, 2, 4), util.grid().at(6, 2, 3), util.grid().at(6, 2, 2), util.grid().at(5, 2, 2)
        );
        for (BlockPos brick : bricks1) {
            scene.world().showSection(util.select().position(brick), Direction.DOWN);
            scene.idle(1);
        }
        scene.idle(20);

        scene.addKeyframe();
        scene.world().showSection(util.select().position(1, 2, 2), Direction.EAST);
        scene.world().showSection(util.select().position(2, 2, 1), Direction.SOUTH);
        scene.idle(10);
        scene.world().showSection(util.select().position(1, 1, 2), Direction.DOWN);
        scene.world().showSection(util.select().position(2, 1, 1), Direction.DOWN);
        scene.idle(40);

        scene.overlay().showText(30)
                .text("In addition, we can also use bricks to continue to build high.")
                .attachKeyFrame();

        List<BlockPos> bricks2 = List.of(
                util.grid().at(6, 3, 2), util.grid().at(5, 3, 2), util.grid().at(4, 3, 2), util.grid().at(3, 3, 2), util.grid().at(2, 3, 2),
                util.grid().at(2, 3, 3), util.grid().at(2, 3, 4), util.grid().at(2, 3, 5), util.grid().at(2, 3, 6),
                util.grid().at(3, 3, 6), util.grid().at(4, 3, 6), util.grid().at(5, 3, 6), util.grid().at(6, 3, 6),
                util.grid().at(6, 3, 5), util.grid().at(6, 3, 4), util.grid().at(6, 3, 3),

                util.grid().at(6, 4, 2), util.grid().at(5, 4, 2), util.grid().at(4, 4, 2), util.grid().at(3, 4, 2), util.grid().at(2, 4, 2),
                util.grid().at(2, 4, 3), util.grid().at(2, 4, 4), util.grid().at(2, 4, 5), util.grid().at(2, 4, 6),
                util.grid().at(3, 4, 6), util.grid().at(4, 4, 6), util.grid().at(5, 4, 6), util.grid().at(6, 4, 6),
                util.grid().at(6, 4, 5), util.grid().at(6, 4, 4), util.grid().at(6, 4, 3),

                util.grid().at(6, 5, 2), util.grid().at(5, 5, 2), util.grid().at(4, 5, 2), util.grid().at(3, 5, 2), util.grid().at(2, 5, 2),
                util.grid().at(2, 5, 3), util.grid().at(2, 5, 4), util.grid().at(2, 5, 5), util.grid().at(2, 5, 6),
                util.grid().at(3, 5, 6), util.grid().at(4, 5, 6), util.grid().at(5, 5, 6), util.grid().at(6, 5, 6),
                util.grid().at(6, 5, 5), util.grid().at(6, 5, 4), util.grid().at(6, 5, 3),

                util.grid().at(6, 6, 2), util.grid().at(5, 6, 2), util.grid().at(4, 6, 2), util.grid().at(3, 6, 2), util.grid().at(2, 6, 2),
                util.grid().at(2, 6, 3), util.grid().at(2, 6, 4), util.grid().at(2, 6, 5), util.grid().at(2, 6, 6),
                util.grid().at(3, 6, 6), util.grid().at(4, 6, 6), util.grid().at(5, 6, 6), util.grid().at(6, 6, 6),
                util.grid().at(6, 6, 5), util.grid().at(6, 6, 4), util.grid().at(6, 6, 3),

                util.grid().at(6, 7, 2), util.grid().at(5, 7, 2), util.grid().at(4, 7, 2), util.grid().at(3, 7, 2), util.grid().at(2, 7, 2),
                util.grid().at(2, 7, 3), util.grid().at(2, 7, 4), util.grid().at(2, 7, 5), util.grid().at(2, 7, 6),
                util.grid().at(3, 7, 6), util.grid().at(4, 7, 6), util.grid().at(5, 7, 6), util.grid().at(6, 7, 6),
                util.grid().at(6, 7, 5), util.grid().at(6, 7, 4), util.grid().at(6, 7, 3),

                util.grid().at(6, 8, 2), util.grid().at(5, 8, 2), util.grid().at(4, 8, 2), util.grid().at(3, 8, 2), util.grid().at(2, 8, 2),
                util.grid().at(2, 8, 3), util.grid().at(2, 8, 4), util.grid().at(2, 8, 5), util.grid().at(2, 8, 6),
                util.grid().at(3, 8, 6), util.grid().at(4, 8, 6), util.grid().at(5, 8, 6), util.grid().at(6, 8, 6),
                util.grid().at(6, 8, 5), util.grid().at(6, 8, 4), util.grid().at(6, 8, 3),

                util.grid().at(6, 9, 2), util.grid().at(5, 9, 2), util.grid().at(4, 9, 2), util.grid().at(3, 9, 2), util.grid().at(2, 9, 2),
                util.grid().at(2, 9, 3), util.grid().at(2, 9, 4), util.grid().at(2, 9, 5), util.grid().at(2, 9, 6),
                util.grid().at(3, 9, 6), util.grid().at(4, 9, 6), util.grid().at(5, 9, 6), util.grid().at(6, 9, 6),
                util.grid().at(6, 9, 5), util.grid().at(6, 9, 4), util.grid().at(6, 9, 3)
        );

        for (BlockPos brick : bricks2) {
            scene.world().showSection(util.select().position(brick), Direction.DOWN);
            scene.idle(1);
        }
        scene.idle(20);

        scene.addKeyframe();
        scene.overlay().showOutline(PonderPalette.RED, o, util.select().position(3, 2, 2), 60);
        scene.overlay().showText(40)
                .text("Finally, remember to import fuel to Fuel Tank")
                .pointAt(util.vector().topOf(util.grid().at(3, 1, 2)))
                .placeNearTarget();
        scene.idle(20);
    }
}