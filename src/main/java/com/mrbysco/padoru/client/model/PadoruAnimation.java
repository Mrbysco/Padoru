package com.mrbysco.padoru.client.model;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class PadoruAnimation {
	public static final AnimationDefinition PADORU_SPIN = AnimationDefinition.Builder.withLength(1f).looping()
			.addAnimation("root",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 180f, 0f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 360f, 0f),
									AnimationChannel.Interpolations.LINEAR))).build();
}
