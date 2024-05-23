package com.andersmmg.lockandblock.record;

import net.minecraft.util.math.BlockPos;

public record KeycardReaderPacket(BlockPos pos, boolean remove, String uuid) {
}