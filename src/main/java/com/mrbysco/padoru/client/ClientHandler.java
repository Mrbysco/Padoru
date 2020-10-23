package com.mrbysco.padoru.client;

import com.mrbysco.padoru.client.render.PadoruRenderer;
import com.mrbysco.padoru.init.ModRegistry;
import com.mrbysco.padoru.item.CustomSpawnEggItem;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {
    public static void registerRenders(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModRegistry.PADORU.get(), PadoruRenderer::new);
    }

    public static void registerItemColors(final ColorHandlerEvent.Item event) {
        ItemColors colors = event.getItemColors();

        for(CustomSpawnEggItem spawneggitem : CustomSpawnEggItem.getEggs()) {
            colors.register((p_198141_1_, p_198141_2_) -> {
                return spawneggitem.getColor(p_198141_2_);
            }, spawneggitem);
        }
    }
}
