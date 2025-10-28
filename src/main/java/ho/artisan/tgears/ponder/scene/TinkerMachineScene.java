package ho.artisan.tgears.ponder.scene;

import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import ho.artisan.tgears.common.block.entity.TinkerSpoutBlockEntity;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import slimeknights.tconstruct.fluids.TinkerFluids;

public class TinkerMachineScene {
    public static void fan(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("tinker_fan", "Use tinker fans to ave stress");
        scene.configureBasePlate(0, 0, 5);
        scene.scaleSceneView(0.9f);
        scene.world().showSection(util.select().layer(0), Direction.UP);

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
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("tinker_spout", "Use tinker spouts with signals");
        scene.configureBasePlate(0, 0, 5);
        scene.scaleSceneView(0.9f);
        scene.world().showSection(util.select().layer(0), Direction.UP);

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
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("tinker_drill", "Use Tinker Drills to break blocks");
        scene.configureBasePlate(0, 0, 5);
        scene.scaleSceneView(0.9f);
        scene.world().showSection(util.select().layer(0), Direction.UP);

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
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("tinker_fortune_drill", "Use Fortune Tinker Drills to yield more drops");
        scene.configureBasePlate(0, 0, 5);
        scene.scaleSceneView(0.9f);
        scene.world().showSection(util.select().layer(0), Direction.UP);

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

        scene.world().createItemEntity(ore.getCenter(), Vec3.ZERO, new ItemStack(Items.DIAMOND, 4));

        scene.idle(20);
        scene.markAsFinished();
    }
}
