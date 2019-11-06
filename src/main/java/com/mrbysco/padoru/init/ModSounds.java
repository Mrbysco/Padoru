package com.mrbysco.padoru.init;

import com.google.common.collect.Lists;
import com.mrbysco.padoru.Padoru;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.List;

@EventBusSubscriber(modid = Padoru.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModSounds {
    private static List<SoundEvent> sounds = Lists.newArrayList();
    public static final SoundEvent PADORU_AMBIENT = createSound("padoru.ambient");
    public static final SoundEvent PADORU_DEATH = createSound("padoru.death");
    public static final SoundEvent PADORU_HURT = createSound("padoru.hurt");

    private static SoundEvent createSound(String name) {
        ResourceLocation resourceLocation = new ResourceLocation(Padoru.MOD_ID, name);
        SoundEvent sound = new SoundEvent(resourceLocation);
        sound.setRegistryName(resourceLocation);
        sounds.add(sound);
        return sound;
    }

    @SubscribeEvent
    public static void registerSound(RegistryEvent.Register<SoundEvent> event) {
        for (SoundEvent sound : sounds) {
            event.getRegistry().register(sound);
        }
    }
}
