package com.mrbysco.padoru.datagen.client;

import com.mrbysco.padoru.Padoru;
import com.mrbysco.padoru.init.ModRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.minecraftforge.registries.RegistryObject;

public class ModLanguageProvider extends LanguageProvider {

	public ModLanguageProvider(PackOutput packOutput) {
		super(packOutput, Padoru.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		addItem(ModRegistry.PADORU_SPAWN_EGG, "Nero Claudius Spawn Egg");
		addEntityType(ModRegistry.PADORU, "Nero Claudius");

		addSubtitle(ModRegistry.PADORU_SPAWN, "Padoru emerges");
		addSubtitle(ModRegistry.PADORU_AMBIENT, "Padoru intensifies");
		addSubtitle(ModRegistry.PADORU_DEATH, "Padoru dies");
		addSubtitle(ModRegistry.PADORU_HURT, "Padoru hurts");
	}

	/**
	 * Add a subtitle to a sound event
	 *
	 * @param sound The sound event
	 * @param text  The subtitle text
	 */
	public void addSubtitle(RegistryObject<SoundEvent> sound, String text) {
		this.addSubtitle(sound.get(), text);
	}

	/**
	 * Add a subtitle to a sound event
	 *
	 * @param sound The sound event registry object
	 * @param text  The subtitle text
	 */
	public void addSubtitle(SoundEvent sound, String text) {
		String path = Padoru.MOD_ID + ".subtitle." + sound.getLocation().getPath();
		this.add(path, text);
	}
}
