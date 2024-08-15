package com.andersmmg.lockandblock.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class LaserBlockEntity extends BlockEntity {
    public LaserBlockEntity(BlockEntityType<RedstoneLaserBlockEntity> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state);
    }
}