package com.mrbysco.padoru.init;

import com.mrbysco.padoru.Padoru;
import com.mrbysco.padoru.entity.PadoruEntity;
import com.mrbysco.padoru.item.CustomSpawnEggItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Padoru.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Padoru.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Padoru.MOD_ID);

    public static final RegistryObject<EntityType<PadoruEntity>> PADORU = ENTITIES.register("nero_claudius", () -> register("nero_claudius", EntityType.Builder.<PadoruEntity>create(PadoruEntity::new, EntityClassification.CREATURE).size(0.5F, 1.0F)));

    public static final RegistryObject<SoundEvent> PADORU_AMBIENT = SOUND_EVENTS.register("padoru.ambient", () -> new SoundEvent(new ResourceLocation(Padoru.MOD_ID, "padoru.ambient")));
    public static final RegistryObject<SoundEvent> PADORU_DEATH = SOUND_EVENTS.register("padoru.death", () -> new SoundEvent(new ResourceLocation(Padoru.MOD_ID, "padoru.death")));
    public static final RegistryObject<SoundEvent> PADORU_HURT = SOUND_EVENTS.register("padoru.hurt", () -> new SoundEvent(new ResourceLocation(Padoru.MOD_ID, "padoru.hurt")));

    public static final RegistryObject<Item> PADORU_SPAWN_EGG = ITEMS.register("nero_claudius_spawn_egg" , () -> new CustomSpawnEggItem(() -> PADORU.get(), 12464433, 16640391, itemBuilder()));

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder, boolean sendVelocityUpdates) {
        return builder.setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(sendVelocityUpdates).build(id);
    }

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
        return register(id, builder, true);
    }

    private static Item.Properties itemBuilder() {
        return new Item.Properties().group(ItemGroup.MISC);
    }
}
