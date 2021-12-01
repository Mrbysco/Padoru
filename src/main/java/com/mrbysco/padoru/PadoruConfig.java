package com.mrbysco.padoru;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class PadoruConfig {
    public static class Spawn {
        public final ForgeConfigSpec.IntValue minGroup;
        public final ForgeConfigSpec.IntValue maxGroup;
        public final ForgeConfigSpec.IntValue weight;

        Spawn(ForgeConfigSpec.Builder builder) {
            builder.push("Spawn settings")
            .comment("Configure spawning rate of Nero Claudius");

            minGroup = builder
                    .comment("Min group size [Default: 1]")
                    .defineInRange("minGroup", 1, 0, 64);

            maxGroup = builder
                    .comment("Max group size [Default: 4]")
                    .defineInRange("maxGroup", 4, 0, 64);

            weight = builder
                    .comment("Spawn weight [Default: 2]")
                    .defineInRange("minGroup", 2, 0, 100);
            builder.pop();
        }
    }

    public static final ForgeConfigSpec spawnSpec;
    public static final Spawn SPAWN;
    static {
        final Pair<PadoruConfig.Spawn, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(PadoruConfig.Spawn::new);
        spawnSpec = specPair.getRight();
        SPAWN = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
        Padoru.LOGGER.debug("Loaded Padoru's config file {}", configEvent.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
        Padoru.LOGGER.fatal("Padoru's config just got changed on the file system!");
    }
}
