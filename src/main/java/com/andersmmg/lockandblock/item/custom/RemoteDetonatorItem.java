package com.andersmmg.lockandblock.item.custom;

import com.andersmmg.lockandblock.LockAndBlock;
import com.andersmmg.lockandblock.block.custom.DetonatorMineBlock;
import com.andersmmg.lockandblock.item.ModItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoteDetonatorItem extends Item implements DyeableItem {
    public RemoteDetonatorItem(Settings settings) {
        super(settings);
    }

    public static boolean isPaired(ItemStack stack) {
        if (!stack.isOf(ModItems.REMOTE_DETONATOR) || !stack.hasNbt()) return false;
        assert stack.getNbt() != null;
        return stack.getNbt().contains(LockAndBlock.DETONATOR_PAIR_KEY);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().getBlockState(context.getBlockPos()).getBlock() instanceof DetonatorMineBlock) {
            if (context.getWorld().isClient()) {
                return ActionResult.SUCCESS;
            }
            World world = context.getWorld();
            BlockPos pos = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            ItemStack stack = context.getStack();

            long[] pairs = stack.getOrCreateNbt().getLongArray(LockAndBlock.DETONATOR_PAIR_KEY);
            ArrayList<Long> list = new ArrayList<>(Arrays.stream(pairs).boxed().toList());
            if (!list.contains(pos.asLong())) {
                list.add(pos.asLong());
                pairs = list.stream().mapToLong(Long::longValue).toArray();
                stack.getOrCreateNbt().putLongArray(LockAndBlock.DETONATOR_PAIR_KEY, pairs);
                world.setBlockState(pos, world.getBlockState(pos).with(DetonatorMineBlock.SET, true), 3);

                assert player != null;
                player.sendMessage(LockAndBlock.langText("detonator.pair_added"), true);

                return ActionResult.SUCCESS;
            }
        }
        return super.useOnBlock(context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            return TypedActionResult.fail(user.getStackInHand(hand));
        }
        if (!user.isSneaking()) {
            if (isPaired(user.getStackInHand(hand))) {
                long[] pairs = user.getStackInHand(hand).getOrCreateNbt().getLongArray(LockAndBlock.DETONATOR_PAIR_KEY);
                for (long pair : pairs) {
                    BlockPos pos = BlockPos.fromLong(pair);
                    if (world.getBlockState(pos).getBlock() instanceof DetonatorMineBlock) {
                        ((DetonatorMineBlock) world.getBlockState(pos).getBlock()).detonate(world, pos);
                    }
                }
                user.sendMessage(Text.translatable("text." + LockAndBlock.MOD_ID + "." + "detonator.detonated", pairs.length), true);
                user.getStackInHand(hand).removeSubNbt(LockAndBlock.DETONATOR_PAIR_KEY);
            }
        }
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (isPaired(stack)) {
            tooltip.add(LockAndBlock.langText("detonator.paired").formatted(Formatting.GOLD));
        }
    }
}
