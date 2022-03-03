package com.mrbysco.padoru.client;

import com.mrbysco.padoru.Padoru;
import com.mrbysco.padoru.client.model.PadoruModel;
import com.mrbysco.padoru.client.render.PadoruRenderer;
import com.mrbysco.padoru.init.ModRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ClientHandler {
	public static final ModelLayerLocation PADORU = new ModelLayerLocation(new ResourceLocation(Padoru.MOD_ID, "padoru"), "padoru");

	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModRegistry.PADORU.get(), PadoruRenderer::new);
	}

	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(PADORU, PadoruModel::createBodyLayer);
	}
}
