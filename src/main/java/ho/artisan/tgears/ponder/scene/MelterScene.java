package ho.artisan.tgears.ponder.scene;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.catnip.math.Pointing;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.component.SearedTankBlock;
import slimeknights.tconstruct.smeltery.block.controller.ControllerBlock;
import slimeknights.tconstruct.smeltery.block.controller.MelterBlock;
import slimeknights.tconstruct.smeltery.block.entity.CastingBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.FaucetBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.component.TankBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.controller.MelterBlockEntity;

public class MelterScene {
    private MelterScene() {}

    public static void basic(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("basic_melter", "Building the basic smeltery");
        scene.configureBasePlate(0, 0, 5);
        scene.world().showSection(util.select().layer(0), Direction.UP);

        Selection smeltery = util.select().fromTo(2, 1, 2, 2, 2 , 2);
        Selection basin = util.select().fromTo(1, 1, 2, 1, 2 , 2);
        Selection table = util.select().fromTo(3, 1, 2, 3, 2 , 2);
        BlockPos melter = util.grid().at(2, 2, 2);

        scene.idle(5);
        scene.world().showSection(util.select().position(melter.below()), Direction.DOWN);
        scene.idle(5);
        scene.world().showSection(util.select().position(melter), Direction.DOWN);

        scene.idle(10);
        scene.overlay().showText(40)
                .text("Your first smeltery")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().topOf(melter));

        scene.idle(5);
        scene.overlay().showOutline(PonderPalette.GREEN, smeltery, util.select().fromTo(2, 1, 2, 2, 2, 2), 40);

        scene.idle(5);
        scene.overlay().showText(40)
                .text("Need a heater to provide heat")
                .placeNearTarget()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(melter.below(), Direction.WEST));

        scene.idle(45);
        scene.overlay().showText(40)
                .text("You still add some fuel to start the smeltery")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(melter.below(), Direction.WEST));
        scene.idle(45);

        scene.overlay().showControls(util.vector().blockSurface(melter.below(), Direction.NORTH), Pointing.RIGHT, 20)
                .withItem(new ItemStack(Items.COAL));
        scene.world().modifyBlock(melter.below(), block -> block.setValue(ControllerBlock.ACTIVE, true), false);

        scene.idle(25);
        scene.overlay().showText(40)
                .text("Or you can use fuel tank")
                .attachKeyFrame()
                .placeNearTarget()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(melter.below(), Direction.WEST));

        scene.idle(25);
        scene.world().destroyBlock(melter.below());
        scene.idle(5);
        scene.world().setBlock(melter.below(), TinkerSmeltery.searedTank.get(SearedTankBlock.TankType.FUEL_TANK).defaultBlockState(), false);

        scene.idle(20);
        scene.overlay().showControls(util.vector().blockSurface(melter.below(), Direction.NORTH), Pointing.RIGHT, 20).rightClick()
                .withItem(new ItemStack(Items.LAVA_BUCKET));
        scene.world().modifyBlockEntity(melter.below(), TankBlockEntity.class, tank -> {
            tank.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                handler.fill(new FluidStack(Fluids.LAVA, 4000), IFluidHandler.FluidAction.EXECUTE);
            });
        });

        scene.overlay().showText(25)
                .text("Also, don't forget to add fuel like lava")
                .attachKeyFrame()
                .placeNearTarget()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(melter.below(), Direction.WEST));
        scene.idle(30);

        scene.overlay().showText(20)
                .text("Finally, install the casting parts")
                .attachKeyFrame()
                .placeNearTarget()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(melter, Direction.UP));

        scene.idle(30);
        scene.world().showSection(basin, Direction.DOWN);
        scene.idle(5);
        scene.world().showSection(table, Direction.DOWN);
        scene.idle(20);

        scene.markAsFinished();
    }

    public static void burner(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("basic_melter_burner", "Use the Blaze Burner to heat");
        scene.configureBasePlate(0, 0, 5);
        scene.world().showSection(util.select().everywhere(), Direction.UP);

        BlockPos melter = util.grid().at(2, 2, 2);
        Selection selection = util.select().fromTo(melter.below(), melter);

        scene.idle(15);
        scene.overlay().showOutline(PonderPalette.GREEN, selection, selection, 40);
        scene.idle(5);
        scene.overlay().showText(25)
                .text("This is also a correct structure")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().blockSurface(melter, Direction.WEST));
        scene.idle(35);

        scene.overlay().showText(25)
                .text("Provide 400°C temperature and half the rate when extinguished")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(melter.below(), Direction.WEST));
        scene.idle(45);

        scene.overlay().showControls(util.vector().blockSurface(melter.below(), Direction.NORTH), Pointing.RIGHT, 15).rightClick()
                .withItem(new ItemStack(Items.OAK_PLANKS));
        scene.idle(7);
        scene.world().modifyBlock(melter.below(), s -> s.setValue(BlazeBurnerBlock.HEAT_LEVEL, BlazeBurnerBlock.HeatLevel.KINDLED), false);
        scene.idle(20);

        scene.overlay().showText(25)
                .text("Provide 800°C temperature and normal rate when kindled")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(melter.below(), Direction.WEST));
        scene.idle(45);

        scene.overlay().showControls(util.vector().blockSurface(melter.below(), Direction.NORTH), Pointing.RIGHT, 15).rightClick()
                .withItem(AllItems.BLAZE_CAKE.asStack());
        scene.idle(7);
        scene.world().modifyBlock(melter.below(), s -> s.setValue(BlazeBurnerBlock.HEAT_LEVEL, BlazeBurnerBlock.HeatLevel.SEETHING), false);
        scene.idle(20);

        scene.overlay().showText(25)
                .text("Provide 1600°C temperature and double the rate when seething")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(melter.below(), Direction.WEST));
        scene.idle(60);

        scene.markAsFinished();
    }

    public static void casting(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("basic_melter_casting", "Melting and Casting");
        scene.configureBasePlate(0, 0, 5);
        scene.world().showSection(util.select().everywhere(), Direction.UP);

        BlockPos table = util.grid().at(1, 1, 2);
        BlockPos basin = util.grid().at(3, 1, 2);
        BlockPos melter = util.grid().at(2, 2, 2);

        scene.idle(5);
        scene.overlay().showText(20)
                .text("Melting to get molten material")
                .attachKeyFrame()
                .placeNearTarget()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(melter.above(), Direction.UP));
        scene.idle(30);

        ElementLink<EntityElement> itemLink = scene.world().createItemEntity(
                melter.above(2).getCenter(),
                util.vector().of(0, 0.1, 0),
                new ItemStack(Items.RAW_GOLD)
        );
        scene.idle(10);
        scene.world().modifyEntity(itemLink, Entity::discard);
        scene.world().modifyBlockEntity(melter, MelterBlockEntity.class, m -> {
            m.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                handler.insertItem(0, new ItemStack(Items.RAW_GOLD), false);
            });
        });
        scene.world().modifyBlock(melter, block -> block.setValue(MelterBlock.ACTIVE, true), false);

        scene.idle(10);
        scene.overlay().showText(20)
                .text("Waiting to melt")
                .attachKeyFrame()
                .placeNearTarget()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(melter, Direction.NORTH));
        scene.idle(30);

        scene.world().modifyBlockEntity(melter, MelterBlockEntity.class, m -> {
            m.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                handler.extractItem(0, 1, false);
            });
            m.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                handler.fill(new FluidStack(TinkerFluids.moltenGold.get(), 120), IFluidHandler.FluidAction.EXECUTE);
            });
        });
        scene.world().modifyBlock(melter, block -> block.setValue(MelterBlock.ACTIVE, false), false);

        scene.idle(5);
        scene.world().hideSection(util.select().position(melter.above()), Direction.UP);

        scene.idle(10);
        scene.overlay().showControls(util.vector().blockSurface(table.above(), Direction.EAST), Pointing.RIGHT, 20)
                .rightClick();
        for (int i = 0; i < 4; i++) {
            scene.world().modifyBlockEntity(table.above(), FaucetBlockEntity.class, f -> {
                f.onActivationPacket(new FluidStack(TinkerFluids.moltenGold.get(), 30), true);
            });
            scene.world().modifyBlockEntity(melter, MelterBlockEntity.class, m -> {
                m.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.drain(new FluidStack(TinkerFluids.moltenGold.get(), 30), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.world().modifyBlockEntity(table, CastingBlockEntity.Table.class, t -> {
                t.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.fill(new FluidStack(TinkerFluids.moltenGold.get(), 30), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.idle(5);
        }
        scene.idle(10);
        scene.world().modifyBlockEntity(table.above(), FaucetBlockEntity.class, f -> {
            f.onActivationPacket(new FluidStack(TinkerFluids.moltenGold.get(), 0), false);
        });
        scene.idle(45);
        scene.world().modifyBlockEntity(table, CastingBlockEntity.Table.class, t -> {
            t.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
                handler.getStackInSlot(0).setCount(0);
            });
        });

        scene.idle(60);
        scene.markAsFinished();
    }

    public static void pipe(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("basic_melter_pipe", "Using fluid pipes while casting");
        scene.configureBasePlate(0, 0, 5);
        scene.world().showSection(util.select().layer(0), Direction.UP);

        Selection table = util.select().fromTo(1, 1, 1, 3, 1 , 1);
        Selection smeltery = util.select().fromTo(3, 1, 3, 3, 2 , 3);

        BlockPos basic = util.grid().at(3, 2, 3);
        BlockPos cog = util.grid().at(2, 3, 3);
        BlockPos casting = util.grid().at(2, 1, 1);

        scene.idle(5);
        scene.world().showSection(smeltery, Direction.UP);
        scene.idle(5);
        scene.world().showSection(table, Direction.UP);

        scene.idle(10);
        scene.rotateCameraY(90);

        scene.idle(25);
        scene.overlay().showText(25)
                .text("Besides the Faucets, you can also use the Pipe for casting")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(basic, Direction.UP));

        scene.idle(35);
        scene.world().showSection(util.select().position(basic.west(1)), Direction.WEST);
        scene.idle(5);
        scene.world().showSection(util.select().position(basic.west(2)), Direction.WEST);
        BlockPos pipe = basic.west(2).north(2);
        scene.idle(5);
        scene.world().showSection(util.select().position(pipe.south()), Direction.WEST);
        scene.idle(5);
        scene.world().showSection(util.select().position(pipe), Direction.WEST);
        scene.idle(5);
        scene.world().showSection(util.select().position(pipe.east(1)), Direction.WEST);
        scene.idle(5);
        scene.world().showSection(util.select().position(pipe.east(2)), Direction.WEST);
        scene.idle(5);

        scene.rotateCameraY(-90);
        scene.idle(25);

        scene.world().showSection(util.select().position(cog), Direction.SOUTH);
        scene.idle(5);
        scene.world().showSection(util.select().position(cog.west()), Direction.SOUTH);
        scene.idle(5);
        scene.overlay().showControls(cog.west().getCenter(), Pointing.DOWN, 20).rightClick();
        scene.idle(7);

        scene.world().setKineticSpeed(util.select().everywhere(), 32);
        scene.world().modifyKineticSpeed(util.select().position(cog.below()), s -> s * -1);
        scene.effects().rotationDirectionIndicator(cog.west());

        scene.idle(25);
        scene.overlay().showText(25)
                .text("The pipe will transport the fluid to the casting container")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(basic.west(), Direction.NORTH));

        scene.idle(35);

        scene.world().modifyBlockEntity(basic, MelterBlockEntity.class, m -> {
            m.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                handler.drain(new FluidStack(TinkerFluids.moltenIron.get(), 90), IFluidHandler.FluidAction.EXECUTE);
            });
        });
        scene.world().modifyBlockEntity(casting.east(), CastingBlockEntity.Table.class, t -> {
            t.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                handler.fill(new FluidStack(TinkerFluids.moltenIron.get(), 90), IFluidHandler.FluidAction.EXECUTE);
            });
        });
        scene.idle(15);

        scene.world().modifyBlockEntity(basic, MelterBlockEntity.class, m -> {
            m.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                handler.drain(new FluidStack(TinkerFluids.moltenIron.get(), 90), IFluidHandler.FluidAction.EXECUTE);
            });
        });
        scene.world().modifyBlockEntity(casting, CastingBlockEntity.Table.class, t -> {
            t.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                handler.fill(new FluidStack(TinkerFluids.moltenIron.get(), 90), IFluidHandler.FluidAction.EXECUTE);
            });
        });
        scene.idle(15);

        scene.world().modifyBlockEntity(basic, MelterBlockEntity.class, m -> {
            m.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                handler.drain(new FluidStack(TinkerFluids.moltenIron.get(), 180), IFluidHandler.FluidAction.EXECUTE);
            });
        });
        scene.world().modifyBlockEntity(casting.west(), CastingBlockEntity.Table.class, t -> {
            t.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                handler.fill(new FluidStack(TinkerFluids.moltenIron.get(), 180), IFluidHandler.FluidAction.EXECUTE);
            });
        });
        scene.idle(15);

        scene.world().hideSection(util.select().position(pipe), Direction.WEST);
        scene.idle(5);
        scene.world().hideSection(util.select().position(pipe.east(1)), Direction.WEST);
        scene.idle(5);
        scene.world().hideSection(util.select().position(pipe.east(2)), Direction.WEST);

        scene.idle(60);
        scene.markAsFinished();
    }


    public static void spout(SceneBuilder builder, SceneBuildingUtil util) {
        CreateSceneBuilder scene = new CreateSceneBuilder(builder);
        scene.title("basic_melter_spout", "Using spout while casting");
        scene.configureBasePlate(0, 0, 5);
        scene.world().showSection(util.select().layer(0), Direction.UP);

        BlockPos table = util.grid().at(2, 1, 1);
        BlockPos spout = util.grid().at(2, 3, 1);
        BlockPos melter = util.grid().at(2, 2, 3);
        BlockPos godHand =  util.grid().at(1, 2, 2);

        Selection cog = util.select().fromTo(1, 3, 1, 1, 3, 2);

        scene.idle(5);
        scene.world().showSection(util.select().position(melter.below()), Direction.NORTH);
        scene.idle(5);
        scene.world().showSection(util.select().position(melter), Direction.NORTH);
        scene.idle(5);
        scene.world().showSection(util.select().position(table), Direction.UP);
        scene.idle(15);

        scene.overlay().showText(25)
                .text("The table is actually a fluid container")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(table, Direction.UP));
        scene.idle(35);

        scene.world().showSection(util.select().position(melter.above()), Direction.UP);
        scene.idle(5);
        scene.world().showSection(util.select().position(spout.south()), Direction.UP);
        scene.idle(5);
        scene.world().showSection(util.select().position(spout), Direction.UP);
        scene.idle(5);

        scene.overlay().showText(25)
                .text("So spout can be used to cast")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.MEDIUM)
                .pointAt(util.vector().blockSurface(spout, Direction.UP));
        scene.idle(35);

        scene.world().showSection(cog, Direction.UP);
        scene.idle(5);
        scene.overlay().showControls(godHand.getCenter(), Pointing.LEFT, 20).rightClick();
        scene.idle(7);
        scene.world().setKineticSpeed(util.select().everywhere(), 32);
        scene.world().modifyKineticSpeed(util.select().position(spout.south()),f -> f * -1);
        scene.effects().rotationDirectionIndicator(godHand);
        scene.idle(35);

        for (int i = 0; i < 5; i++) {
            scene.world().modifyBlockEntity(melter, MelterBlockEntity.class, m -> {
                m.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.drain(new FluidStack(TinkerFluids.moltenIron.get(), 200), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.world().modifyBlockEntity(spout, SpoutBlockEntity.class, s -> {
                s.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.fill(new FluidStack(TinkerFluids.moltenIron.get(), 200), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.idle(5);
        }

        scene.idle(60);
        scene.markAsFinished();
    }
}
