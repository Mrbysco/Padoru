package com.mrbysco.padoru.init;

import com.mrbysco.padoru.PadoruMod;
import com.mrbysco.padoru.entity.Padoru;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRegistry {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PadoruMod.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, PadoruMod.MOD_ID);
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, PadoruMod.MOD_ID);

	public static final Supplier<EntityType<Padoru>> PADORU = ENTITY_TYPES.register("nero_claudius", () ->
			EntityType.Builder.<Padoru>of(Padoru::new, MobCategory.CREATURE)
					.sized(0.5F, 1.0F).clientTrackingRange(10).build("nero_claudius"));

	public static final DeferredHolder<SoundEvent, SoundEvent> PADORU_SPAWN = SOUND_EVENTS.register("padoru.spawn", () ->
			SoundEvent.createVariableRangeEvent(new ResourceLocation(PadoruMod.MOD_ID, "padoru.spawn")));
	public static final DeferredHolder<SoundEvent, SoundEvent> PADORU_AMBIENT = SOUND_EVENTS.register("padoru.ambient", () ->
			SoundEvent.createVariableRangeEvent(new ResourceLocation(PadoruMod.MOD_ID, "padoru.ambient")));
	public static final DeferredHolder<SoundEvent, SoundEvent> PADORU_DEATH = SOUND_EVENTS.register("padoru.death", () ->
			SoundEvent.createVariableRangeEvent(new ResourceLocation(PadoruMod.MOD_ID, "padoru.death")));
	public static final DeferredHolder<SoundEvent, SoundEvent> PADORU_HURT = SOUND_EVENTS.register("padoru.hurt", () ->
			SoundEvent.createVariableRangeEvent(new ResourceLocation(PadoruMod.MOD_ID, "padoru.hurt")));

	public static final Supplier<Item> PADORU_SPAWN_EGG = ITEMS.register("nero_claudius_spawn_egg", () ->
			new DeferredSpawnEggItem(PADORU, 12464433, 16640391, (new Item.Properties())));

}
