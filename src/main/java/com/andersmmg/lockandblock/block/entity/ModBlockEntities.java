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

    public static void registerBlockEntities() {
        LockAndBlock.LOGGER.info("Registering Block Entities for " + LockAndBlock.MOD_ID);
    }
}