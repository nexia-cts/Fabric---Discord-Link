package fr.catcore.fdlink.api.discord.handlers;

import fr.catcore.fdlink.api.discord.MinecraftToDiscordFunction;
import net.minecraft.text.TranslatableText;

public class TextHandler extends MessageHandler {
    private final String key;
    public TextHandler(String key, MinecraftToDiscordFunction minecraftToDiscordFunction) {
        super(minecraftToDiscordFunction);
        this.key = key;
    }

    public boolean match(TranslatableText text) {
        return text.getKey().startsWith(this.key);
    }
}
