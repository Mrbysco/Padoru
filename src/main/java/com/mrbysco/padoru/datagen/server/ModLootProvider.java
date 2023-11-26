package com.mrbysco.padoru.datagen.server;

import com.mrbysco.padoru.init.ModRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class ModLootProvider extends LootTableProvider {
	public ModLootProvider(PackOutput packOutput) {
		super(packOutput, Set.of(), List.of(
				new SubProviderEntry(HashireSoriYo::new, LootContextParamSets.ENTITY)
		));
	}

	@Override
	protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
		map.forEach((name, table) -> table.validate(validationtracker));
	}

	private static class HashireSoriYo extends EntityLootSubProvider {
		protected HashireSoriYo() {
			super(FeatureFlags.REGISTRY.allFlags());
		}

		@Override
		public void generate() {
			this.add(ModRegistry.PADORU.get(), LootTable.lootTable());
		}

		@Override
		protected Stream<EntityType<?>> getKnownEntityTypes() {
			return ModRegistry.ENTITY_TYPES.getEntries().stream().map(RegistryObject::get);
		}
	}
}
