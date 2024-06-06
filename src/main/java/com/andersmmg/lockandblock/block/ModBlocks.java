package com.andersmmg.lockandblock.block;

import com.andersmmg.lockandblock.LockAndBlock;
import com.andersmmg.lockandblock.block.custom.*;
import com.andersmmg.lockandblock.item.ModItemGroups;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlocks {
    public static final Block KEYCARD_READER = registerBlock("keycard_reader",
            new KeycardReaderBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).nonOpaque()));
    public static final Block KEYCARD_WRITER = registerBlock("keycard_writer",
            new KeycardWriterBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).nonOpaque()));
    public static final Block KEYCARD_CLONER = registerBlock("keycard_cloner",
            new KeycardClonerBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).nonOpaque()));
    public static final Block KEYPAD = registerBlock("keypad",
            new KeypadBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).nonOpaque()));
    public static final Block TESLA_COIL = registerBlock("tesla_coil",
            new TeslaCoilBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).nonOpaque()));
    public static final Block FORCEFIELD_GENERATOR = registerBlock("forcefield_generator",
            new ForceFieldGeneratorBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).nonOpaque()));
    public static final Block FORCEFIELD = registerBlockOnly("forcefield",
            new ForceFieldBlock(FabricBlockSettings.create().nonOpaque().hardness(-1).notSolid().luminance(2)));
    public static final Block PLAYER_SENSOR = registerBlock("player_sensor",
            new PlayerSensorBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).nonOpaque()));
    public static final Block PROX_MINE = registerBlock("prox_mine",
            new ProxMineBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).nonOpaque()));
    public static final Block LAND_MINE = registerBlock("land_mine",
            new LandMineBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).nonOpaque()));
    public static final Block TRIP_MINE = registerBlock("trip_mine",
            new TripMineBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).nonOpaque()));
    public static final Block LASER_SENSOR = registerBlock("laser_sensor",
            new LaserSensorBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).nonOpaque()));
    public static final Block REDSTONE_LASER = registerBlock("redstone_laser",
            new RedstoneLaser(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).nonOpaque()));
    public static final Block DETONATOR_MINE = registerBlock("detonator_mine",
            new DetonatorMineBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE).nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, LockAndBlock.id(name), block);
    }

    @SuppressWarnings("SameParameterValue")
    private static Block registerBlockOnly(String name, Block block) {
        return Registry.register(Registries.BLOCK, LockAndBlock.id(name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, LockAndBlock.id(name),
                new BlockItem(block, new OwoItemSettings().group(ModItemGroups.LOCKBLOCK_GROUP)));
    }

    public static void registerModBlocks() {
        LockAndBlock.LOGGER.info("Registering ModBlocks for " + LockAndBlock.MOD_ID);
    }
}