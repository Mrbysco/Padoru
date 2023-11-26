package com.mrbysco.padoru.init;

import com.mrbysco.padoru.entity.Padoru;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRegistry {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, com.mrbysco.padoru.Padoru.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, com.mrbysco.padoru.Padoru.MOD_ID);
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, com.mrbysco.padoru.Padoru.MOD_ID);

	public static final RegistryObject<EntityType<Padoru>> PADORU = ENTITY_TYPES.register("nero_claudius", () ->
			EntityType.Builder.<Padoru>of(Padoru::new, MobCategory.CREATURE)
					.sized(0.5F, 1.0F).clientTrackingRange(10).build("nero_claudius"));

	public static final RegistryObject<SoundEvent> PADORU_SPAWN = SOUND_EVENTS.register("padoru.spawn", () ->
			SoundEvent.createVariableRangeEvent(new ResourceLocation(com.mrbysco.padoru.Padoru.MOD_ID, "padoru.spawn")));
	public static final RegistryObject<SoundEvent> PADORU_AMBIENT = SOUND_EVENTS.register("padoru.ambient", () ->
			SoundEvent.createVariableRangeEvent(new ResourceLocation(com.mrbysco.padoru.Padoru.MOD_ID, "padoru.ambient")));
	public static final RegistryObject<SoundEvent> PADORU_DEATH = SOUND_EVENTS.register("padoru.death", () ->
			SoundEvent.createVariableRangeEvent(new ResourceLocation(com.mrbysco.padoru.Padoru.MOD_ID, "padoru.death")));
	public static final RegistryObject<SoundEvent> PADORU_HURT = SOUND_EVENTS.register("padoru.hurt", () ->
			SoundEvent.createVariableRangeEvent(new ResourceLocation(com.mrbysco.padoru.Padoru.MOD_ID, "padoru.hurt")));

	public static final RegistryObject<Item> PADORU_SPAWN_EGG = ITEMS.register("nero_claudius_spawn_egg", () ->
			new ForgeSpawnEggItem(PADORU, 12464433, 16640391, (new Item.Properties())));

}
