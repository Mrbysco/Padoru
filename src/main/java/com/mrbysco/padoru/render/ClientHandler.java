package com.mrbysco.padoru.render;

import com.mrbysco.padoru.entity.PadoruEntity;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientHandler {
    public static void registerRenders(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(PadoruEntity.class, PadoruRenderer::new);
    }
}
