package com.andersmmg.lockandblock.client.render;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

public class RedstoneLaserRenderer extends LaserRenderer {
    public RedstoneLaserRenderer(BlockEntityRendererFactory.Context ctx) {
        super(ctx, 0xFF0000);
    }
}
