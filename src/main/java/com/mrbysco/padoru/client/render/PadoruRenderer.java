package com.mrbysco.padoru.client.render;

import com.mrbysco.padoru.Padoru;
import com.mrbysco.padoru.client.model.PadoruModel;
import com.mrbysco.padoru.entity.PadoruEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class PadoruRenderer extends MobRenderer<PadoruEntity, PadoruModel<PadoruEntity>> {
    private static final ResourceLocation PADORU_TEXTURES = new ResourceLocation(Padoru.MOD_ID, "textures/entity/padoru.png");

    public PadoruRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PadoruModel(), 0.25F);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(PadoruEntity entity) {
        return PADORU_TEXTURES;
    }
}