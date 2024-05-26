package com.andersmmg.lockandblock.datagen;

import com.andersmmg.lockandblock.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.TRIP_MINE);
        addDrop(ModBlocks.PROX_MINE);
        addDrop(ModBlocks.LAND_MINE);
        addDrop(ModBlocks.PLAYER_SENSOR);
        addDrop(ModBlocks.TESLA_COIL);
        addDrop(ModBlocks.KEYPAD);
        addDrop(ModBlocks.KEYCARD_READER);
        addDrop(ModBlocks.KEYCARD_WRITER);
        addDrop(ModBlocks.KEYCARD_CLONER);
        addDrop(ModBlocks.FORCEFIELD_GENERATOR);
    }

}