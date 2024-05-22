package com.andersmmg.lockandblock.sounds;

import com.andersmmg.lockandblock.LockAndBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent BEEP_SUCCESS = registerSoundEvent("beep_success");
    public static final SoundEvent BEEP_ERROR = registerSoundEvent("beep_error");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = LockAndBlock.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        LockAndBlock.LOGGER.info("Registering Mod Sounds for " + LockAndBlock.MOD_ID);
    }
}