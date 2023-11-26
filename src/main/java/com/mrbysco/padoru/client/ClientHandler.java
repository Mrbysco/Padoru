package com.mrbysco.padoru.client;

import com.mrbysco.padoru.PadoruMod;
import com.mrbysco.padoru.client.model.PadoruModel;
import com.mrbysco.padoru.client.render.PadoruRenderer;
import com.mrbysco.padoru.init.ModRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ClientHandler {
	public static final ModelLayerLocation PADORU = new ModelLayerLocation(new ResourceLocation(PadoruMod.MOD_ID, "padoru"), "padoru");

	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModRegistry.PADORU.get(), PadoruRenderer::new);
	}

	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(PADORU, PadoruModel::createBodyLayer);
	}
}
