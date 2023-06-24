package com.mrbysco.padoru;

import com.mrbysco.padoru.client.ClientHandler;
import com.mrbysco.padoru.init.ModRegistry;
import com.mrbysco.padoru.init.ModSpawns;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Padoru.MOD_ID)
public class Padoru {
	public static final String MOD_ID = "padoru";
	public static final Logger LOGGER = LogManager.getLogger();

	public Padoru() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModRegistry.ENTITY_TYPES.register(eventBus);
		ModRegistry.ITEMS.register(eventBus);
		ModRegistry.SOUND_EVENTS.register(eventBus);

		eventBus.addListener(ModSpawns::registerEntityAttributes);
		eventBus.addListener(ModSpawns::registerSpawnPlacements);
		eventBus.addListener(this::addTabContents);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHandler::registerEntityRenders);
			eventBus.addListener(ClientHandler::registerLayerDefinitions);
		});
	}

	private void addTabContents(final BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			event.accept(ModRegistry.PADORU_SPAWN_EGG);
		}
	}
}
