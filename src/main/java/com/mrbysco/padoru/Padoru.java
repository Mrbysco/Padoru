package com.mrbysco.padoru;

import com.mrbysco.padoru.init.EntityRegistry;
import com.mrbysco.padoru.render.ClientHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Padoru.MOD_ID)
public class Padoru
{
    public static final String MOD_ID = "padoru";
    public static final Logger LOGGER = LogManager.getLogger();

    public Padoru() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, PadoruConfig.spawnSpec);
        FMLJavaModLoadingContext.get().getModEventBus().register(PadoruConfig.class);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            MinecraftForge.EVENT_BUS.addListener(ClientHandler::registerRenders);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientHandler::registerRenders);
        });
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        EntityRegistry.addSpawn();
    }
}
