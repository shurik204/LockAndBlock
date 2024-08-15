package com.andersmmg.lockandblock.client.render;

import com.andersmmg.lockandblock.block.entity.LaserBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;

public class LaserRenderer implements BlockEntityRenderer<LaserBlockEntity> {
    private final Vector3f color;
    public LaserRenderer(BlockEntityRendererFactory.Context ctx, int color) {
        this.color = Vec3d.unpackRgb(color).toVector3f();
    }

    @Override
    public void render(LaserBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (tickDelta > 0.15f || entity.getWorld() == null) {
            return;
        }

        BlockState state = entity.getWorld().getBlockState(entity.getPos());
        if (state.isAir() || state.get(Properties.POWERED)) {
            return;
        }
        BlockPos pos = entity.getPos();

        int power = state.get(Properties.POWER);
        Direction direction = state.get(Properties.FACING);
        double d = (double) pos.getX() + 0.5 - 0.4 * (double) direction.getOffsetX();
        double e = (double) pos.getY() + 0.5 - 0.4 * (double) direction.getOffsetY();
        double f = (double) pos.getZ() + 0.5 - 0.4 * (double) direction.getOffsetZ();
        for (int i = 0; i < 10 * power; i++) {
            entity.getWorld().addParticle(
                    new DustParticleEffect(color, (float) 0.5),
                    true,
                    d + (double) direction.getOffsetX() * (i / 10f),
                    e + (double) direction.getOffsetY() * (i / 10f),
                    f + (double) direction.getOffsetZ() * (i / 10f),
                    0.0, 0.0, 0.0
            );
        }
    }
}