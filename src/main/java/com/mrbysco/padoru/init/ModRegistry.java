package com.mrbysco.padoru.init;

import com.mrbysco.padoru.PadoruMod;
import com.mrbysco.padoru.entity.Padoru;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRegistry {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PadoruMod.MOD_ID);
	public static final DeferredRegister.Entities ENTITIES = DeferredRegister.createEntities(PadoruMod.MOD_ID);
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, PadoruMod.MOD_ID);

	public static final Supplier<EntityType<Padoru>> PADORU = ENTITIES.registerEntityType("nero_claudius",
			Padoru::new,
			MobCategory.CREATURE,
			builder -> builder
					.sized(0.5F, 1.0F)
					.eyeHeight(0.9F)
					.clientTrackingRange(10)
	);

	public static final DeferredHolder<SoundEvent, SoundEvent> PADORU_SPAWN = SOUND_EVENTS.register("padoru.spawn", () ->
			SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(PadoruMod.MOD_ID, "padoru.spawn")));
	public static final DeferredHolder<SoundEvent, SoundEvent> PADORU_AMBIENT = SOUND_EVENTS.register("padoru.ambient", () ->
			SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(PadoruMod.MOD_ID, "padoru.ambient")));
	public static final DeferredHolder<SoundEvent, SoundEvent> PADORU_DEATH = SOUND_EVENTS.register("padoru.death", () ->
			SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(PadoruMod.MOD_ID, "padoru.death")));
	public static final DeferredHolder<SoundEvent, SoundEvent> PADORU_HURT = SOUND_EVENTS.register("padoru.hurt", () ->
			SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(PadoruMod.MOD_ID, "padoru.hurt")));

	public static final Supplier<Item> PADORU_SPAWN_EGG = ITEMS.registerItem("nero_claudius_spawn_egg", (properties) ->
			new SpawnEggItem(properties.spawnEgg(PADORU.get())));

}
