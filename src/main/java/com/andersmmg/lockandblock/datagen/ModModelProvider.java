package com.andersmmg.lockandblock.datagen;

import com.andersmmg.lockandblock.block.ModBlocks;
import com.andersmmg.lockandblock.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerActivatable(blockStateModelGenerator, ModBlocks.KEYCARD_READER);
        registerRotatable(blockStateModelGenerator, ModBlocks.KEYCARD_WRITER);
        registerRotatable(blockStateModelGenerator, ModBlocks.KEYCARD_CLONER);
        registerTeslaCoil(blockStateModelGenerator, ModBlocks.TESLA_COIL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.KEYCARD, Models.GENERATED);
    }

    private void registerRotatable(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        Identifier model_base = ModelIds.getBlockModelId(block);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, model_base))
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
    }

    private void registerTeslaCoil(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        Identifier model_base = ModelIds.getBlockModelId(block);
        Identifier model_powered = ModelIds.getBlockSubModelId(block, "_powered");
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, model_base))
                .coordinate(BlockStateModelGenerator.createNorthDefaultRotationStates())
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.POWERED, model_powered, model_base)));
    }

    private void registerActivatable(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        Identifier model_base = ModelIds.getBlockModelId(block);
        Identifier model_open = ModelIds.getBlockSubModelId(block, "_active");
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
                .coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.POWERED, model_open, model_base)));
    }

}