package com.mrbysco.padoru.entity;

import com.mrbysco.padoru.init.EntityRegistry;
import com.mrbysco.padoru.init.ModSounds;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PadoruEntity extends CreatureEntity {

    public PadoruEntity(EntityType<? extends PadoruEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public PadoruEntity(World worldIn)
    {
        super(EntityRegistry.PADORU, worldIn);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowPlayerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setCallsForHelp());
    }

    protected float getSoundVolume() {
        return super.getSoundVolume();
    }

    @Override
    protected float getSoundPitch() {
        return 1.0F;
    }

    @Nullable
    public SoundEvent getAmbientSound() { return ModSounds.PADORU_AMBIENT; }
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.PADORU_HURT;
    }
    protected SoundEvent getDeathSound() {
        return ModSounds.PADORU_DEATH;
    }

    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)0.25F);
    }

    public PlayerEntity getNearestPlayer() {
        AxisAlignedBB axisalignedbb = (new AxisAlignedBB(posX, posY, posZ, posX + 1, posY + 1, posZ + 1)).grow(8);
        List<PlayerEntity> list = world.getEntitiesWithinAABB(PlayerEntity.class, axisalignedbb);
        return !list.isEmpty() ? list.get(0) : null;
    }
}
