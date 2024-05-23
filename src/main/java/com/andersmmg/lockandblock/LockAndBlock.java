package com.andersmmg.lockandblock;

import com.andersmmg.lockandblock.block.ModBlocks;
import com.andersmmg.lockandblock.block.entity.ModBlockEntities;
import com.andersmmg.lockandblock.config.ModConfig;
import com.andersmmg.lockandblock.item.ModItemGroups;
import com.andersmmg.lockandblock.item.ModItems;
import com.andersmmg.lockandblock.sounds.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockAndBlock implements ModInitializer {
    public static final String MOD_ID = "lockandblock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ModConfig CONFIG = ModConfig.createAndLoad();
    public static final String CARD_UUID_KEY = "card_uuid";

    public static final RegistryKey<DamageType> TESLA_COIL_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, id("tesla_coil_damage_type"));

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static MutableText langText(String key) {
        return langText(key, "text");
    }

    public static MutableText langText(String key, String type) {
        return Text.translatable(type + "." + LockAndBlock.MOD_ID + "." + key);
    }

    public static DamageSource damageOf(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();
        ModSounds.registerSounds();
    }
}
