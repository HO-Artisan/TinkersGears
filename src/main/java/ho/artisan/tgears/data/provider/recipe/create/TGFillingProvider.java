package ho.artisan.tgears.data.provider.recipe.create;

import com.mrh0.createaddition.index.CAItems;
import com.simibubi.create.api.data.recipe.FillingRecipeGen;
import ho.artisan.tgears.TinkersGears;
import ho.artisan.tgears.index.TGBlocks;
import ho.artisan.tgears.index.TGItems;
import ho.artisan.tgears.index.TGTagKeys;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import plus.dragons.createenchantmentindustry.entry.CeiFluids;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.gadgets.TinkerGadgets;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.shared.block.SlimeType;
import slimeknights.tconstruct.world.block.FoliageType;

import static ho.artisan.tgears.compat.TinkersGearsCompat.*;

public final class TGFillingProvider extends FillingRecipeGen {

    public TGFillingProvider(PackOutput output) {
        super(output, TinkersGears.MOD_ID);
        cakeRecipes();
        machineRecipes();
        foodRecipes();
    }

    private void foodRecipes() {
        create("blazing_chocolate_glazed_berries", b -> b.require(TGTagKeys.Fluids.BLAZING_CHOCOLATE, FluidValues.BOTTLE)
                .require(Items.SWEET_BERRIES)
                .output(TGItems.BLAZING_CHOCOLATE_BERRIES));
    }

    private void machineRecipes() {
        create("silktouch_drill", b -> b.require(TinkerFluids.moltenRoseGold.getTag(), FluidValues.INGOT * 4)
                .require(TGBlocks.TINKER_DRILL)
                .output(TGBlocks.TINKER_SILKTOUCH_DRILL));

        create("silktouch_crushing_wheel", b -> b.require(TinkerFluids.moltenRoseGold.getTag(), FluidValues.INGOT * 8)
                .require(TGBlocks.TINKER_CRUSHING_WHEEL)
                .output(TGBlocks.TINKER_SILKY_CRUSHING_WHEEL));

        if (ENCHANTMENT_LOADED) {
            create("fortune_drill", b -> b.withCondition(new ModLoadedCondition(CEI))
                    .require(CeiFluids.EXPERIENCE.getSource(), 108)
                    .require(TGBlocks.TINKER_DRILL)
                    .output(TGBlocks.TINKER_FORTUNE_DRILL));

            create("fortune_crushing_wheel", b -> b.withCondition(new ModLoadedCondition(CEI))
                    .require(CeiFluids.EXPERIENCE.getSource(), 432)
                    .require(TGBlocks.TINKER_CRUSHING_WHEEL)
                    .output(TGBlocks.TINKER_FORTUNE_CRUSHING_WHEEL));
        }
    }

    private void cakeRecipes() {
        cake("earth", TinkerFluids.slime.get(SlimeType.EARTH), new ItemStack(TinkerGadgets.cake.get(FoliageType.EARTH)));
        cake("sky", TinkerFluids.slime.get(SlimeType.SKY), new ItemStack(TinkerGadgets.cake.get(FoliageType.SKY)));
        cake("ender", TinkerFluids.slime.get(SlimeType.ENDER), new ItemStack(TinkerGadgets.cake.get(FoliageType.ENDER)));
    }

    private void cake(String id, Fluid fluid, ItemStack output) {
        create("cake/" + id, b -> b.require(fluid, 1000)
                .require(CAItems.CAKE_BASE)
                .output(output).withCondition(new ModLoadedCondition(CA)));
    }

}