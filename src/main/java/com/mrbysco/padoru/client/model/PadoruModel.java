package com.mrbysco.padoru.client.model;

import com.mrbysco.padoru.entity.Padoru;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class PadoruModel<T extends Padoru> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public PadoruModel(ModelPart part) {
		this.root = part.getChild("root");
		this.head = this.root.getChild("head");
		this.rightArm = this.root.getChild("right_arm");
		this.leftArm = this.root.getChild("left_arm");
		this.rightLeg = this.root.getChild("right_leg");
		this.leftLeg = this.root.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -7.0F, -4.25F, 7.0F, 7.0F, 8.0F)
				.texOffs(22, 0).addBox(-2.5F, -5.0F, 2.75F, 5.0F, 3.0F, 3.0F), PartPose.offset(-0.5F, -11.0F, 0.25F));

		PartDefinition bow = head.addOrReplaceChild("bow", CubeListBuilder.create().texOffs(30, 11).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 3.75F, 0.0873F, 0.0F, 0.0F));

		PartDefinition bow_strings = bow.addOrReplaceChild("bow_strings", CubeListBuilder.create().texOffs(30, 8).addBox(-3.5F, -1.0F, 0.5F, 3.0F, 2.0F, 1.0F)
				.texOffs(30, 14).addBox(0.5F, -1.0F, 0.5F, 3.0F, 2.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 1.75F, 0.5F, 0.0873F, 0.0F, 0.0F));

		PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 15).addBox(-2.7835F, -0.8131F, -1.897F, 6.0F, 2.0F, 6.0F)
				.texOffs(0, 23).addBox(-1.3583F, -2.7185F, -1.6765F, 4.0F, 2.0F, 4.0F)
				.texOffs(0, 29).addBox(1.1417F, -3.9685F, -2.4265F, 2.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(0.9554F, -7.0602F, -3.2478F, 0.0873F, 0.0873F, 0.1745F));

		PartDefinition right_arm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 51).addBox(-4.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(-3.9008F, -8.3392F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition left_arm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 45).addBox(-0.5F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F), PartPose.offsetAndRotation(2.8055F, -8.581F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition skirt = root.addOrReplaceChild("skirt", CubeListBuilder.create().texOffs(38, 47).addBox(-3.5F, -6.0F, -3.0F, 7.0F, 3.0F, 6.0F)
				.texOffs(40, 13).addBox(-5.5F, 0.0F, -5.0F, 2.0F, 3.0F, 10.0F)
				.texOffs(40, 0).addBox(3.5F, 0.0F, -5.0F, 2.0F, 3.0F, 10.0F)
				.texOffs(46, 26).addBox(-3.5F, 0.0F, 3.0F, 7.0F, 3.0F, 2.0F)
				.texOffs(46, 31).addBox(-3.5F, 0.0F, -5.0F, 7.0F, 3.0F, 2.0F)
				.texOffs(30, 36).addBox(-4.5F, -3.0F, -4.0F, 9.0F, 3.0F, 8.0F), PartPose.offset(-0.5F, -5.0F, 0.0F));

		PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 57).addBox(-1.0F, 0.5F, -1.0F, 2.0F, 5.0F, 2.0F), PartPose.offset(1.0F, -5.5F, 0.0F));

		PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(10, 57).addBox(-1.0F, 0.5F, -1.0F, 2.0F, 5.0F, 2.0F), PartPose.offset(-2.0F, -5.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.animateHeadLookTarget(headPitch, netHeadYaw);
		this.animateWalk(limbSwing, limbSwingAmount);
		this.animate(entity.spinAnimationState, PadoruAnimation.PADORU_SPIN, ageInTicks);
	}

	private void animateHeadLookTarget(float headPitch, float netHeadYaw) {
		this.head.xRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.yRot = headPitch * ((float) Math.PI / 180F);
	}

	private void animateWalk(float limbSwing, float limbSwingAmount) {
		this.leftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount * 0.5F;
		this.rightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount * 0.5F;

		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
		this.leftLeg.yRot = 0.0F;
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
		this.rightLeg.yRot = 0.0F;
	}

	public ModelPart root() {
		return this.root;
	}
}
