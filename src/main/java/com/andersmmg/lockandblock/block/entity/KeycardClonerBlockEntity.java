package com.andersmmg.lockandblock.block.entity;

import com.andersmmg.lockandblock.LockAndBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class KeycardClonerBlockEntity extends BlockEntity {
    private String uuid = "";

    public KeycardClonerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KEYCARD_CLONER_BLOCK_ENTITY, pos, state);
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

    public void clearUuid() {
        this.uuid = "";
    }
}
