package com.mrbysco.padoru.init;

import com.mrbysco.padoru.entity.PadoruEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;

public class ModSpawns {

	public static void registerEntityAttributes(SpawnPlacementRegisterEvent event) {
		SpawnPlacements.register(ModRegistry.PADORU.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PadoruEntity::checkMobSpawnRules);
	}

	public static void registerSpawnPlacements(EntityAttributeCreationEvent event) {
		event.put(ModRegistry.PADORU.get(), PadoruEntity.registerAttributes().build());
	}
}
