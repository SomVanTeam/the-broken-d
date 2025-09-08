package com.somevanguy.thebrokendildo;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, TheBrokenDildoMod.MOD_ID);
    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(TheBrokenDildoMod.MOD_ID, name);
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
    public static final Supplier<SoundEvent> Goon = registerSoundEvent("goon");
    public static final Supplier<SoundEvent> Kys = registerSoundEvent("kys");
    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
