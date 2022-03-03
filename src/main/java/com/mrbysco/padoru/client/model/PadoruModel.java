package com.mrbysco.padoru.client.model;

import com.mrbysco.padoru.entity.PadoruEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class PadoruModel<T extends PadoruEntity> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart skirt;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public PadoruModel(ModelPart part) {
		this.root = part;
		this.head = part.getChild("head");
		this.rightArm = part.getChild("right_arm");
		this.leftArm = part.getChild("left_arm");
		this.skirt = part.getChild("skirt");
		this.rightLeg = part.getChild("right_leg");
		this.leftLeg = part.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

//        Padoru = new ModelPart(this);
//        Padoru.setPos(0.0F, 24.0F, 0.0F);

		PartDefinition headDefinition = partDefinition.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(0, 0).addBox(-3.5F, -7.0F, -4.0F, 7.0F, 7.0F, 8.0F)
						.texOffs(22, 0).addBox(-2.5F, -5.0F, 3.0F, 5.0F, 3.0F, 3.0F),
				PartPose.offset(-0.5F, -11.0F, 0.0F));


		PartDefinition bow = headDefinition.addOrReplaceChild("bow", CubeListBuilder.create()
						.texOffs(30, 11).addBox(-2.0F, -12.0F, 5.0F, 3.0F, 2.0F, 1.0F),
				PartPose.offsetAndRotation(0.5F, 11.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		bow.addOrReplaceChild("bow_strings", CubeListBuilder.create()
						.texOffs(30, 8).addBox(-4.0F, -10.25F, 6.0F, 3.0F, 2.0F, 1.0F)
						.texOffs(30, 14).addBox(0.0F, -10.25F, 6.0F, 3.0F, 2.0F, 1.0F),
				PartPose.rotation(0.0873F, 0.0F, 0.0F));

		headDefinition.addOrReplaceChild("hat", CubeListBuilder.create()
						.texOffs(0, 15).addBox(-6.0F, -19.0F, -3.0F, 6.0F, 2.0F, 6.0F)
						.texOffs(0, 23).addBox(-4.5748F, -20.9054F, -2.7795F, 4.0F, 2.0F, 4.0F)
						.texOffs(0, 29).addBox(-2.0748F, -22.1554F, -3.5295F, 2.0F, 2.0F, 2.0F),
				PartPose.offsetAndRotation(0.5F, 11.0F, 0.0F, 0.0873F, -0.0873F, 0.1745F));

		partDefinition.addOrReplaceChild("right_arm", CubeListBuilder.create()
						.texOffs(0, 51).addBox(-6.0F, -10.0F, -1.0F, 5.0F, 2.0F, 2.0F),
				PartPose.rotation(0.0F, 0.0F, -0.2618F));

		partDefinition.addOrReplaceChild("left_arm", CubeListBuilder.create()
						.texOffs(0, 45).addBox(0.0F, -10.0F, -1.0F, 5.0F, 2.0F, 2.0F),
				PartPose.rotation(0.0F, 0.0F, 0.2618F));

		partDefinition.addOrReplaceChild("skirt", CubeListBuilder.create()
						.texOffs(38, 47).addBox(-3.5F, -6.0F, -3.0F, 7.0F, 3.0F, 6.0F)
						.texOffs(40, 13).addBox(-5.5F, 0.0F, -5.0F, 2.0F, 3.0F, 10.0F)
						.texOffs(40, 0).addBox(3.5F, 0.0F, -5.0F, 2.0F, 3.0F, 10.0F)
						.texOffs(46, 26).addBox(-3.5F, 0.0F, 3.0F, 7.0F, 3.0F, 2.0F)
						.texOffs(46, 31).addBox(-3.5F, 0.0F, -5.0F, 7.0F, 3.0F, 2.0F)
						.texOffs(30, 36).addBox(-4.5F, -3.0F, -4.0F, 9.0F, 3.0F, 8.0F),
				PartPose.offset(-0.5F, -5.0F, 0.0F));


		partDefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
						.texOffs(10, 57).addBox(-3.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F),
				PartPose.ZERO);

		partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create()
						.texOffs(0, 57).addBox(0.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F),
				PartPose.ZERO);

		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.head.xRot = headPitch * ((float) Math.PI / 180F);
		this.head.zRot = 0.0F;

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
