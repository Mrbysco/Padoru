package com.mrbysco.padoru.init;

import com.mrbysco.padoru.Padoru;
import com.mrbysco.padoru.entity.PadoruEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRegistry {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Padoru.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Padoru.MOD_ID);
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Padoru.MOD_ID);

	public static final RegistryObject<EntityType<PadoruEntity>> PADORU = ENTITY_TYPES.register("nero_claudius", () -> register("nero_claudius", EntityType.Builder.<PadoruEntity>of(PadoruEntity::new, MobCategory.CREATURE).sized(0.5F, 1.0F)));

	public static final RegistryObject<SoundEvent> PADORU_AMBIENT = SOUND_EVENTS.register("padoru.ambient", () -> new SoundEvent(new ResourceLocation(Padoru.MOD_ID, "padoru.ambient")));
	public static final RegistryObject<SoundEvent> PADORU_DEATH = SOUND_EVENTS.register("padoru.death", () -> new SoundEvent(new ResourceLocation(Padoru.MOD_ID, "padoru.death")));
	public static final RegistryObject<SoundEvent> PADORU_HURT = SOUND_EVENTS.register("padoru.hurt", () -> new SoundEvent(new ResourceLocation(Padoru.MOD_ID, "padoru.hurt")));

	public static final RegistryObject<Item> PADORU_SPAWN_EGG = ITEMS.register("nero_claudius_spawn_egg", () -> new ForgeSpawnEggItem(() -> PADORU.get(), 12464433, 16640391, itemBuilder()));

	public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder, boolean sendVelocityUpdates) {
		return builder.setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(sendVelocityUpdates).build(id);
	}

	public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
		return register(id, builder, true);
	}

	private static Item.Properties itemBuilder() {
		return new Item.Properties().tab(CreativeModeTab.TAB_MISC);
	}
}
