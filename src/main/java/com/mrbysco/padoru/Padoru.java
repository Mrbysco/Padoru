package com.mrbysco.padoru;

import com.mrbysco.padoru.client.ClientHandler;
import com.mrbysco.padoru.init.ModRegistry;
import com.mrbysco.padoru.init.ModSpawns;
import com.mrbysco.padoru.item.CustomSpawnEggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Padoru.MOD_ID)
public class Padoru {
    public static final String MOD_ID = "padoru";
    public static final Logger LOGGER = LogManager.getLogger();

    public Padoru() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, PadoruConfig.spawnSpec);
        eventBus.register(PadoruConfig.class);

        ModRegistry.ENTITIES.register(eventBus);
        ModRegistry.ITEMS.register(eventBus);
        ModRegistry.SOUND_EVENTS.register(eventBus);

        eventBus.addListener(ModSpawns::registerEntityAttributes);

        eventBus.addListener(this::setup);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            eventBus.addListener(ClientHandler::registerEntityRenders);
            eventBus.addListener(ClientHandler::registerLayerDefinitions);
            eventBus.addListener(ClientHandler::registerItemColors);
        });
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModSpawns.entityAttributes();

        event.enqueueWork(() -> {
            for(RegistryObject<Item> registryObject : ModRegistry.ITEMS.getEntries()) {
                if(registryObject.get() instanceof CustomSpawnEggItem spawnEgg) {
                    SpawnEggItem.BY_ID.put(spawnEgg.entityType.get(), spawnEgg);
                }
            }
        });
    }
}
