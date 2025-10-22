package ho.artisan.tgears.ponder.scene;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.EntityElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.smeltery.block.controller.SmelteryControllerBlock;
import slimeknights.tconstruct.smeltery.block.entity.controller.SmelteryBlockEntity;

import java.util.List;

@SuppressWarnings("ALL")
public class SmelteryScene {
    private SmelteryScene() {
    }

    public static void newBasic(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        Object o = new Object();

        scene.title("new_smeltery", "New Smeltery");
        scene.showBasePlate();
        scene.idle(20);
        scene.scaleSceneView(0.7f);

        Selection bottom = util.select().fromTo(5, 1, 3, 3, 1, 5);
        Selection controllers = util.select().fromTo(5, 2, 2, 3, 2, 2);
        Selection fluidFuelTank = util.select().position(5, 2, 2);
        Selection controller = util.select().position(4, 2, 2);
        Selection drain = util.select().position(3, 2, 2);

        scene.world().showSection(bottom, Direction.DOWN);
        scene.idle(20);
        scene.overlay().showText(35)
                .text("The bottom consists of a maximum of 11x11\nRectangular Seared Bricks composition")
                .pointAt(util.vector().topOf(util.grid().at(3, 3, 3)));
        scene.overlay().showOutline(PonderPalette.GREEN, o, bottom, 60);
        scene.idle(60);

        scene.world().showSection(controllers, Direction.SOUTH);
        scene.overlay().showText(35)
                .text("Put the second layer in any direction\nFuel Tank\nSmeltery Controller\nSeared Drain")
                .attachKeyFrame();
        scene.idle(20);
        scene.overlay().showOutline(PonderPalette.BLUE, o, fluidFuelTank, 30);
        scene.overlay().showText(20)
                .text("Fuel Tank")
                .pointAt(util.vector().topOf(util.grid().at(5, 2, 2)))
                .placeNearTarget();
        scene.idle(30);
        scene.overlay().showOutline(PonderPalette.BLUE, o, controller, 30);
        scene.overlay().showText(20)
                .text("Smeltery Controller")
                .pointAt(util.vector().topOf(util.grid().at(4, 2, 2)))
                .placeNearTarget();
        scene.idle(30);
        scene.overlay().showOutline(PonderPalette.BLUE, o, drain, 30);
        scene.overlay().showText(20)
                .text("Seared Drain")
                .pointAt(util.vector().topOf(util.grid().at(3, 2, 2)))
                .placeNearTarget();
        scene.idle(40);

        scene.overlay().showText(30)
                .text("The rest of the surface we fill with bricks\nAt the same time, put faucet in front of drain.");

        List<BlockPos> bricks0 = List.of(
                util.grid().at(2, 2, 3), util.grid().at(2, 2, 4), util.grid().at(2, 2, 5),
                util.grid().at(3, 2, 6), util.grid().at(4, 2, 6), util.grid().at(5, 2, 6),
                util.grid().at(6, 2, 5), util.grid().at(6, 2, 4), util.grid().at(6, 2, 3)
        );
        for (BlockPos brick : bricks0) {
            scene.world().showSection(util.select().position(brick), Direction.DOWN);
            scene.idle(1);
        }
        scene.idle(20);

        scene.world().showSection(util.select().position(2, 2, 2), Direction.EAST);
        scene.world().showSection(util.select().position(3, 2, 1), Direction.SOUTH);
        scene.idle(10);
        scene.world().showSection(util.select().position(2, 1, 2), Direction.DOWN);
        scene.world().showSection(util.select().position(3, 1, 1), Direction.DOWN);
        scene.idle(40);

        scene.overlay().showText(30)
                .text("In addition, we can also use bricks to continue to build high.")
                .attachKeyFrame();

        List<BlockPos> bricks1 = List.of(
                util.grid().at(2, 3, 3), util.grid().at(2, 3, 4), util.grid().at(2, 3, 5),
                util.grid().at(3, 3, 6), util.grid().at(4, 3, 6), util.grid().at(5, 3, 6),
                util.grid().at(6, 3, 5), util.grid().at(6, 3, 4), util.grid().at(6, 3, 3),
                util.grid().at(5, 3, 2), util.grid().at(4, 3, 2), util.grid().at(3, 3, 2),

                util.grid().at(2, 4, 3), util.grid().at(2, 4, 4), util.grid().at(2, 4, 5),
                util.grid().at(3, 4, 6), util.grid().at(4, 4, 6), util.grid().at(5, 4, 6),
                util.grid().at(6, 4, 5), util.grid().at(6, 4, 4), util.grid().at(6, 4, 3),
                util.grid().at(5, 4, 2), util.grid().at(4, 4, 2), util.grid().at(3, 4, 2),

                util.grid().at(2, 5, 3), util.grid().at(2, 5, 4), util.grid().at(2, 5, 5),
                util.grid().at(3, 5, 6), util.grid().at(4, 5, 6), util.grid().at(5, 5, 6),
                util.grid().at(6, 5, 5), util.grid().at(6, 5, 4), util.grid().at(6, 5, 3),
                util.grid().at(5, 5, 2), util.grid().at(4, 5, 2), util.grid().at(3, 5, 2),

                util.grid().at(2, 6, 3), util.grid().at(2, 6, 4), util.grid().at(2, 6, 5),
                util.grid().at(3, 6, 6), util.grid().at(4, 6, 6), util.grid().at(5, 6, 6),
                util.grid().at(6, 6, 5), util.grid().at(6, 6, 4), util.grid().at(6, 6, 3),
                util.grid().at(5, 6, 2), util.grid().at(4, 6, 2), util.grid().at(3, 6, 2),

                util.grid().at(2, 7, 3), util.grid().at(2, 7, 4), util.grid().at(2, 7, 5),
                util.grid().at(3, 7, 6), util.grid().at(4, 7, 6), util.grid().at(5, 7, 6),
                util.grid().at(6, 7, 5), util.grid().at(6, 7, 4), util.grid().at(6, 7, 3),
                util.grid().at(5, 7, 2), util.grid().at(4, 7, 2), util.grid().at(3, 7, 2),

                util.grid().at(2, 8, 3), util.grid().at(2, 8, 4), util.grid().at(2, 8, 5),
                util.grid().at(3, 8, 6), util.grid().at(4, 8, 6), util.grid().at(5, 8, 6),
                util.grid().at(6, 8, 5), util.grid().at(6, 8, 4), util.grid().at(6, 8, 3),
                util.grid().at(5, 8, 2), util.grid().at(4, 8, 2), util.grid().at(3, 8, 2),

                util.grid().at(2, 9, 3), util.grid().at(2, 9, 4), util.grid().at(2, 9, 5),
                util.grid().at(3, 9, 6), util.grid().at(4, 9, 6), util.grid().at(5, 9, 6),
                util.grid().at(6, 9, 5), util.grid().at(6, 9, 4), util.grid().at(6, 9, 3),
                util.grid().at(5, 9, 2), util.grid().at(4, 9, 2), util.grid().at(3, 9, 2)
        );
        for (BlockPos brick : bricks1) {
            scene.world().showSection(util.select().position(brick), Direction.DOWN);
            scene.idle(1);
        }
        scene.idle(20);

        scene.addKeyframe();
        scene.overlay().showOutline(PonderPalette.RED, o, util.select().position(5, 2, 2), 60);
        scene.overlay().showText(40)
                .text("Finally, remember to import fuel to Fuel Tank")
                .pointAt(util.vector().topOf(util.grid().at(5, 1, 2)))
                .placeNearTarget();
        scene.idle(60);
    }

    public static void basic(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("smeltery", "Building the smeltery");
        scene.configureBasePlate(0, 0, 7);
        scene.scaleSceneView(0.75F);
        scene.world().showSection(util.select().layer(0), Direction.UP);

        Selection body = util.select().fromTo(1, 2, 1, 5, 2, 5);
        Selection floor = util.select().fromTo(2, 1, 2, 4, 1, 4);
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

    public static void transfer(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("smeltery_transfer", "Understanding the smeltery transfer");
        scene.configureBasePlate(0, 0, 7);
        scene.scaleSceneView(0.75F);
        scene.world().showSection(util.select().everywhere(), Direction.UP);

        BlockPos tank = util.grid().at(0, 1, 5);
        BlockPos controller = util.grid().at(4, 2, 2);
        BlockPos chute = util.grid().at(2, 2, 3);
        Selection chuteSelection = util.select().position(chute);

        scene.idle(35);
        scene.overlay().showOutline(PonderPalette.GREEN, chuteSelection, chuteSelection, 30);
        scene.idle(5);
        scene.overlay().showText(25)
                .text("The chute can transport items to the smeltery")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(chute, Direction.WEST));
        scene.idle(35);

        scene.world().setKineticSpeed(util.select().everywhere(), 16);
        scene.idle(20);

        ItemStack stack = new ItemStack(AllItems.CRUSHED_IRON.get());
        ElementLink<EntityElement> item =
                scene.world().createItemEntity(util.vector().centerOf(2, 4, 0), util.vector().of(0, 0, 0), stack);
        scene.idle(13);
        scene.world().modifyEntity(item, Entity::discard);
        BlockPos beltStart = util.grid().at(2, 1, 0);
        scene.world().createItemOnBelt(beltStart, Direction.NORTH, stack);

        scene.idle(73);
        BlockPos beltEnd = util.grid().at(2, 1, 2);
        scene.world().flapFunnel(beltEnd.above(), false);
        scene.world().removeItemsFromBelt(beltEnd);

        scene.world().modifyBlock(controller, blockState -> blockState.setValue(SmelteryControllerBlock.ACTIVE, true), false);
        scene.world().modifyBlockEntity(controller, SmelteryBlockEntity.class, s -> {
            s.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                handler.insertItem(0, stack, false);
            });
        });
        scene.idle(10);

        scene.overlay().showText(20)
                .text("Waiting to melt")
                .attachKeyFrame()
                .placeNearTarget()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(controller, Direction.NORTH));
        scene.idle(30);

        scene.world().modifyBlock(controller, blockState -> blockState.setValue(SmelteryControllerBlock.ACTIVE, false), false);
        scene.world().modifyBlockEntity(tank, FluidTankBlockEntity.class, t -> {
            t.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                handler.fill(new FluidStack(TinkerFluids.moltenIron.get(), 150), IFluidHandler.FluidAction.EXECUTE);
            });
        });
        scene.idle(10);
        scene.overlay().showText(20)
                .text("Input to fluid tank")
                .attachKeyFrame()
                .placeNearTarget()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(tank, Direction.WEST));

        scene.idle(60);
        scene.markAsFinished();
    }
}
