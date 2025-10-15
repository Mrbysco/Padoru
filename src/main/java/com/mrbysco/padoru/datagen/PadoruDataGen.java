package com.mrbysco.padoru.datagen;

import com.mrbysco.padoru.PadoruMod;
import com.mrbysco.padoru.datagen.client.ModLanguageProvider;
import com.mrbysco.padoru.datagen.client.ModModelProvider;
import com.mrbysco.padoru.datagen.client.ModSoundProvider;
import com.mrbysco.padoru.datagen.server.ModBiomeTags;
import com.mrbysco.padoru.datagen.server.ModLootProvider;
import com.mrbysco.padoru.init.ModRegistry;
import com.mrbysco.padoru.init.ModTags;
import net.minecraft.core.Cloner;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.Weighted;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class PadoruDataGen {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(true, new DatapackBuiltinEntriesProvider(
				packOutput, CompletableFuture.supplyAsync(PadoruDataGen::getProvider), Set.of(PadoruMod.MOD_ID)));

		generator.addProvider(true, new ModLootProvider(packOutput, lookupProvider));
		generator.addProvider(true, new ModBiomeTags(packOutput, lookupProvider));

		generator.addProvider(true, new ModLanguageProvider(packOutput));
		generator.addProvider(true, new ModModelProvider(packOutput));
		generator.addProvider(true, new ModSoundProvider(packOutput));

	}

	private static RegistrySetBuilder.PatchedRegistries getProvider() {
		final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
		registryBuilder.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, context -> {
			final HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);
			final BiomeModifier addSpawn = BiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(
					biomeHolderGetter.getOrThrow(ModTags.CAN_SPAWN_NERO_CLAUDIUS),
					new Weighted<>(new MobSpawnSettings.SpawnerData(ModRegistry.PADORU.get(), 1, 4), 2));

			context.register(createKey("add_padoru_spawn"), addSpawn);
		});
		// We need the BIOME registry to be present so we can use a biome tag, doesn't matter that it's empty
		registryBuilder.add(Registries.BIOME, $ -> {
		});
		RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
		Cloner.Factory cloner$factory = new Cloner.Factory();
		net.neoforged.neoforge.registries.DataPackRegistriesHooks.getDataPackRegistriesWithDimensions().forEach(data -> data.runWithArguments(cloner$factory::addCodec));
		return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup(), cloner$factory);
	}

	private static ResourceKey<BiomeModifier> createKey(String name) {
		return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(PadoruMod.MOD_ID, name));
	}
}
