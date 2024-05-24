package com.andersmmg.lockandblock.block.custom;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.tick.TickPriority;
import org.jetbrains.annotations.Nullable;

public class ForceFieldBlock extends Block {
    public static final IntProperty AGE = Properties.AGE_5;

    public ForceFieldBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        world.scheduleBlockTick(pos, this, 10, TickPriority.NORMAL);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
        if (sourceBlock instanceof ForceFieldBlock && world.getBlockState(sourcePos).getBlock() instanceof AirBlock) {
            world.setBlockState(pos, state.with(AGE, 5), 3);
        }
        world.scheduleBlockTick(pos, this, 5, TickPriority.NORMAL);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextDouble() < 0.8) {
            world.setBlockState(pos, state.cycle(AGE), 3);
        }
        if (state.get(AGE) == 5) {
            world.removeBlock(pos, false);
        } else {
            world.scheduleBlockTick(pos, this, random.nextBetween(5, 20), TickPriority.NORMAL);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
