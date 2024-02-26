package fr.catcore.fdlink.api.minecraft.compat;

import fr.catcore.fdlink.api.minecraft.Message;

public class CommandMessageCompat implements Message {

    private final String commandName;
    private final String source;
    private final String message;

    public CommandMessageCompat(String source, String message, String commandName) {
        this.message = message;
        this.source = source;
        this.commandName = commandName;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public MessageObjectType getType() {
        return MessageObjectType.TEXT;
    }

    @Override
    public TextType getTextType() {
        return TextType.COMMAND;
    }

    @Override
    public String getSource() {
        return this.source;
    }

    @Override
    public String getCommandName() {
        return this.commandName;
    }
}
