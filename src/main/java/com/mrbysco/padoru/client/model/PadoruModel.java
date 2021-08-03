package com.mrbysco.padoru.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mrbysco.padoru.entity.PadoruEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class PadoruModel<T extends PadoruEntity> extends EntityModel<T> {
    private final ModelRenderer Padoru;
    private final ModelRenderer Head;
    private final ModelRenderer Bow;
    private final ModelRenderer BowStrings;
    private final ModelRenderer Hat;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;
    private final ModelRenderer Skirt;
    private final ModelRenderer LeftFeet;
    private final ModelRenderer RightFeet;

    public PadoruModel() {
        textureWidth = 64;
        textureHeight = 64;

        Padoru = new ModelRenderer(this);
        Padoru.setRotationPoint(0.0F, 24.0F, 0.0F);


        Head = new ModelRenderer(this);
        Head.setRotationPoint(-0.5F, -11.0F, 0.0F);
        Padoru.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-3.5F, -7.0F, -4.0F, 7.0F, 7.0F, 8.0F, 0.0F, false);
        Head.setTextureOffset(22, 0).addBox(-2.5F, -5.0F, 3.0F, 5.0F, 3.0F, 3.0F, 0.0F, false);

        Bow = new ModelRenderer(this);
        Bow.setRotationPoint(0.5F, 11.0F, 0.0F);
        Head.addChild(Bow);
        setRotationAngle(Bow, 0.0873F, 0.0F, 0.0F);
        Bow.setTextureOffset(30, 11).addBox(-2.0F, -12.0F, 5.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        BowStrings = new ModelRenderer(this);
        BowStrings.setRotationPoint(0.0F, 0.0F, 0.0F);
        Bow.addChild(BowStrings);
        setRotationAngle(BowStrings, 0.0873F, 0.0F, 0.0F);
        BowStrings.setTextureOffset(30, 8).addBox(-4.0F, -10.25F, 6.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);
        BowStrings.setTextureOffset(30, 14).addBox(0.0F, -10.25F, 6.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        Hat = new ModelRenderer(this);
        Hat.setRotationPoint(0.5F, 11.0F, 0.0F);
        Head.addChild(Hat);
        setRotationAngle(Hat, 0.0873F, -0.0873F, 0.1745F);
        Hat.setTextureOffset(0, 15).addBox(-6.0F, -19.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);
        Hat.setTextureOffset(0, 23).addBox(-4.5748F, -20.9054F, -2.7795F, 4.0F, 2.0F, 4.0F, 0.0F, false);
        Hat.setTextureOffset(0, 29).addBox(-2.0748F, -22.1554F, -3.5295F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        RightArm = new ModelRenderer(this);
        RightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        Padoru.addChild(RightArm);
        setRotationAngle(RightArm, 0.0F, 0.0F, -0.2618F);
        RightArm.setTextureOffset(0, 51).addBox(-6.0F, -10.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        Padoru.addChild(LeftArm);
        setRotationAngle(LeftArm, 0.0F, 0.0F, 0.2618F);
        LeftArm.setTextureOffset(0, 45).addBox(0.0F, -10.0F, -1.0F, 5.0F, 2.0F, 2.0F, 0.0F, false);

        Skirt = new ModelRenderer(this);
        Skirt.setRotationPoint(-0.5F, -5.0F, 0.0F);
        Padoru.addChild(Skirt);
        Skirt.setTextureOffset(38, 47).addBox(-3.5F, -6.0F, -3.0F, 7.0F, 3.0F, 6.0F, 0.0F, false);
        Skirt.setTextureOffset(40, 13).addBox(-5.5F, 0.0F, -5.0F, 2.0F, 3.0F, 10.0F, 0.0F, false);
        Skirt.setTextureOffset(40, 0).addBox(3.5F, 0.0F, -5.0F, 2.0F, 3.0F, 10.0F, 0.0F, false);
        Skirt.setTextureOffset(46, 26).addBox(-3.5F, 0.0F, 3.0F, 7.0F, 3.0F, 2.0F, 0.0F, false);
        Skirt.setTextureOffset(46, 31).addBox(-3.5F, 0.0F, -5.0F, 7.0F, 3.0F, 2.0F, 0.0F, false);
        Skirt.setTextureOffset(30, 36).addBox(-4.5F, -3.0F, -4.0F, 9.0F, 3.0F, 8.0F, 0.0F, false);

        LeftFeet = new ModelRenderer(this);
        LeftFeet.setRotationPoint(0.0F, 0.0F, 0.0F);
        Padoru.addChild(LeftFeet);
        LeftFeet.setTextureOffset(0, 57).addBox(0.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        RightFeet = new ModelRenderer(this);
        RightFeet.setRotationPoint(0.0F, 0.0F, 0.0F);
        Padoru.addChild(RightFeet);
        RightFeet.setTextureOffset(10, 57).addBox(-3.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        this.Head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.Head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.Head.rotateAngleZ = 0.0F;

        this.LeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F;
        this.RightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount * 0.5F;

        this.LeftFeet.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
        this.LeftFeet.rotateAngleY = 0.0F;
        this.RightFeet.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
        this.RightFeet.rotateAngleY = 0.0F;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        Padoru.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
