package fr.catcore.fdlink.api.discord;

import fr.catcore.fdlink.api.minecraft.CompatText;
import fr.catcore.fdlink.api.minecraft.Message;

import java.util.ArrayList;
import java.util.List;

public interface MessageHandler {

    static final List<fr.catcore.fdlink.api.discord.handlers.MessageHandler> TEXT_HANDLERS = new ArrayList<>();

    MinecraftMessage handleText(Message text);

    static void registerHandler(fr.catcore.fdlink.api.discord.handlers.MessageHandler messageHandler) {
        TEXT_HANDLERS.add(messageHandler);
    }
    default String adaptUsernameToDiscord(String username) {
        return adaptUsername(username);
    }

    static String adaptUsername(String username) {
        return username.replaceAll("§[b0931825467adcfeklmnor]", "")
                .replaceAll("([_`~*>])", "\\\\$1");
    }

    default String getArgAsString(Object arg) {
        return getAsString(arg);
    }

    static String getAsString(Object arg) {
        if (arg instanceof CompatText) {
            return ((CompatText) arg).fabric_Discord_Link$getMessage();
        } else if (arg instanceof Message) {
            return ((Message) arg).getMessage();
        }
        return (String) arg;
    }
}
