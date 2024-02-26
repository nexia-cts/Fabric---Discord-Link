package fr.catcore.fdlink.api.discord;

import fr.catcore.fdlink.api.minecraft.Message;

public interface MessageSender {

    void serverStarting();

    void serverStarted();

    void serverStopping();

    void serverStopped();

    void sendMessage(Message message);
}
