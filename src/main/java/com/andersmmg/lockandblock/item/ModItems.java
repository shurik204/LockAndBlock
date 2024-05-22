package com.andersmmg.lockandblock.item;

import com.andersmmg.lockandblock.LockAndBlock;
import com.andersmmg.lockandblock.item.custom.KeycardItem;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {
    public static final Item KEYCARD = registerItem("keycard", new KeycardItem(new OwoItemSettings().group(ModItemGroups.LOCKBLOCK_GROUP)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, LockAndBlock.id(name), item);
    }

    public static void registerModItems() {
        LockAndBlock.LOGGER.info("Registering Mod Items for " + LockAndBlock.MOD_ID);
    }
}