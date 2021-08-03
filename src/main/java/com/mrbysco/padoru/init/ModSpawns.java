package com.mrbysco.padoru.init;

import com.mrbysco.padoru.Padoru;
import com.mrbysco.padoru.PadoruConfig;
import com.mrbysco.padoru.entity.PadoruEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Padoru.MOD_ID)
public class ModSpawns {
    @SubscribeEvent(priority =  EventPriority.HIGH)
    public static void addSpawn(BiomeLoadingEvent event) {
        RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        if(BiomeDictionary.hasType(biomeKey, Type.COLD) || BiomeDictionary.hasType(biomeKey, Type.SNOWY)) {
            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(ModRegistry.PADORU.get(), PadoruConfig.SPAWN.weight.get(), PadoruConfig.SPAWN.minGroup.get(), PadoruConfig.SPAWN.maxGroup.get()));
        }
    }

    public static void entityAttributes() {
        EntitySpawnPlacementRegistry.register(ModRegistry.PADORU.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PadoruEntity::canSpawnOn);
    }

    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModRegistry.PADORU.get(), PadoruEntity.registerAttributes().create());
    }
}
