package com.mrbysco.padoru.init;

import com.mrbysco.padoru.entity.PadoruEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

public class ModSpawns {

	public static void entityAttributes() {
		SpawnPlacements.register(ModRegistry.PADORU.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PadoruEntity::checkMobSpawnRules);
	}

	public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(ModRegistry.PADORU.get(), PadoruEntity.registerAttributes().build());
	}
}
