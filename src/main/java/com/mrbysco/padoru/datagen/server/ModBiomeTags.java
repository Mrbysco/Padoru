package com.mrbysco.padoru.datagen.server;

import com.mrbysco.padoru.PadoruMod;
import com.mrbysco.padoru.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTags extends BiomeTagsProvider {

	public ModBiomeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, PadoruMod.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(ModTags.CAN_SPAWN_NERO_CLAUDIUS).addTags(Tags.Biomes.IS_COLD, Tags.Biomes.IS_SNOWY);
	}
}
