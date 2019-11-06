package com.mrbysco.padoru.render.model;

import com.mrbysco.padoru.entity.PadoruEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.util.math.MathHelper;

public class PadoruModel extends EntityModel<PadoruEntity> {
    private final RendererModel Padoru;
    private final RendererModel Head;
    private final RendererModel Bow;
    private final RendererModel BowStrings;
    private final RendererModel Hat;
    private final RendererModel RightArm;
    private final RendererModel LeftArm;
    private final RendererModel Skirt;
    private final RendererModel LeftFeet;
    private final RendererModel RightFeet;

    public PadoruModel() {
        textureWidth = 64;
        textureHeight = 64;

        Padoru = new RendererModel(this);
        Padoru.setRotationPoint(0.0F, 24.0F, 0.0F);

        Head = new RendererModel(this);
        Head.setRotationPoint(-0.5F, -11.0F, 0.0F);
        Padoru.addChild(Head);
        Head.cubeList.add(new ModelBox(Head, 0, 0, -3.5F, -7.0F, -4.0F, 7, 7, 8, 0.0F, false));
        Head.cubeList.add(new ModelBox(Head, 22, 0, -2.5F, -5.0F, 3.0F, 5, 3, 3, 0.0F, false));

        Bow = new RendererModel(this);
        Bow.setRotationPoint(0.5F, 11.0F, 0.0F);
        setRotationAngle(Bow, 0.0873F, 0.0F, 0.0F);
        Head.addChild(Bow);
        Bow.cubeList.add(new ModelBox(Bow, 30, 11, -2.0F, -12.0F, 5.0F, 3, 2, 1, 0.0F, false));

        BowStrings = new RendererModel(this);
        BowStrings.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(BowStrings, 0.0873F, 0.0F, 0.0F);
        Bow.addChild(BowStrings);
        BowStrings.cubeList.add(new ModelBox(BowStrings, 30, 8, -4.0F, -10.25F, 6.0F, 3, 2, 1, 0.0F, false));
        BowStrings.cubeList.add(new ModelBox(BowStrings, 30, 14, 0.0F, -10.25F, 6.0F, 3, 2, 1, 0.0F, false));

        Hat = new RendererModel(this);
        Hat.setRotationPoint(0.5F, 11.0F, 0.0F);
        setRotationAngle(Hat, 0.0873F, -0.0873F, 0.1745F);
        Head.addChild(Hat);
        Hat.cubeList.add(new ModelBox(Hat, 0, 15, -6.0F, -19.0F, -3.0F, 6, 2, 6, 0.0F, false));
        Hat.cubeList.add(new ModelBox(Hat, 0, 23, -4.5748F, -20.9054F, -2.7795F, 4, 2, 4, 0.0F, false));
        Hat.cubeList.add(new ModelBox(Hat, 0, 29, -2.0748F, -22.1554F, -3.5295F, 2, 2, 2, 0.0F, false));

        RightArm = new RendererModel(this);
        RightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(RightArm, 0.0F, 0.0F, -0.2618F);
        Padoru.addChild(RightArm);
        RightArm.cubeList.add(new ModelBox(RightArm, 0, 51, -6.0F, -10.0F, -1.0F, 5, 2, 2, 0.0F, false));

        LeftArm = new RendererModel(this);
        LeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(LeftArm, 0.0F, 0.0F, 0.2618F);
        Padoru.addChild(LeftArm);
        LeftArm.cubeList.add(new ModelBox(LeftArm, 0, 45, 0.0F, -10.0F, -1.0F, 5, 2, 2, 0.0F, false));

        Skirt = new RendererModel(this);
        Skirt.setRotationPoint(0.0F, 0.0F, 0.0F);
        Padoru.addChild(Skirt);
        Skirt.cubeList.add(new ModelBox(Skirt, 38, 47, -4.0F, -11.0F, -3.0F, 7, 3, 6, 0.0F, false));
        Skirt.cubeList.add(new ModelBox(Skirt, 40, 13, -6.0F, -5.0F, -5.0F, 2, 3, 10, 0.0F, false));
        Skirt.cubeList.add(new ModelBox(Skirt, 40, 0, 3.0F, -5.0F, -5.0F, 2, 3, 10, 0.0F, false));
        Skirt.cubeList.add(new ModelBox(Skirt, 46, 26, -4.0F, -5.0F, 3.0F, 7, 3, 2, 0.0F, false));
        Skirt.cubeList.add(new ModelBox(Skirt, 46, 31, -4.0F, -5.0F, -5.0F, 7, 3, 2, 0.0F, false));
        Skirt.cubeList.add(new ModelBox(Skirt, 30, 36, -5.0F, -8.0F, -4.0F, 9, 3, 8, 0.0F, false));

        LeftFeet = new RendererModel(this);
        LeftFeet.setRotationPoint(0.0F, 0.0F, 0.0F);
        Padoru.addChild(LeftFeet);
        LeftFeet.cubeList.add(new ModelBox(LeftFeet, 0, 57, 0.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F, false));

        RightFeet = new RendererModel(this);
        RightFeet.setRotationPoint(0.0F, 0.0F, 0.0F);
        Padoru.addChild(RightFeet);
        RightFeet.cubeList.add(new ModelBox(RightFeet, 10, 57, -3.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F, false));
    }

    @Override
    public void render(PadoruEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        Padoru.render(scale);
    }

    public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(PadoruEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
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
}
