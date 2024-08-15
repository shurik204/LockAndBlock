package com.andersmmg.lockandblock.client;

import com.andersmmg.lockandblock.block.ModBlocks;
import com.andersmmg.lockandblock.block.entity.ModBlockEntities;
import com.andersmmg.lockandblock.block.entity.RedstoneLaserBlockEntity;
import com.andersmmg.lockandblock.client.render.RedstoneLaserRenderer;
import com.andersmmg.lockandblock.client.render.SensorLaserRenderer;
import com.andersmmg.lockandblock.item.ModItems;
import com.andersmmg.lockandblock.item.custom.KeycardItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

public class LockAndBlockClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FORCEFIELD, RenderLayer.getTranslucent());
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            if (stack.getItem() instanceof KeycardItem keycardItem) {
                return keycardItem.getColor(stack);
            }
            return 0;
        }, ModItems.KEYCARD);

        BlockEntityRendererFactories.register(ModBlockEntities.REDSTONE_LASER_BLOCK_ENTITY, RedstoneLaserRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.SENSOR_LASER_BLOCK_ENTITY, SensorLaserRenderer::new);
    }
}