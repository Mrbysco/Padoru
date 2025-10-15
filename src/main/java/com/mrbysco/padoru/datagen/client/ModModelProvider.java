package com.mrbysco.padoru.datagen.client;

import com.mrbysco.padoru.PadoruMod;
import com.mrbysco.padoru.init.ModRegistry;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {
	public ModModelProvider(PackOutput output) {
		super(output, PadoruMod.MOD_ID);
	}

	@Override
	protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
		itemModels.generateFlatItem(ModRegistry.PADORU_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
	}
}
