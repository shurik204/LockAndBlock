package com.andersmmg.lockandblock.block.entity;

import com.andersmmg.lockandblock.LockAndBlock;
import com.andersmmg.lockandblock.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {
    public static final BlockEntityType<KeycardReaderBlockEntity> KEYCARD_READER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, LockAndBlock.id("keycard_reader_be"),
                    FabricBlockEntityTypeBuilder.create(KeycardReaderBlockEntity::new,
                            ModBlocks.KEYCARD_READER).build());
    public static final BlockEntityType<KeycardClonerBlockEntity> KEYCARD_CLONER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, LockAndBlock.id("keycard_cloner_be"),
                    FabricBlockEntityTypeBuilder.create(KeycardClonerBlockEntity::new,
                            ModBlocks.KEYCARD_CLONER).build());
    public static final BlockEntityType<KeypadBlockEntity> KEYPAD_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, LockAndBlock.id("keypad_be"),
                    FabricBlockEntityTypeBuilder.create(KeypadBlockEntity::new,
                            ModBlocks.KEYPAD).build());

    public static final BlockEntityType<RedstoneLaserBlockEntity> REDSTONE_LASER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, LockAndBlock.id("redstone_laser_be"),
                    FabricBlockEntityTypeBuilder.create(RedstoneLaserBlockEntity::new,
                            ModBlocks.REDSTONE_LASER).build());
    public static final BlockEntityType<RedstoneLaserBlockEntity> SENSOR_LASER_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, LockAndBlock.id("sensor_laser_be"),
                    FabricBlockEntityTypeBuilder.create(RedstoneLaserBlockEntity::new,
                            ModBlocks.PLAYER_SENSOR).build());

    public static void registerBlockEntities() {
        LockAndBlock.LOGGER.info("Registering Block Entities for " + LockAndBlock.MOD_ID);
    }
}