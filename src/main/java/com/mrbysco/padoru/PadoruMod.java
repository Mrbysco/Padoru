package com.mrbysco.padoru;

import com.mrbysco.padoru.client.ClientHandler;
import com.mrbysco.padoru.init.ModRegistry;
import com.mrbysco.padoru.init.ModSpawns;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(PadoruMod.MOD_ID)
public class PadoruMod {
	public static final String MOD_ID = "padoru";
	public static final Logger LOGGER = LogManager.getLogger();

	public PadoruMod(IEventBus eventBus) {
		ModRegistry.ENTITY_TYPES.register(eventBus);
		ModRegistry.ITEMS.register(eventBus);
		ModRegistry.SOUND_EVENTS.register(eventBus);

		eventBus.addListener(ModSpawns::registerEntityAttributes);
		eventBus.addListener(ModSpawns::registerSpawnPlacements);
		eventBus.addListener(this::addTabContents);

		if (FMLEnvironment.dist.isClient()) {
			eventBus.addListener(ClientHandler::registerEntityRenders);
			eventBus.addListener(ClientHandler::registerLayerDefinitions);
		}
	}

	private void addTabContents(final BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			event.accept(ModRegistry.PADORU_SPAWN_EGG.get());
		}
	}
}
