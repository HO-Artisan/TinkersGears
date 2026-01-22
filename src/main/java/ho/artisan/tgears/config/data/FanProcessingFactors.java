package ho.artisan.tgears.config.data;

import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import net.createmod.catnip.config.ConfigBase;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleSupplier;

public class FanProcessingFactors extends ConfigBase {
    private static final Object2DoubleMap<ResourceLocation> DEFAULT_FACTORS = new Object2DoubleOpenHashMap<>();

    protected final Map<ResourceLocation, ForgeConfigSpec.ConfigValue<Double>> factors = new HashMap<>();

    static {
        setFactor(ResourceLocation.tryParse("create:smoking"), -0.5d);
        setFactor(ResourceLocation.tryParse("create:haunting"), -0.75d);
        setFactor(ResourceLocation.tryParse("create:blasting"), -1.0d);
        setFactor(ResourceLocation.tryParse("create_henry:seething"), -2.0f);

        // Default 1.0f
        setFactor(ResourceLocation.tryParse("create:splashing"), 1.5f);
        setFactor(ResourceLocation.tryParse("create_henry:freezing"), 2.0f);
    }

    public static void setFactor(ResourceLocation resourceLocation, double value) {
        DEFAULT_FACTORS.put(resourceLocation, value);
    }

    @Override
    public void registerAll(ForgeConfigSpec.Builder builder) {
        builder.comment(".", Comments.factor).push("factor");
        DEFAULT_FACTORS.forEach((id, value) -> this.factors.put(id, builder.define(id.getPath(), value)));
        builder.pop();
    }

    @Nullable
    public DoubleSupplier getFactor(FanProcessingType type) {
        ResourceLocation id = CreateBuiltInRegistries.FAN_PROCESSING_TYPE.getKey(type);
        ForgeConfigSpec.ConfigValue<Double> value = this.factors.get(id);
        return value == null ? null : value::get;
    }

    @Override
    public String getName() {
        return "fanProcessingFactors";
    }

    private static class Comments {
        static String factor = "Configure the cooling factor of fan processing.";
    }
}
