package com.mrbysco.padoru.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;

import java.util.EnumSet;

public class FollowPlayerGoal extends Goal {
    protected final PadoruEntity padoru;
    private PlayerEntity player;
    protected final IWorldReader world;
    private final double followSpeed;
    private final PathNavigator navigator;
    private int timeToRecalcPath;
    private final float maxDist;
    private final float minDist;
    private float oldWaterCost;

    public FollowPlayerGoal(PadoruEntity padoruIn, double followSpeedIn, float minDistIn, float maxDistIn) {
        this.padoru = padoruIn;
        this.world = padoruIn.world;
        this.followSpeed = followSpeedIn;
        this.navigator = padoruIn.getNavigator();
        this.minDist = minDistIn;
        this.maxDist = maxDistIn;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        if (!(padoruIn.getNavigator() instanceof GroundPathNavigator) && !(padoruIn.getNavigator() instanceof FlyingPathNavigator)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowplayerGoal");
        }
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        PlayerEntity nearestPlayer = this.padoru.getNearestPlayer();
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
    public boolean shouldContinueExecuting() {
        return !this.navigator.noPath() && this.padoru.getDistanceSq(this.player) > (double) (this.maxDist * this.maxDist);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.padoru.getPathPriority(PathNodeType.WATER);
        this.padoru.setPathPriority(PathNodeType.WATER, 0.0F);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        this.player = null;
        this.navigator.clearPath();
        this.padoru.setPathPriority(PathNodeType.WATER, this.oldWaterCost);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        this.padoru.getLookController().setLookPositionWithEntity(this.player, 10.0F, (float) this.padoru.getVerticalFaceSpeed());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!this.navigator.tryMoveToEntityLiving(this.player, this.followSpeed)) {
                if (!this.padoru.getLeashed() && !this.padoru.isPassenger()) {
                    if (!(this.padoru.getDistanceSq(this.player) < 144.0D)) {
                        int i = MathHelper.floor(this.player.posX) - 2;
                        int j = MathHelper.floor(this.player.posZ) - 2;
                        int k = MathHelper.floor(this.player.getBoundingBox().minY);

                        for (int l = 0; l <= 4; ++l) {
                            for (int i1 = 0; i1 <= 4; ++i1) {
                                if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.canTeleportToBlock(new BlockPos(i + l, k - 1, j + i1))) {
                                    this.padoru.setLocationAndAngles((double) ((float) (i + l) + 0.5F), (double) k, (double) ((float) (j + i1) + 0.5F), this.padoru.rotationYaw, this.padoru.rotationPitch);
                                    this.navigator.clearPath();
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
        return blockstate.canEntitySpawn(this.world, pos, this.padoru.getType()) && this.world.isAirBlock(pos.up()) && this.world.isAirBlock(pos.up(2));
    }
}