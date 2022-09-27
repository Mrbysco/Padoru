package com.mrbysco.padoru;

import com.mojang.logging.LogUtils;
import com.mrbysco.padoru.client.ClientHandler;
import com.mrbysco.padoru.init.ModRegistry;
import com.mrbysco.padoru.init.ModSpawns;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Padoru.MOD_ID)
public class Padoru {
	public static final String MOD_ID = "padoru";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Padoru() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModRegistry.ENTITY_TYPES.register(eventBus);
		ModRegistry.ITEMS.register(eventBus);
		ModRegistry.SOUND_EVENTS.register(eventBus);

		eventBus.addListener(ModSpawns::registerEntityAttributes);

		eventBus.addListener(this::setup);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHandler::registerEntityRenders);
			eventBus.addListener(ClientHandler::registerLayerDefinitions);
		});
	}

	private void setup(final FMLCommonSetupEvent event) {
		ModSpawns.entityAttributes();
	}
}
