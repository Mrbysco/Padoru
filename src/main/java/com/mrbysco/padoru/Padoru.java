package com.mrbysco.padoru;

import com.mrbysco.padoru.client.ClientHandler;
import com.mrbysco.padoru.init.ModRegistry;
import com.mrbysco.padoru.init.ModSpawns;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Padoru.MOD_ID)
public class Padoru {
    public static final String MOD_ID = "padoru";
    public static final Logger LOGGER = LogManager.getLogger();

    public Padoru() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, PadoruConfig.spawnSpec);
        eventBus.register(PadoruConfig.class);

        ModRegistry.ENTITIES.register(eventBus);
        ModRegistry.ITEMS.register(eventBus);
        ModRegistry.SOUND_EVENTS.register(eventBus);

        eventBus.addListener(this::setup);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            eventBus.addListener(ClientHandler::registerRenders);
            eventBus.addListener(ClientHandler::registerItemColors);
        });
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModSpawns.entityAttributes();
        DeferredWorkQueue.runLater(ModSpawns::addSpawn);
    }
}
