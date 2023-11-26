package com.mrbysco.padoru.client.render;

import com.mrbysco.padoru.client.ClientHandler;
import com.mrbysco.padoru.client.model.PadoruModel;
import com.mrbysco.padoru.entity.Padoru;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class PadoruRenderer extends MobRenderer<Padoru, PadoruModel<Padoru>> {
	private static final ResourceLocation PADORU_TEXTURES = new ResourceLocation(com.mrbysco.padoru.Padoru.MOD_ID, "textures/entity/padoru.png");

	public PadoruRenderer(Context context) {
		super(context, new PadoruModel<>(context.bakeLayer(ClientHandler.PADORU)), 0.25F);
	}

	@Override
	public Vec3 getRenderOffset(Padoru entity, float offset) {
		return super.getRenderOffset(entity, offset).add(0, -1.5, 0);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(Padoru entity) {
		return PADORU_TEXTURES;
	}
}