package com.mrbysco.padoru.datagen;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.mrbysco.padoru.Padoru;
import com.mrbysco.padoru.init.ModRegistry;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.common.Tags.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers.AddSpawnsBiomeModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PadoruDataGen {
	public static final TagKey<Biome> CAN_SPAWN_NERO_CLAUDIUS = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Padoru.MOD_ID, "can_spawn_nero_claudius"));

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.builtinCopy());
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(event.includeServer(), new ModBiomeTags(generator, event.getExistingFileHelper()));


		if (event.includeServer()) {
			final HolderSet.Named<Biome> spawnBiomesTag = new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), CAN_SPAWN_NERO_CLAUDIUS);
			final BiomeModifier addSpawn = AddSpawnsBiomeModifier.singleSpawn(
					spawnBiomesTag,
					new SpawnerData(ModRegistry.PADORU.get(), 2, 1, 4));


			generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(
					generator, helper, Padoru.MOD_ID, ops, ForgeRegistries.Keys.BIOME_MODIFIERS,
					Map.of(
							new ResourceLocation(Padoru.MOD_ID, "add_padoru_spawn"), addSpawn
					)
			));
		}
	}

	private static class ModBiomeTags extends BiomeTagsProvider {

		public ModBiomeTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
			super(generator, Padoru.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags() {
			this.tag(CAN_SPAWN_NERO_CLAUDIUS).addTags(Biomes.IS_COLD, Biomes.IS_SNOWY);
		}
	}
}
