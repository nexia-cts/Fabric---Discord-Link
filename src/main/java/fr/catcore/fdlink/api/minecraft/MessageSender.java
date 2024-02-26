package fr.catcore.fdlink.api.minecraft;

import fr.catcore.fdlink.api.minecraft.compat.MinecraftServerCompat;
import fr.catcore.fdlink.api.minecraft.style.Style;

public interface MessageSender {

    void sendMessageToChat(MinecraftServerCompat server, String message, Style style);
}