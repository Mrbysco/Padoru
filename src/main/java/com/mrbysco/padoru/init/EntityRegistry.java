package com.mrbysco.padoru.init;

import com.google.common.base.Preconditions;
import com.mrbysco.padoru.Padoru;
import com.mrbysco.padoru.PadoruConfig;
import com.mrbysco.padoru.entity.PadoruEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Padoru.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegistry {
    public static final EntityType<PadoruEntity> PADORU = register("nero_claudius", EntityType.Builder.<PadoruEntity>create(PadoruEntity::new, EntityClassification.CREATURE)
            .size(0.5F, 1.0F)
            .setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true));

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
        EntityType<T> entityType = builder.build("");
        ResourceLocation name = new ResourceLocation(Padoru.MOD_ID, id);

        entityType.setRegistryName(name);

        return entityType;
    }

    public static void register(EntityType<?> entity, String name, RegistryEvent.Register<EntityType<?>> event) {
        Preconditions.checkNotNull(entity, "registryName");
        event.getRegistry().register(entity);
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event)
    {
        Padoru.LOGGER.debug("Registering Padoru");
        register(PADORU, "nero_claudius", event);
    }

    public static void addSpawn() {
        for(Biome biome : ForgeRegistries.BIOMES) {
            if(BiomeDictionary.hasType(biome, Type.COLD) || BiomeDictionary.hasType(biome, Type.SNOWY)) {
                biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(PADORU, PadoruConfig.SPAWN.weight.get(), PadoruConfig.SPAWN.minGroup.get(), PadoruConfig.SPAWN.maxGroup.get()));
            }
        }
        EntitySpawnPlacementRegistry.register(PADORU, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PadoruEntity::func_223315_a);
    }
}
