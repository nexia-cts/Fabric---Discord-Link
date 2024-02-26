package fr.catcore.fdlink.api.minecraft;

import fr.catcore.fdlink.api.minecraft.compat.MinecraftServerCompat;
import fr.catcore.fdlink.api.minecraft.style.Style;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;

public class VersionHelper {
    private static final List<MessageSender> MESSAGE_SENDERS = new ArrayList<>();

    public static void registerMessageSender(MessageSender messageSender) {
        MESSAGE_SENDERS.add(messageSender);
    }

    public static void sendMessageToChat(MinecraftServerCompat server, String message, Style style) {
        MessageSender messageSender = MESSAGE_SENDERS.get(0);
        messageSender.sendMessageToChat(server, message, style);
    }
}