package com.andersmmg.lockandblock.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class KeycardReaderBlockEntity extends BlockEntity {
    public KeycardReaderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KEYCARD_READER_BLOCK_ENTITY, pos, state);
    }
}
