package com.mrbysco.padoru.datagen.client;

import com.mrbysco.padoru.PadoruMod;
import com.mrbysco.padoru.init.ModRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class ModSoundProvider extends SoundDefinitionsProvider {

	public ModSoundProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
		super(packOutput, PadoruMod.MOD_ID, existingFileHelper);
	}

	@Override
	public void registerSounds() {
		this.add(ModRegistry.PADORU_SPAWN, definition()
				.subtitle(modSubtitle(ModRegistry.PADORU_SPAWN.getId()))
				.with(sound(modLoc("ambient_full"))));
		this.add(ModRegistry.PADORU_AMBIENT, definition()
				.subtitle(modSubtitle(ModRegistry.PADORU_AMBIENT.getId()))
				.with(
						sound(modLoc("ambient_full")),
						sound(modLoc("ambient1")),
						sound(modLoc("ambient2")),
						sound(modLoc("ambient3")),
						sound(modLoc("padoru_padoru"))
				));
		this.add(ModRegistry.PADORU_DEATH, definition()
				.subtitle(modSubtitle(ModRegistry.PADORU_DEATH.getId()))
				.with(sound(modLoc("padoru_padoru"))));
		this.add(ModRegistry.PADORU_HURT, definition()
				.subtitle(modSubtitle(ModRegistry.PADORU_HURT.getId()))
				.with(sound(modLoc("padoru"))));
	}


	public String modSubtitle(ResourceLocation id) {
		return PadoruMod.MOD_ID + ".subtitle." + id.getPath();
	}

	public ResourceLocation modLoc(String name) {
		return new ResourceLocation(PadoruMod.MOD_ID, name);
	}
}
