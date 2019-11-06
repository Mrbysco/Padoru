package com.mrbysco.padoru.init;

import com.google.common.base.Preconditions;
import com.mrbysco.padoru.Padoru;
import com.mrbysco.padoru.init.EntityRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = Padoru.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {

    public static Item nero_claudius_spawn_egg;
    public static ArrayList<Item> ITEMS = new ArrayList<>();

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();

        nero_claudius_spawn_egg = registerItem(new SpawnEggItem(EntityRegistry.PADORU, 12464433, 16640391, itemBuilderWithGroup()), "nero_claudius_spawn_egg");

        registry.registerAll(ITEMS.toArray(new Item[0]));
    }

    public static <T extends Item> T registerItem(T item, String name)
    {
        ITEMS.add(item);

        item.setRegistryName(new ResourceLocation(Padoru.MOD_ID, name));
        Preconditions.checkNotNull(item, "registryName");
        return item;
    }

    private static Item.Properties itemBuilder()
    {
        return new Item.Properties();
    }

    private static Item.Properties itemBuilderWithGroup()
    {
        return itemBuilder().group(ItemGroup.MISC);
    }
}
