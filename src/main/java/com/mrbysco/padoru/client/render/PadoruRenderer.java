package com.mrbysco.padoru.client.render;

import com.mrbysco.padoru.PadoruMod;
import com.mrbysco.padoru.client.ClientHandler;
import com.mrbysco.padoru.client.model.PadoruModel;
import com.mrbysco.padoru.client.state.PadoruRenderState;
import com.mrbysco.padoru.entity.Padoru;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class PadoruRenderer extends MobRenderer<Padoru, PadoruRenderState, PadoruModel> {
	private static final ResourceLocation PADORU_TEXTURES = ResourceLocation.fromNamespaceAndPath(PadoruMod.MOD_ID, "textures/entity/padoru.png");

	public PadoruRenderer(Context context) {
		super(context, new PadoruModel(context.bakeLayer(ClientHandler.PADORU)), 0.25F);
	}

	@Override
	public Vec3 getRenderOffset(PadoruRenderState renderState) {
		return super.getRenderOffset(renderState).add(0, -1.5, 0);
	}

	@Override
	public PadoruRenderState createRenderState() {
		return new PadoruRenderState();
	}

	@Override
	public void extractRenderState(Padoru padoru, PadoruRenderState renderState, float partialTick) {
		super.extractRenderState(padoru, renderState, partialTick);
		renderState.spinAnimationState.copyFrom(padoru.spinAnimationState);
	}

	@NotNull
	@Override
	public ResourceLocation getTextureLocation(PadoruRenderState renderState) {
		return PADORU_TEXTURES;
	}
}