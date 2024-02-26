package fr.catcore.fdlink.api.discord.handlers;

import fr.catcore.fdlink.api.discord.MinecraftToDiscordFunction;
import fr.catcore.fdlink.api.minecraft.Message;

public class CommandHandler extends MessageHandler {
    private final String commandName;

    public CommandHandler(String commandName, MinecraftToDiscordFunction minecraftToDiscordFunction) {
        super(minecraftToDiscordFunction);
        this.commandName = commandName;
    }

    @Override
    public boolean match(Message message) {
        if (message.getTextType() == Message.TextType.COMMAND) {
            return this.commandName.equals(message.getCommandName());
        }
        return false;
    }
}
