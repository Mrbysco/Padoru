package com.mrbysco.padoru.init;

import com.mrbysco.padoru.Padoru;
import com.mrbysco.padoru.PadoruConfig;
import com.mrbysco.padoru.entity.PadoruEntity;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
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
        if(event.getName() != null) {
            ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
            if(BiomeDictionary.hasType(biomeKey, Type.COLD) || BiomeDictionary.hasType(biomeKey, Type.SNOWY)) {
                event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(ModRegistry.PADORU.get(), PadoruConfig.SPAWN.weight.get(), PadoruConfig.SPAWN.minGroup.get(), PadoruConfig.SPAWN.maxGroup.get()));
            }
        }
    }

    public static void entityAttributes() {
        SpawnPlacements.register(ModRegistry.PADORU.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PadoruEntity::checkMobSpawnRules);
    }

    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModRegistry.PADORU.get(), PadoruEntity.registerAttributes().build());
    }
}
