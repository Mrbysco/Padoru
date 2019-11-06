package com.mrbysco.padoru.render;

import com.mrbysco.padoru.Padoru;
import com.mrbysco.padoru.entity.PadoruEntity;
import com.mrbysco.padoru.render.model.PadoruModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class PadoruRenderer extends MobRenderer<PadoruEntity, PadoruModel> {
    private static final ResourceLocation PADORU_TEXTURES = new ResourceLocation(Padoru.MOD_ID, "textures/entity/padoru.png");

    public PadoruRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PadoruModel(), 0.25F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(PadoruEntity entity) {
        return PADORU_TEXTURES;
    }
}