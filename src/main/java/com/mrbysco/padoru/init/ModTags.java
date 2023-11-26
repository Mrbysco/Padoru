package com.mrbysco.padoru.init;

import com.mrbysco.padoru.PadoruMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModTags {
	public static final TagKey<Biome> CAN_SPAWN_NERO_CLAUDIUS = TagKey.create(Registries.BIOME, new ResourceLocation(PadoruMod.MOD_ID, "can_spawn_nero_claudius"));
}
