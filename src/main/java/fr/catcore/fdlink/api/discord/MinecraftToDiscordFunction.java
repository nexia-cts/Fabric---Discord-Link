package fr.catcore.fdlink.api.discord;

import com.mojang.brigadier.Message;
import fr.catcore.fdlink.api.config.Config;

public interface MinecraftToDiscordFunction {

    MinecraftMessage handleText(Message text, Config config);
}
