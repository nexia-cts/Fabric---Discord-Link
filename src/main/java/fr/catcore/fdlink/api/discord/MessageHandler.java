package fr.catcore.fdlink.api.discord;

import net.minecraft.text.TranslatableText;

import java.util.ArrayList;
import java.util.List;

public interface MessageHandler {

    static final List<MessageHandler> TEXT_HANDLERS = new ArrayList<>();

    MinecraftMessage handleText(TranslatableText text);

    static void registerHandler(MessageHandler messageHandler) {
        TEXT_HANDLERS.add(messageHandler);
    }

    default String adaptUsernameToDiscord(String username) {
        return adaptUsername(username);
    }

    static String adaptUsername(String username) {
        return username.replaceAll("§[b0931825467adcfeklmnor]", "")
                .replaceAll("([_`~*>])", "\\\\$1");
    }
}
