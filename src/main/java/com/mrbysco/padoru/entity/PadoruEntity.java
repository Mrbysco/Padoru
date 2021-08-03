package com.mrbysco.padoru.entity;

import com.mrbysco.padoru.init.ModRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.List;

public class PadoruEntity extends PathfinderMob {

    public PadoruEntity(EntityType<? extends PadoruEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public PadoruEntity(Level worldIn)
    {
        super(ModRegistry.PADORU.get(), worldIn);
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
    public SoundEvent getAmbientSound() { return ModRegistry.PADORU_AMBIENT.get(); }
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModRegistry.PADORU_HURT.get();
    }
    protected SoundEvent getDeathSound() {
        return ModRegistry.PADORU_DEATH.get();
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return PadoruEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.25F);
    }

    public Player getNearestPlayer(LevelAccessor worldIn) {
        AABB axisalignedbb = (new AABB(getX(), getY(), getZ(), getX() + 1, getY() + 1, getZ() + 1)).inflate(8);
        List<Player> list = worldIn.getEntitiesOfClass(Player.class, axisalignedbb);
        return !list.isEmpty() ? list.get(0) : null;
    }
}
