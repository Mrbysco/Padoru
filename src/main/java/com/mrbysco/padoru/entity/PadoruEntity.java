package com.mrbysco.padoru.entity;

import com.mrbysco.padoru.init.ModRegistry;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
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
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PadoruEntity extends CreatureEntity {

    public PadoruEntity(EntityType<? extends PadoruEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public PadoruEntity(World worldIn)
    {
        super(ModRegistry.PADORU.get(), worldIn);
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
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    protected float getSoundVolume() {
        return super.getSoundVolume();
    }

    @Override
    protected float getVoicePitch() {
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

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return PadoruEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.25F);
    }

    public PlayerEntity getNearestPlayer(IWorld worldIn) {
        AxisAlignedBB axisalignedbb = (new AxisAlignedBB(getX(), getY(), getZ(), getX() + 1, getY() + 1, getZ() + 1)).inflate(8);
        List<PlayerEntity> list = worldIn.getEntitiesOfClass(PlayerEntity.class, axisalignedbb);
        return !list.isEmpty() ? list.get(0) : null;
    }
}
