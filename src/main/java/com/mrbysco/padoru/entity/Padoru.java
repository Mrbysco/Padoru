package com.mrbysco.padoru.entity;

import com.mrbysco.padoru.init.ModRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Unit;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Padoru extends PathfinderMob {

	public AnimationState spinAnimationState = new AnimationState();

	public Padoru(EntityType<? extends Padoru> type, Level level) {
		super(type, level);
	}

	public Padoru(Level level) {
		super(ModRegistry.PADORU.get(), level);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(6, new FollowPlayerGoal(this, 1.0D, 10.0F, 2.0F));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
	}

	protected float getSoundVolume() {
		return super.getSoundVolume();
	}

	@Override
	public float getVoicePitch() {
		return 1.0F;
	}

	@Nullable
	public SoundEvent getAmbientSound() {
		if (getPose() == Pose.EMERGING) return null;
		return ModRegistry.PADORU_AMBIENT.get();
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModRegistry.PADORU_HURT.get();
	}

	protected SoundEvent getDeathSound() {
		return ModRegistry.PADORU_DEATH.get();
	}

	public static AttributeSupplier.Builder registerAttributes() {
		return Padoru.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 12.0D)
				.add(Attributes.ATTACK_DAMAGE, 2.0D)
				.add(Attributes.MOVEMENT_SPEED, (double) 0.25F);
	}

	public Player getNearestPlayer() {
		AABB aabb = (new AABB(getX(), getY(), getZ(), getX() + 1, getY() + 1, getZ() + 1)).inflate(8);
		List<Player> list = this.level().getEntitiesOfClass(Player.class, aabb);
		return !list.isEmpty() ? list.get(0) : null;
	}

	public void onSyncedDataUpdated(EntityDataAccessor<?> dataAccessor) {
		if (DATA_POSE.equals(dataAccessor)) {
			if (this.getPose() == Pose.EMERGING) {
				this.spinAnimationState.start(this.tickCount);
			}
		}

		super.onSyncedDataUpdated(dataAccessor);
	}

	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return new ClientboundAddEntityPacket(this, this.hasPose(Pose.EMERGING) ? 1 : 0);
	}

	public void recreateFromPacket(ClientboundAddEntityPacket clientboundAddEntityPacket) {
		super.recreateFromPacket(clientboundAddEntityPacket);
		if (clientboundAddEntityPacket.getData() == 1) {
			this.setPose(Pose.EMERGING);
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (this.spinAnimationState.isStarted() && this.spinAnimationState.getAccumulatedTime() > 9000L) {
			this.spinAnimationState.stop();
			if (getPose() == Pose.EMERGING) {
				this.setPose(Pose.STANDING);
			}
		}
	}

	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance,
										MobSpawnType spawnType, @Nullable SpawnGroupData groupData, @Nullable CompoundTag tag) {
		this.setPose(Pose.EMERGING);
		this.getBrain().setMemoryWithExpiry(MemoryModuleType.IS_EMERGING, Unit.INSTANCE, (long) 100.0F);
		this.playSound(ModRegistry.PADORU_SPAWN.get(), 1.0F, 1.0F);

		return super.finalizeSpawn(levelAccessor, difficultyInstance, spawnType, groupData, tag);
	}
}
