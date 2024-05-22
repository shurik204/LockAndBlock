package com.andersmmg.lockandblock;

import com.andersmmg.lockandblock.block.ModBlocks;
import com.andersmmg.lockandblock.block.entity.ModBlockEntities;
import com.andersmmg.lockandblock.item.ModItemGroups;
import com.andersmmg.lockandblock.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockAndBlock implements ModInitializer {
    public static final String MOD_ID = "lockandblock";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
//    public static final ModConfig CONFIG = ModConfig.createAndLoad();

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();
    }
}
