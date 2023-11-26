package com.mrbysco.padoru.init;

import com.mrbysco.padoru.entity.Padoru;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;

public class ModSpawns {

	public static void registerEntityAttributes(SpawnPlacementRegisterEvent event) {
		event.register(ModRegistry.PADORU.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Padoru::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
	}

	public static void registerSpawnPlacements(EntityAttributeCreationEvent event) {
		event.put(ModRegistry.PADORU.get(), Padoru.registerAttributes().build());
	}
}
