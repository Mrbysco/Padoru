package com.mrbysco.padoru.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import java.util.EnumSet;

public class FollowPlayerGoal extends Goal {
	protected final PadoruEntity padoru;
	private Player player;
	protected final LevelReader world;
	private final double followSpeed;
	private final PathNavigation navigator;
	private int timeToRecalcPath;
	private final float maxDist;
	private final float minDist;
	private float oldWaterCost;

	public FollowPlayerGoal(PadoruEntity padoruIn, double followSpeedIn, float minDistIn, float maxDistIn) {
		this.padoru = padoruIn;
		this.world = padoruIn.level;
		this.followSpeed = followSpeedIn;
		this.navigator = padoruIn.getNavigation();
		this.minDist = minDistIn;
		this.maxDist = maxDistIn;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		if (!(padoruIn.getNavigation() instanceof GroundPathNavigation) && !(padoruIn.getNavigation() instanceof FlyingPathNavigation)) {
			throw new IllegalArgumentException("Unsupported mob type for FollowplayerGoal");
		}
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean canUse() {
		Player nearestPlayer = this.padoru.getNearestPlayer(this.padoru.level);
		if (nearestPlayer == null) {
			return false;
		} else if (nearestPlayer.isSpectator()) {
			return false;
		} else {
			this.player = nearestPlayer;
			return true;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean canContinueToUse() {
		return !this.navigator.isDone() && this.padoru.distanceToSqr(this.player) > (double) (this.maxDist * this.maxDist);
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void start() {
		this.timeToRecalcPath = 0;
		this.oldWaterCost = this.padoru.getPathfindingMalus(BlockPathTypes.WATER);
		this.padoru.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by another one
	 */
	public void stop() {
		this.player = null;
		this.navigator.stop();
		this.padoru.setPathfindingMalus(BlockPathTypes.WATER, this.oldWaterCost);
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		this.padoru.getLookControl().setLookAt(this.player, 10.0F, (float) this.padoru.getMaxHeadXRot());
		if (--this.timeToRecalcPath <= 0) {
			this.timeToRecalcPath = 10;
			if (!this.navigator.moveTo(this.player, this.followSpeed)) {
				if (!this.padoru.isLeashed() && !this.padoru.isPassenger()) {
					if (!(this.padoru.distanceToSqr(this.player) < 144.0D)) {
						int i = Mth.floor(this.player.getX()) - 2;
						int j = Mth.floor(this.player.getZ()) - 2;
						int k = Mth.floor(this.player.getBoundingBox().minY);

						for (int l = 0; l <= 4; ++l) {
							for (int i1 = 0; i1 <= 4; ++i1) {
								if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.canTeleportToBlock(new BlockPos(i + l, k - 1, j + i1))) {
									this.padoru.moveTo((double) ((float) (i + l) + 0.5F), (double) k, (double) ((float) (j + i1) + 0.5F), this.padoru.getYRot(), this.padoru.getXRot());
									this.navigator.stop();
									return;
								}
							}
						}

					}
				}
			}
		}
	}

	protected boolean canTeleportToBlock(BlockPos pos) {
		BlockState blockstate = this.world.getBlockState(pos);
		return blockstate.isValidSpawn(this.world, pos, this.padoru.getType()) && this.world.isEmptyBlock(pos.above()) && this.world.isEmptyBlock(pos.above(2));
	}
}