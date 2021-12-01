package com.mrbysco.padoru.client;

import com.mrbysco.padoru.client.render.PadoruRenderer;
import com.mrbysco.padoru.init.ModRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {
    public static void registerRenders(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModRegistry.PADORU.get(), PadoruRenderer::new);
    }
}
