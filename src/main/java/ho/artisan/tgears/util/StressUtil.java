package ho.artisan.tgears.util;

import ho.artisan.tgears.TinkersGears;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.tools.nbt.IModDataView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public final class StressUtil {
    public static final ResourceLocation STRESS_KEY = TinkersGears.asResource("stress");
    public static final ResourceLocation CAPACITY_KEY= TinkersGears.asResource("stress_capacity");
    public static final ResourceLocation EFFICACY_KEY = TinkersGears.asResource("stress_efficacy");

    private StressUtil() {}

    public static void removeStress(ModDataNBT data) {
        data.remove(STRESS_KEY);
    }

    public static void setStress(ModDataNBT data, int value) {
        int stress = Math.max(0, Math.min(value, getStressCapacity(data)));
        data.putInt(STRESS_KEY, stress);
    }

    public static void setStressCapacity(ModDataNBT data, int value) {
        data.putInt(CAPACITY_KEY, value);
    }

    public static void setEfficacy(ModDataNBT data, int value) {
        data.putInt(EFFICACY_KEY, value);
    }

    public static void changeEfficacy(ModDataNBT data, float multiplier) {
        data.putInt(EFFICACY_KEY, (int) (getEfficacy(data) * multiplier));
    }

    public static void changeCapacity(ModDataNBT data, float multiplier) {
        data.putInt(CAPACITY_KEY, (int) (getStressCapacity(data) * multiplier));
    }

    public static void increaseStress(ModDataNBT data, int amount) {
        setStress(data, getStress(data) + amount);
    }

    public static void decreaseStress(ModDataNBT data, int amount) {
        setStress(data, getStress(data) - amount);
    }

    public static int getEfficacy(IModDataView data) {
        return data.getInt(EFFICACY_KEY);
    }

    public static int getStressCapacity(IModDataView data) {
        return data.getInt(CAPACITY_KEY);
    }

    public static int getStress(IModDataView data) {
        return data.getInt(STRESS_KEY);
    }
}
