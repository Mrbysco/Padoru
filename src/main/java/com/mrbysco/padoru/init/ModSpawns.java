package com.mrbysco.padoru.init;

import com.mrbysco.padoru.PadoruConfig;
import com.mrbysco.padoru.entity.PadoruEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSpawns {
    public static void addSpawn() {
        for(Biome biome : ForgeRegistries.BIOMES) {
            if(BiomeDictionary.hasType(biome, Type.COLD) || BiomeDictionary.hasType(biome, Type.SNOWY)) {
                biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(ModRegistry.PADORU.get(), PadoruConfig.SPAWN.weight.get(), PadoruConfig.SPAWN.minGroup.get(), PadoruConfig.SPAWN.maxGroup.get()));
            }
        }
        EntitySpawnPlacementRegistry.register(ModRegistry.PADORU.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PadoruEntity::canSpawnOn);
    }

    public static void entityAttributes() {
        GlobalEntityTypeAttributes.put(ModRegistry.PADORU.get(), PadoruEntity.registerAttributes().create());
    }
}
