package ho.artisan.tgears.client.ponder.scene;

import ho.artisan.tgears.old.block.entity.TinkerCrushingWheelControllerBlockEntity;
import ho.artisan.tgears.old.block.entity.TinkerSpoutBlockEntity;
import ho.artisan.tgears.client.ponder.TGSceneBuilder;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import slimeknights.tconstruct.fluids.TinkerFluids;

public class TinkerMachineScene {
    public static void fan(SceneBuilder builder, SceneBuildingUtil util) {
        TGSceneBuilder scene = new TGSceneBuilder(builder);

        scene.title("tinker_fan", "Use tinker fans to ave stress");

        scene.init5x5(util);

        BlockPos fan_0 = util.grid().at(1, 1, 3);
        BlockPos fan_1 = util.grid().at(2, 1, 3);
        BlockPos fan_2 = util.grid().at(3, 1, 3);

        Selection fans = util.select().fromTo(fan_0, fan_2);

        scene.idle(15);
        scene.world().showSection(fans, Direction.NORTH);
        scene.idle(10);
        scene.world().setKineticSpeed(fans, -32f);
        scene.idle(10);
        scene.overlay().showOutline(PonderPalette.GREEN, fans, fans, 30);
        scene.idle(5);
        scene.overlay().showText(25)
                .text("Tinker fans and ordinary fans have the same function, while their stress consumption is only half that of the latter")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(fan_1, Direction.UP));

        scene.idle(40);
        scene.markAsFinished();
    }

    public static void spout(SceneBuilder builder, SceneBuildingUtil util) {
        TGSceneBuilder scene = new TGSceneBuilder(builder);
        scene.title("tinker_spout", "Use tinker spouts with signals");
        scene.init5x5(util);

        BlockPos table_0 = util.grid().at(1, 1, 2);
        BlockPos table_1 = util.grid().at(3, 1, 2);

        BlockPos spout_0 = util.grid().at(1, 3, 2);
        BlockPos spout_1 = util.grid().at(3, 3, 2);

        Selection s_0 = util.select().fromTo(table_0, spout_0);
        Selection s_1 = util.select().fromTo(table_1, spout_1.north());

        Selection c = util.select().fromTo(table_0.south(), spout_1.south(2));

        scene.idle(10);
        scene.world().showSection(s_0, Direction.NORTH);
        scene.idle(10);
        scene.world().showSection(s_1, Direction.NORTH);

        scene.idle(15);
        scene.overlay().showText(25)
                .text("Tinker spouts can be controlled by signal")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(spout_0, Direction.UP));
        scene.idle(30);

        scene.world().showSection(c, Direction.NORTH);
        scene.idle(10);
        scene.world().setKineticSpeed(c, 32f);
        scene.idle(5);
        for (int i = 0; i < 8; i++) {
            scene.world().modifyBlockEntity(spout_0, TinkerSpoutBlockEntity.class, m -> {
                m.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.fill(new FluidStack(TinkerFluids.moltenIron.get(), 500), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.world().modifyBlockEntity(spout_1, TinkerSpoutBlockEntity.class, m -> {
                m.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.fill(new FluidStack(TinkerFluids.moltenIron.get(), 500), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.idle(10);
        }

        scene.idle(15);
        scene.overlay().showText(25)
                .text("When activated, it stops working")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(spout_1, Direction.UP));
        scene.idle(45);

        scene.markAsFinished();
    }

    public static void drill(SceneBuilder builder, SceneBuildingUtil util) {
        TGSceneBuilder scene = new TGSceneBuilder(builder);
        scene.title("tinker_drill", "Use Tinker Drills to break blocks");
        scene.init5x5(util);

        BlockPos glass_0 = util.grid().at(1, 1, 2);
        BlockPos glass_1 = util.grid().at(3, 1, 2);

        BlockPos drill_0 = util.grid().at(1, 1, 3);
        BlockPos drill_1 = util.grid().at(3, 1, 3);

        scene.idle(10);
        scene.world().showSection(util.select().fromTo(glass_0, glass_1), Direction.NORTH);
        scene.idle(15);
        scene.world().showSection(util.select().fromTo(drill_0, drill_1), Direction.NORTH);

        scene.idle(15);
        scene.overlay().showText(25)
                .text("Tinker drills and ordinary drills have the same function, while their stress consumption is only half that of the latter")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(drill_0, Direction.UP));
        scene.idle(30);

        scene.idle(15);
        scene.overlay().showText(25)
                .text("Tinker drills can be upgraded to drill with the Silk Touch")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(drill_1, Direction.UP));
        scene.idle(40);

        scene.world().setKineticSpeed(util.select().fromTo(drill_0, drill_1), 16f);

        for (int i = 0; i < 10; i++) {
            scene.idle(10);
            scene.world().incrementBlockBreakingProgress(glass_0);
            scene.world().incrementBlockBreakingProgress(glass_1);
        }

        scene.world().createItemEntity(glass_1.getCenter(), Vec3.ZERO, new ItemStack(Items.GLASS));

        scene.idle(45);
        scene.markAsFinished();
    }

    public static void fortuneDrill(SceneBuilder builder, SceneBuildingUtil util) {
        TGSceneBuilder scene = new TGSceneBuilder(builder);

        scene.title("tinker_fortune_drill", "Use Fortune Tinker Drills to yield more drops");

        scene.init5x5(util);

        BlockPos ore = util.grid().at(2, 1, 2);
        BlockPos drill = util.grid().at(2, 1, 3);

        scene.idle(10);
        scene.world().showSection(util.select().position(ore), Direction.NORTH);
        scene.idle(15);
        scene.world().showSection(util.select().position(drill), Direction.NORTH);

        scene.idle(15);
        scene.overlay().showText(25)
                .text("Fortune Tinker Drills can break blocks with a chance to yield more drops, functioning just like the Fortune Enchantment")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(drill, Direction.UP));
        scene.idle(30);

        scene.world().setKineticSpeed(util.select().position(drill), 16f);

        for (int i = 0; i < 10; i++) {
            scene.idle(10);
            scene.world().incrementBlockBreakingProgress(ore);
        }

        for (int i = 0; i < 4; i++) {
            scene.world().createItemEntity(
                    ore.getCenter().offsetRandom(RandomSource.create(), 0.5F),
                    Vec3.ZERO,
                    new ItemStack(Items.DIAMOND, 1)
            );
        }

        scene.idle(20);
        scene.markAsFinished();
    }

    public static void crushing(SceneBuilder builder, SceneBuildingUtil util) {
        TGSceneBuilder scene = new TGSceneBuilder(builder);

        scene.title("tinker_crushing", "Use Tinker Crushing Wheels to crush items");

        scene.init5x5(util);

        BlockPos wheel_0 = util.grid().at(1, 2, 2);
        BlockPos controller = util.grid().at(2, 2, 2);
        BlockPos wheel_1 = util.grid().at(3, 2, 2);
        BlockPos center = util.grid().at(2, 3, 2);

        scene.idle(10);
        scene.world().showSection(util.select().position(wheel_0), Direction.NORTH);
        scene.idle(5);
        scene.world().showSection(util.select().position(wheel_1), Direction.NORTH);

        scene.idle(20);
        scene.overlay().showText(25)
                .text("Tinker Crushing Wheel is more stress-saving")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(wheel_0, Direction.UP));
        scene.idle(35);

        scene.world().showSection(util.select().position(wheel_0.south()), Direction.UP);
        scene.world().showSection(util.select().position(wheel_1.south()), Direction.UP);

        scene.idle(20);

        scene.world().setKineticSpeed(util.select().position(wheel_0), -32f);
        scene.world().setKineticSpeed(util.select().position(wheel_1), 32f);

        scene.idle(20);

        scene.world().modifyBlockEntity(controller, TinkerCrushingWheelControllerBlockEntity.class, entity -> {
            entity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                handler.insertItem(0, new ItemStack(Items.WHITE_WOOL), false);
            });
        });

        scene.overlay().showControls(util.vector().blockSurface(controller, Direction.UP), Pointing.DOWN, 15)
                .withItem(new ItemStack(Items.WHITE_WOOL));

        scene.idle(60);

        scene.markAsFinished();
    }

    public static void fortuneCrushing(SceneBuilder builder, SceneBuildingUtil util) {
        TGSceneBuilder scene = new TGSceneBuilder(builder);

        scene.title("tinker_fortune_crushing", "Use Tinker Fortune Crushing Wheels to crush items for more product");

        scene.init5x5(util);

        BlockPos wheel_0 = util.grid().at(1, 2, 2);
        BlockPos wheel_1 = util.grid().at(3, 2, 2);
        BlockPos center = util.grid().at(2, 3, 2);

        scene.idle(10);
        scene.world().showSection(util.select().position(wheel_0), Direction.NORTH);
        scene.idle(5);
        scene.world().showSection(util.select().position(wheel_1), Direction.NORTH);

        scene.idle(15);
        scene.overlay().showText(25)
                .text("Tinker Fortune Crushing Wheel can produce by-products with 100% probability")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(wheel_0, Direction.UP));

        scene.idle(30);

        scene.world().showSection(util.select().position(wheel_0.south()), Direction.UP);
        scene.world().showSection(util.select().position(wheel_1.south()), Direction.UP);

        scene.idle(20);

        scene.world().setKineticSpeed(util.select().position(wheel_0), -32f);
        scene.world().setKineticSpeed(util.select().position(wheel_1), 32f);
        scene.idle(60);
        scene.markAsFinished();
    }
}
