package com.andersmmg.falloutstuff.client.screen;

import com.andersmmg.lockandblock.LockAndBlock;
import com.andersmmg.lockandblock.block.entity.KeycardReaderBlockEntity;
import com.andersmmg.lockandblock.record.KeycardReaderPacket;
import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class KeycardReaderScreen extends BaseOwoScreen<FlowLayout> {
    private final KeycardReaderBlockEntity blockEntity;
    private final String uuid;

    public KeycardReaderScreen(KeycardReaderBlockEntity entity, String uuid) {
        this.blockEntity = entity;
        this.uuid = uuid;
    }

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent
                .surface(Surface.VANILLA_TRANSLUCENT)
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .verticalAlignment(VerticalAlignment.CENTER);

        rootComponent.child(
                Containers.verticalFlow(Sizing.content(), Sizing.content())
                        .child(Components.label(LockAndBlock.langText("keycard_reader.title", "gui").formatted(Formatting.BLACK)))
                        .gap(5)
                        .child(Components.button(LockAndBlock.langText("keycard_reader.clear", "gui"), button -> {
                            LockAndBlock.KEYCARD_READER_CHANNEL.clientHandle().send(new KeycardReaderPacket(blockEntity.getPos(), false, uuid));
                            close();
                        }).horizontalSizing(Sizing.fixed(70)))
                        .child(Components.button(LockAndBlock.langText("keycard_reader.remove", "gui"), button -> {
                            LockAndBlock.KEYCARD_READER_CHANNEL.clientHandle().send(new KeycardReaderPacket(blockEntity.getPos(), true, uuid));
                            close();
                        }).horizontalSizing(Sizing.fixed(70)))
                        .padding(Insets.of(10))
                        .surface(Surface.PANEL)
                        .verticalAlignment(VerticalAlignment.CENTER)
                        .horizontalAlignment(HorizontalAlignment.CENTER)
        );
    }
}