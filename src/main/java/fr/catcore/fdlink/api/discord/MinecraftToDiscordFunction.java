package fr.catcore.fdlink.api.discord;

import fr.catcore.fdlink.api.config.Config;
import fr.catcore.fdlink.api.minecraft.Message;

public interface MinecraftToDiscordFunction {

    MinecraftMessage handleText(Message text, Config config);
}
