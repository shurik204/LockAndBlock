package com.andersmmg.lockandblock.item;

import com.andersmmg.lockandblock.LockAndBlock;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;

public class ModItemGroups {
    public static final OwoItemGroup LOCKBLOCK_GROUP = OwoItemGroup
            .builder(LockAndBlock.id("item_group"), () -> Icon.of(ModItems.KEYCARD))
            .build();

    public static void registerItemGroups() {
        LockAndBlock.LOGGER.info("Registering Mod Item Groups for " + LockAndBlock.MOD_ID);
        LOCKBLOCK_GROUP.initialize();
    }
}