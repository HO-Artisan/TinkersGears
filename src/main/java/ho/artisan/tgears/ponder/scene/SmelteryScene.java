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
