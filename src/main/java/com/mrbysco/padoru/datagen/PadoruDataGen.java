package com.mrbysco.padoru.datagen;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.mrbysco.padoru.Padoru;
import com.mrbysco.padoru.datagen.client.ModLanguageProvider;
import com.mrbysco.padoru.datagen.client.ModSoundProvider;
import com.mrbysco.padoru.datagen.server.ModBiomeTags;
import com.mrbysco.padoru.datagen.server.ModLootProvider;
import com.mrbysco.padoru.init.ModRegistry;
import com.mrbysco.padoru.init.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PadoruDataGen {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		HolderLookup.Provider provider = getProvider();
		final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, provider);
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
					packOutput, CompletableFuture.supplyAsync(PadoruDataGen::getProvider), Set.of(Padoru.MOD_ID)));

			generator.addProvider(event.includeServer(), new ModLootProvider(packOutput));
			generator.addProvider(event.includeServer(), new ModBiomeTags(packOutput, lookupProvider, event.getExistingFileHelper()));
		}
		if (event.includeClient()) {
			generator.addProvider(event.includeClient(), new ModLanguageProvider(packOutput));
			generator.addProvider(event.includeClient(), new ModSoundProvider(packOutput, helper));
		}
	}

	private static HolderLookup.Provider getProvider() {
		final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
		registryBuilder.add(ForgeRegistries.Keys.BIOME_MODIFIERS, context -> {
			final HolderGetter<Biome> biomeHolderGetter = context.lookup(Registries.BIOME);
			final BiomeModifier addSpawn = ForgeBiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(
					biomeHolderGetter.getOrThrow(ModTags.CAN_SPAWN_NERO_CLAUDIUS),
					new SpawnerData(ModRegistry.PADORU.get(), 2, 1, 4));

			context.register(createKey("add_padoru_spawn"), addSpawn);
		});
		// We need the BIOME registry to be present so we can use a biome tag, doesn't matter that it's empty
		registryBuilder.add(Registries.BIOME, $ -> {
		});
		RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
		return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup());
	}

	private static ResourceKey<BiomeModifier> createKey(String name) {
		return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Padoru.MOD_ID, name));
	}
}
