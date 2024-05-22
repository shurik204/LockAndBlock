package com.andersmmg.lockandblock.item.custom;

import com.andersmmg.lockandblock.LockAndBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class KeycardItem extends Item {
    public KeycardItem(Settings settings) {
        super(settings);
    }

    public static boolean hasUuid(ItemStack stack) {
        return stack.hasNbt() && stack.getNbt().contains(LockAndBlock.CARD_UUID_KEY);
    }

    public static String getUuid(ItemStack stack) {
        return stack.getNbt().getString(LockAndBlock.CARD_UUID_KEY);
    }

    public static void setUuid(String uuid, ItemStack stack) {
        stack.getOrCreateNbt().putString(LockAndBlock.CARD_UUID_KEY, uuid);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (hasUuid(stack)) {
            tooltip.add(LockAndBlock.langText("keycard.written").formatted(Formatting.GOLD));
            return;
        }
        tooltip.add(LockAndBlock.langText("keycard.blank").formatted(Formatting.ITALIC, Formatting.GRAY));
    }
}
