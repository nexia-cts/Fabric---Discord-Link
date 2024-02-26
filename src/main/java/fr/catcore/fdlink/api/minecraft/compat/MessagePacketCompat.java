package fr.catcore.fdlink.api.minecraft.compat;

import fr.catcore.fdlink.api.minecraft.Message;
import fr.catcore.fdlink.api.minecraft.MessagePacket;

import java.util.UUID;

public class MessagePacketCompat implements MessagePacket {

    private Message message;
    private MessageType messageType;
    private UUID uuid;

    public MessagePacketCompat(Message message, MessageType messageType, UUID uuid) {
        this.message = message;
        this.messageType = messageType;
        this.uuid = uuid;
    }

    @Override
    public Message getMessage() {
        return this.message;
    }

    @Override
    public MessageType getMessageType() {
        return this.messageType;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }
}
