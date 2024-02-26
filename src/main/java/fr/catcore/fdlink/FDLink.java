package fr.catcore.fdlink;

import fr.catcore.fdlink.api.discord.MessageSender;
import fr.catcore.fdlink.api.minecraft.Message;
import fr.catcore.fdlink.api.minecraft.MessagePacket;
import fr.catcore.fdlink.api.minecraft.VersionHelper;
import fr.catcore.fdlink.api.minecraft.compat.MessageCompat;
import fr.catcore.fdlink.api.minecraft.compat.MessagePacketCompat;
import fr.catcore.fdlink.api.minecraft.compat.MinecraftServerCompat;
import fr.catcore.fdlink.config.ConfigHandler;
import fr.catcore.fdlink.discord.bot.DiscordBot;
import fr.catcore.fdlink.discord.webhook.DiscordWebhook;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.text.BaseText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class FDLink implements DedicatedServerModInitializer {

    private static DiscordBot messageReceiver;
    private static MessageSender messageSender;
    public static Logger LOGGER = Logger.getLogger("FDLink");
    public static Logger MESSAGE_LOGGER = Logger.getLogger("Discord->Minecraft");
    private static boolean loaded = false;

    @Override
    public void onInitializeServer() {
        initialize();
    }

    public static void handleText(Text text, UUID uUID) {
        if (text instanceof BaseText baseText) {
            FDLink.getMessageSender().sendMessage(getMessageFromText(baseText).setAuthorUUID(uUID));
        }
        else {
            FDLink.getMessageSender().sendMessage(new MessageCompat(text.getString()).setAuthorUUID(uUID));
        }
    }

    private static Message getMessageFromText(BaseText text) {
        List<Text> siblings = text.getSiblings();
        Message message;
        if (text instanceof TranslatableText translatableText) {
            Object[] args = translatableText.getArgs();
            Object[] argsList = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if (arg instanceof BaseText) {
                    argsList[i] = getMessageFromText((BaseText) arg);
                } else {
                    argsList[i] = arg;
                }
            }
            message = new MessageCompat(translatableText.getKey(), text.getString(), argsList);
        }
        else message = new MessageCompat(text.getString());
        for (Text sib : siblings) {
            if (sib instanceof BaseText baseText) message.addSibbling(getMessageFromText(baseText));
            else message.addSibbling(new MessageCompat(sib.getString()));
        }

        return message;
    }

    private static void initialize() {
        ConfigHandler.ConfigHolder configHolder = ConfigHandler.getConfig();
        messageReceiver = new DiscordBot(configHolder.getToken(), configHolder.getConfig());
        if (configHolder.getConfig().mainConfig.webhook.url.isEmpty()) {
            messageSender = messageReceiver;
        } else {
            LOGGER.info("Found a webhook URL, using Webhook instead of Bot to send message.");
            if (configHolder.getConfig().mainConfig.chatChannels.isEmpty() && configHolder.getConfig().mainConfig.logChannels.isEmpty()) {
                LOGGER.warning("Unable to find any channel id, only Minecraft->Discord will work, add a channel id to the config if this wasn't intended.");
            }
            messageSender = new DiscordWebhook(configHolder.getConfig().mainConfig.webhook.url, configHolder.getConfig(), messageReceiver);
        }

        initializeVersion();

        loaded = true;
    }

    private static void initializeVersion() {
        ServerTickEvents.START_SERVER_TICK.register((server -> FDLink.getMessageReceiver().serverTick(new MinecraftServerCompat(server))));

        VersionHelper.registerMessageSender((server, message, style) -> {
            Message literalText = new MessageCompat(message);
            if (style != null) {
                literalText = literalText.setStyle(style);
            }
            server.sendMessageToAll(new MessagePacketCompat(literalText, MessagePacket.MessageType.CHAT, UUID.randomUUID()));
        });

        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> FDLink.getMessageSender().serverStarting());
        ServerLifecycleEvents.SERVER_STARTED.register((server -> FDLink.getMessageSender().serverStarted()));
        ServerLifecycleEvents.SERVER_STOPPING.register(minecraftServer -> FDLink.getMessageSender().serverStopping());
        ServerLifecycleEvents.SERVER_STOPPED.register((server -> FDLink.getMessageSender().serverStopped()));

    }

    public static void regenConfig() {
        ConfigHandler.ConfigHolder configHolder = ConfigHandler.getConfig();
        messageReceiver = new DiscordBot(configHolder.getToken(), configHolder.getConfig());
        if (configHolder.getConfig().mainConfig.webhook.url.isEmpty()) {
            messageSender = messageReceiver;
        } else {
            messageSender = new DiscordWebhook(configHolder.getConfig().mainConfig.webhook.url, configHolder.getConfig(), messageReceiver);
        }
    }

    public static MessageSender getMessageSender() {
        if (!loaded) initialize();
        return messageSender;
    }

    public static DiscordBot getMessageReceiver() {
        if (!loaded) initialize();
        return messageReceiver;
    }
}