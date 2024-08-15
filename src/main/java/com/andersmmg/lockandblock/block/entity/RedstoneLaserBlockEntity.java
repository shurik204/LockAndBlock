package com.andersmmg.lockandblock.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class RedstoneLaserBlockEntity extends LaserBlockEntity {
    public RedstoneLaserBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.REDSTONE_LASER_BLOCK_ENTITY, pos, state);
    }
}