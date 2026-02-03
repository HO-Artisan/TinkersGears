package ho.artisan.tgears.client.ponder.scene;

import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.simibubi.create.content.processing.burner.BlazeBurnerBlock;

import ho.artisan.tgears.client.ponder.TGSceneBuilder;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.component.SearedTankBlock;
import slimeknights.tconstruct.smeltery.block.controller.MelterBlock;
import slimeknights.tconstruct.smeltery.block.entity.CastingBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.FaucetBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.component.TankBlockEntity;
import slimeknights.tconstruct.smeltery.block.entity.controller.MelterBlockEntity;
import slimeknights.tconstruct.smeltery.item.CopperCanItem;

public class CastingScene {
    private CastingScene() {
    }

    public static void basic(SceneBuilder builder, SceneBuildingUtil util) {
        TGSceneBuilder scene = new TGSceneBuilder(builder);
        scene.title("casting", "Use fluid and faucet to cast");
        scene.init5x5(util);
        BlockPos basin = util.grid().at(1, 1, 2);
        BlockPos table = util.grid().at(3, 1, 2);
        BlockPos tank = util.grid().at(2, 2, 2);
        Selection selection0 = util.select().fromTo(table, basin);
        Selection selection1 = util.select().fromTo(table.above(), basin.above());

        scene.idle(10);
        scene.overlay().showText(20)
                .text("Casting can convert fluid into items or blocks.")
                .attachKeyFrame()
                .colored(PonderPalette.GREEN);
        scene.world().showSection(selection0, Direction.DOWN);
        scene.idle(30);
        scene.overlay().showOutline(PonderPalette.GREEN, basin, util.select().fromTo(basin, basin), 40);
        scene.overlay().showOutline(PonderPalette.GREEN, table, util.select().fromTo(table, table), 40);
        scene.overlay().showText(40)
                .text("We need a basin or a table for casting.")
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().topOf(basin))
                .pointAt(util.vector().topOf(table));
        scene.idle(40);
        scene.world().showSection(selection1, Direction.DOWN);
        scene.idle(20);
        scene.overlay().showControls(util.vector().blockSurface(basin.above(), Direction.NORTH), Pointing.RIGHT, 15)
                .rightClick();
        scene.idle(5);
        for (int i = 0; i < 82; i++) {
            scene.world().modifyBlockEntity(basin.above(), FaucetBlockEntity.class, f -> {
                f.onActivationPacket(new FluidStack(TinkerFluids.moltenGold.get(), 10), true);
            });
            scene.world().modifyBlockEntity(tank, TankBlockEntity.class, m -> {
                m.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.drain(new FluidStack(TinkerFluids.moltenGold.get(), 10), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.world().modifyBlockEntity(basin, CastingBlockEntity.Basin.class, t -> {
                t.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.fill(new FluidStack(TinkerFluids.moltenGold.get(), 10), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.idle(1);
        }
        scene.idle(5);
        scene.world().modifyBlockEntity(basin.above(), FaucetBlockEntity.class, f -> {
            f.onActivationPacket(new FluidStack(TinkerFluids.moltenGold.get(), 0), false);
        });
        scene.idle(180);
        scene.markAsFinished();
    }

    public static void use_casts(SceneBuilder builder, SceneBuildingUtil util) {
        TGSceneBuilder scene = new TGSceneBuilder(builder);
        scene.title("use_casts", "Use casts");
        scene.init5x5(util);
        BlockPos table1 = util.grid().at(1, 1, 2);
        BlockPos table2 = util.grid().at(2, 1, 1);
        BlockPos tank = util.grid().at(2, 2, 2);
        Selection selection = util.select().fromTo(table1, table2.above());
        scene.idle(10);
        scene.world().showSection(selection, Direction.DOWN);
        scene.overlay().showText(20)
                .text("Some items need to be cast using casts.")
                .attachKeyFrame()
                .colored(PonderPalette.GREEN);
        scene.idle(20);
        scene.overlay().showText(20)
                .text("Use an object of a similar shape to press a disposable sand mold")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().topOf(table2));
        scene.idle(20);
        scene.overlay().showControls(util.vector().blockSurface(table2.above(), Direction.NORTH), Pointing.RIGHT, 20)
                .rightClick().withItem(new ItemStack(TinkerSmeltery.blankSandCast.get()));
        scene.idle(5);
        scene.world().modifyBlockEntity(table2, CastingBlockEntity.Table.class, t -> {
            if (t == null)
                return;
            t.setItem(0, new ItemStack(TinkerSmeltery.blankSandCast.get()));
        });
        scene.idle(20);
        scene.overlay().showControls(util.vector().blockSurface(table2.above(), Direction.NORTH), Pointing.RIGHT, 20)
                .rightClick().withItem(new ItemStack(Items.BRICK));
        scene.idle(5);
        scene.world().modifyBlockEntity(table2, CastingBlockEntity.Table.class, t -> {
            if (t == null)
                return;
            t.setItem(0, new ItemStack(TinkerSmeltery.ingotCast.getSand()));
            t.setItem(1, new ItemStack(Items.BRICK));
        });
        scene.idle(20);
        scene.overlay().showControls(util.vector().blockSurface(table2.above(), Direction.NORTH), Pointing.RIGHT, 20)
                .rightClick();
        scene.idle(5);
        scene.world().modifyBlockEntity(table2, CastingBlockEntity.Table.class, t -> {
            if (t == null)
                return;
            t.setItem(1, ItemStack.EMPTY);
        });
        scene.idle(20);
        for (int i = 0; i < 10; i++) {
            scene.world().modifyBlockEntity(table2.above(), FaucetBlockEntity.class, f -> {
                f.onActivationPacket(new FluidStack(TinkerFluids.moltenGold.get(), 10), true);
            });
            scene.world().modifyBlockEntity(tank, TankBlockEntity.class, m -> {
                m.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.drain(new FluidStack(TinkerFluids.moltenGold.get(), 10), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.world().modifyBlockEntity(table2, CastingBlockEntity.Table.class, t -> {
                if (t == null)
                    return;
                t.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.fill(new FluidStack(TinkerFluids.moltenGold.get(), 10), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.idle(1);
        }
        scene.idle(5);
        scene.world().modifyBlockEntity(table2.above(), FaucetBlockEntity.class, f -> {
            f.onActivationPacket(new FluidStack(TinkerFluids.moltenGold.get(), 0), false);
        });
        scene.idle(60);
        scene.world().modifyBlockEntity(table2, CastingBlockEntity.Table.class, t -> {
            if (t == null)
                return;
            t.setItem(0, ItemStack.EMPTY);
        });
        scene.idle(20);
        scene.overlay().showText(20)
                .text("Or use an indestructible gold mold")
                .placeNearTarget()
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().topOf(table1));
        scene.idle(20);
        scene.overlay().showControls(util.vector().blockSurface(table1.above(), Direction.NORTH), Pointing.RIGHT, 20)
                .rightClick().withItem(new ItemStack(TinkerSmeltery.ingotCast.get()));
        scene.idle(5);
        scene.world().modifyBlockEntity(table1, CastingBlockEntity.Table.class, t -> {
            if (t == null)
                return;
            t.setItem(0, new ItemStack(TinkerSmeltery.ingotCast.get()));
        });
        scene.idle(20);
        for (int i = 0; i < 10; i++) {
            scene.world().modifyBlockEntity(table1.above(), FaucetBlockEntity.class, f -> {
                f.onActivationPacket(new FluidStack(TinkerFluids.moltenGold.get(), 10), true);
            });
            scene.world().modifyBlockEntity(tank, TankBlockEntity.class, m -> {
                m.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.drain(new FluidStack(TinkerFluids.moltenGold.get(), 10), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.world().modifyBlockEntity(table1, CastingBlockEntity.Table.class, t -> {
                if (t == null)
                    return;
                t.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.fill(new FluidStack(TinkerFluids.moltenGold.get(), 10), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.idle(1);
        }
        scene.idle(5);
        scene.world().modifyBlockEntity(table1.above(), FaucetBlockEntity.class, f -> {
            f.onActivationPacket(new FluidStack(TinkerFluids.moltenGold.get(), 0), false);
        });
        scene.idle(60);
        scene.markAsFinished();
    }

    public static void auto_casting(SceneBuilder builder, SceneBuildingUtil util) {
        TGSceneBuilder scene = new TGSceneBuilder(builder);
        scene.title("auto_casting", "Auto casting");
        scene.init5x5(util);
        BlockPos table = util.grid().at(1, 1, 2);
        BlockPos input = util.grid().at(0, 1, 2);
        BlockPos output = util.grid().at(1, 1, 1);
        BlockPos tank = util.grid().at(2, 2, 2);
        Selection selection = util.select().fromTo(tank.above(), util.grid().at(0, 1, 0));
        scene.idle(10);
        scene.world().showSection(selection, Direction.DOWN);
        scene.overlay().showText(20)
                .text("With the help of redstone devices, we can automate casting.")
                .attachKeyFrame()
                .colored(PonderPalette.GREEN);
        scene.idle(20);
        scene.overlay().showOutline(PonderPalette.GREEN, input, util.select().fromTo(input, input), 20);
        scene.overlay().showOutline(PonderPalette.GREEN, output, util.select().fromTo(output, output), 20);
        scene.overlay().showText(20)
                .text("Automate input and output using a funnel")
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().centerOf(input));
        scene.idle(20);
        scene.overlay().showControls(util.vector().centerOf(input), Pointing.RIGHT, 15)
                .rightClick().withItem(new ItemStack(TinkerSmeltery.ingotCast.get()));
        scene.world().modifyBlockEntity(table, CastingBlockEntity.Table.class, t -> {
            if (t == null)
                return;
            t.setItem(0, new ItemStack(TinkerSmeltery.ingotCast.get()));
        });
        scene.idle(20);
        scene.overlay().showOutline(PonderPalette.GREEN, output,
                util.select().fromTo(table.above().above(), table.above().above()), 20);
        scene.overlay().showText(20)
                .text("Automate pouring with redstone signal")
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.select().fromTo(input, input.east()).getCenter());
        scene.idle(20);
        scene.overlay().showControls(util.vector().centerOf(table.above().above()), Pointing.RIGHT, 15)
                .rightClick();
        scene.idle(5);
        scene.world().modifyBlock(table.above().above(), block -> block.setValue(LeverBlock.POWERED, true), false);
        for (int i = 0; i < 10; i++) {
            scene.world().modifyBlockEntity(table.above(), FaucetBlockEntity.class, f -> {
                f.onActivationPacket(new FluidStack(TinkerFluids.moltenGold.get(), 10), true);
            });
            scene.world().modifyBlockEntity(tank, TankBlockEntity.class, m -> {
                m.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.drain(new FluidStack(TinkerFluids.moltenGold.get(), 10), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.world().modifyBlockEntity(table, CastingBlockEntity.Table.class, t -> {
                if (t == null)
                    return;
                t.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.fill(new FluidStack(TinkerFluids.moltenGold.get(), 10), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.idle(1);
        }
        scene.idle(5);
        scene.world().modifyBlockEntity(table.above(), FaucetBlockEntity.class, f -> {
            f.onActivationPacket(new FluidStack(TinkerFluids.moltenGold.get(), 0), false);
        });
        scene.idle(60);
        scene.world().createItemEntity(util.vector().topOf(output.below()), new Vec3(0, 0, 0),
                new ItemStack(Items.GOLD_INGOT, 1));
        scene.world().modifyBlockEntity(table, CastingBlockEntity.Table.class, t -> {
            if (t == null)
                return;
            t.deserializeNBT(new CompoundTag());
            t.setItem(0, new ItemStack(TinkerSmeltery.ingotCast.get()));
            t.updateFluidTo(FluidStack.EMPTY);
        });
        for (int i = 0; i < 10; i++) {
            scene.world().modifyBlockEntity(table.above(), FaucetBlockEntity.class, f -> {
                f.onActivationPacket(new FluidStack(TinkerFluids.moltenGold.get(), 10), true);
            });
            scene.world().modifyBlockEntity(tank, TankBlockEntity.class, m -> {
                m.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.drain(new FluidStack(TinkerFluids.moltenGold.get(), 10), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.world().modifyBlockEntity(table, CastingBlockEntity.Table.class, t -> {
                if (t == null)
                    return;
                t.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                    handler.fill(new FluidStack(TinkerFluids.moltenGold.get(), 10), IFluidHandler.FluidAction.EXECUTE);
                });
            });
            scene.idle(1);
        }
        scene.idle(5);
        scene.world().modifyBlockEntity(table.above(), FaucetBlockEntity.class, f -> {
            f.onActivationPacket(new FluidStack(TinkerFluids.moltenGold.get(), 0), false);
        });
        scene.idle(60);
        scene.markAsFinished();
    }

    public static void more_filling_methods(SceneBuilder builder, SceneBuildingUtil util) {
        TGSceneBuilder scene = new TGSceneBuilder(builder);
        scene.title("more_filling_methods", "More filling methods");
        scene.init5x5(util);
        BlockPos table1 = util.grid().at(1, 1, 1);
        BlockPos table2 = util.grid().at(1, 1, 3);
        BlockPos bump = util.grid().at(3, 1, 2);
        BlockPos tank = util.grid().at(3, 1, 1);
        Selection selection = util.select().fromTo(tank.above(), table2);
        scene.idle(10);
        scene.world().showSection(selection, Direction.DOWN);
        scene.overlay().showText(20)
                .text("Besides the faucet,")
                .attachKeyFrame()
                .colored(PonderPalette.GREEN);
        scene.idle(25);
        scene.overlay().showText(20)
                .text("we can also use a bump to fill the table and the basin,")
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().centerOf(bump));
        scene.idle(25);
        scene.world().setKineticSpeed(util.select().everywhere(), 32);
        scene.world().modifyKineticSpeed(util.select().position(3, 1, 2), s -> s * -1);
        scene.world().propagatePipeChange(bump);
        scene.idle(50);
        scene.world().setKineticSpeed(util.select().everywhere(), 0);
        scene.idle(60);
        scene.overlay().showText(20)
                .text("or directly pour liquid from an item.")
                .attachKeyFrame()
                .colored(PonderPalette.GREEN)
                .pointAt(util.vector().topOf(table1));
        scene.idle(25);
        scene.overlay().showControls(util.vector().topOf(table1), Pointing.RIGHT, 15)
                .rightClick().withItem(CopperCanItem.setFluid(new ItemStack(TinkerSmeltery.copperCan.get(), 1),
                        new FluidStack(TinkerFluids.moltenGold.get(), 90)));
        scene.idle(5);
        scene.world().modifyBlockEntity(table1, CastingBlockEntity.Table.class, t -> {
            if (t == null)
                return;
            t.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(handler -> {
                handler.fill(new FluidStack(TinkerFluids.moltenGold.get(), 90), IFluidHandler.FluidAction.EXECUTE);
            });
        });
        scene.idle(60);
        scene.markAsFinished();
    }

    public static void table(SceneBuilder builder, SceneBuildingUtil util) {
        TGSceneBuilder scene = new TGSceneBuilder(builder);
        scene.title("table_cooling", "Use fans to accelerate cooling");
        scene.init5x5(util);

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
