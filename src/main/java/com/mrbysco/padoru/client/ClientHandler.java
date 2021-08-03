package com.mrbysco.padoru.client;

import com.mrbysco.padoru.Padoru;
import com.mrbysco.padoru.client.model.PadoruModel;
import com.mrbysco.padoru.client.render.PadoruRenderer;
import com.mrbysco.padoru.init.ModRegistry;
import com.mrbysco.padoru.item.CustomSpawnEggItem;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ClientHandler {
    public static final ModelLayerLocation PADORU = new ModelLayerLocation(new ResourceLocation(Padoru.MOD_ID, "padoru"), "padoru");

    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModRegistry.PADORU.get(), PadoruRenderer::new);
    }

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PADORU, PadoruModel::createBodyLayer);
    }

    public static void registerItemColors(final ColorHandlerEvent.Item event) {
        ItemColors colors = event.getItemColors();

        for(RegistryObject<Item> registryObject : ModRegistry.ITEMS.getEntries()) {
            if(registryObject.get() instanceof CustomSpawnEggItem spawnEgg) {
                colors.register((stack, tintIndex) -> spawnEgg.getColor(tintIndex), spawnEgg);
            }
        }
    }
}
