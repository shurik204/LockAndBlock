package com.andersmmg.lockandblock.block.custom;

import com.andersmmg.lockandblock.LockAndBlock;
import com.andersmmg.lockandblock.util.VoxelUtils;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.tick.TickPriority;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LaserSensorBlock extends Block {
    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty SET = LockAndBlock.SET;
    public static final BooleanProperty POWERED = Properties.POWERED;
    public static final IntProperty POWER = Properties.POWER;
    private static final VoxelShape VOXEL_SHAPE = Block.createCuboidShape(6, 6, 14, 10, 10, 16);
    private static final VoxelShape VOXEL_SHAPE_UP = Block.createCuboidShape(6, 0, 6, 10, 2, 10);
    private static final VoxelShape VOXEL_SHAPE_DOWN = Block.createCuboidShape(6, 14, 6, 10, 16, 10);

    public LaserSensorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(SET, false).with(POWERED, false).with(POWER, 0));
    }

    protected static Direction getDirection(BlockState state) {
        return state.get(FACING);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        world.scheduleBlockTick(pos, this, 20, TickPriority.NORMAL);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(SET)) {
            boolean shouldPower = this.shouldPower(world, pos, state);
            if (state.get(POWERED) != shouldPower) {
                world.setBlockState(pos, state.with(POWERED, shouldPower), 3);
                state.updateNeighbors(world, pos, 3);
            }
        } else {
            world.setBlockState(pos, state.with(SET, true), 3);
        }
        world.scheduleBlockTick(pos, this, 1, TickPriority.byIndex(1));
    }

    private boolean shouldPower(World world, BlockPos pos, BlockState state) {
        Direction direction = state.get(RedstoneLaser.FACING);
        int distance = LockAndBlock.CONFIG.allowTripMinesAir() ? LockAndBlock.CONFIG.maxTripMineDistance() + 1 : 0;

        for (int i = 1; i <= Math.min(LockAndBlock.CONFIG.maxTripMineDistance() + 1, 15); i++) {
            BlockState blockState = world.getBlockState(pos.offset(direction, i));
            String blockId = Registries.BLOCK.getId(blockState.getBlock()).toString();
            if (LockAndBlock.CONFIG.laserPassthroughWhitelist().contains(blockId)) {
                continue;
            }
            if (blockState.getCameraCollisionShape(world, pos.offset(direction, i), ShapeContext.absent()).isEmpty()) {
                continue;
            }
            if (blockState.isSideSolid(world, pos.offset(direction, i), direction.getOpposite(), SideShapeType.CENTER)) {
                distance = i;
                break;
            }
            if (blockState.isSideSolid(world, pos.offset(direction, i), direction, SideShapeType.CENTER)) {
                distance = i + 1;
                break;
            }
        }

        world.setBlockState(pos, state.with(POWER, distance), Block.NOTIFY_LISTENERS);

        if (distance == 0) {
            return false;
        }

        // check if there are players in the area
        double expandOffset = ((distance - 1) / 2.0f);
        Box detectionBox = new Box(pos).contract(0.5f)
                .expand(direction.getOffsetX() * expandOffset, direction.getOffsetY() * expandOffset, direction.getOffsetZ() * expandOffset)
                .offset(direction.getOffsetX() * (distance - 1) * 0.5f, direction.getOffsetY() * (distance - 1) * 0.5f, direction.getOffsetZ() * (distance - 1) * 0.5f);

        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, detectionBox, entity -> true);
        return !entities.isEmpty();
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(POWERED) ? 15 : 0;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(POWERED) ? 15 : 0;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (getDirection(state)) {
            case UP -> VOXEL_SHAPE_UP;
            case DOWN -> VOXEL_SHAPE_DOWN;
            default -> VoxelUtils.rotateShape(getDirection(state), VOXEL_SHAPE);
        };
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, SET, POWERED, POWER);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getSide());
    }
}
