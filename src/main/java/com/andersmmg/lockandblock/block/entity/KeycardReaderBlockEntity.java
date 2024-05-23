package com.andersmmg.lockandblock.block.entity;

import com.andersmmg.lockandblock.LockAndBlock;
import com.andersmmg.lockandblock.item.custom.KeycardItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class KeycardReaderBlockEntity extends BlockEntity {
    private String uuid = "";

    public KeycardReaderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KEYCARD_READER_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putString(LockAndBlock.CARD_UUID_KEY, uuid);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        uuid = nbt.getString(LockAndBlock.CARD_UUID_KEY);
    }

    public boolean hasUuid() {
        return !uuid.isEmpty();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean checkKeycard(ItemStack stack) {
        if (KeycardItem.hasUuid(stack)) {
            return KeycardItem.getUuid(stack).equals(this.uuid);
        }
        return false;
    }

    public void clearUuid() {
        this.uuid = "";
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
