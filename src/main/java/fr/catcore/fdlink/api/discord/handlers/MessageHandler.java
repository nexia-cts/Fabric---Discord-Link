package fr.catcore.fdlink.api.discord.handlers;

import fr.catcore.fdlink.api.config.Config;
import fr.catcore.fdlink.api.discord.MinecraftMessage;
import fr.catcore.fdlink.api.discord.MinecraftToDiscordFunction;
import fr.catcore.fdlink.api.minecraft.Message;

public abstract class MessageHandler {
    private final MinecraftToDiscordFunction minecraftToDiscordFunction;

    public MessageHandler(MinecraftToDiscordFunction minecraftToDiscordFunction) {
        this.minecraftToDiscordFunction = minecraftToDiscordFunction;
    }

    public MinecraftMessage handle(Message text, Config config) {
        return this.minecraftToDiscordFunction.handleText(text, config);
    }

    public abstract boolean match(Message message);
}
