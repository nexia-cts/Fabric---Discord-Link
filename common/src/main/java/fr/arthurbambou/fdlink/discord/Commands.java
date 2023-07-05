package fr.arthurbambou.fdlink.discord;

import fr.arthurbambou.fdlink.api.discord.MessageHandler;
import fr.arthurbambou.fdlink.api.minecraft.MinecraftServer;
import fr.arthurbambou.fdlink.api.minecraft.PlayerEntity;
import fr.arthurbambou.fdlink.discordstuff.MinecraftToDiscordHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public enum Commands {
    playerlist("Show the list of players on the server.",(minecraftServer, messageCreateEvent, startTime) -> {
        StringBuilder playerlist = new StringBuilder();

        List<PlayerEntity> players = minecraftServer.getPlayers();

        if (players.size() > 0) {
            playerlist.append("Players:").append("\n");

            for (int i = 0; i < players.size() - 1; i++) {
                playerlist.append("- ").append(MessageHandler.adaptUsername(players.get(i).getPlayerName())).append("\n");
            }

            playerlist.append("- ").append(MessageHandler.adaptUsername(players.get(players.size() - 1).getPlayerName()));
        } else {
            playerlist.append("There are no players online.");
        }

        messageCreateEvent.getChannel().sendMessage(playerlist).submit();
        return false;
    });

    private final String description;
    private final CommandFunction function;

    Commands(String description, CommandFunction function) {
        this.description = description;
        this.function = function;
    }

    public String getDescription() {
        return description;
    }

    public boolean execute(MinecraftServer minecraftServer, MessageReceivedEvent messageCreateEvent, long startTime) {
        return this.function.execute(minecraftServer, messageCreateEvent, startTime);
    }

    private interface CommandFunction {

        boolean execute(MinecraftServer minecraftServer, MessageReceivedEvent messageCreateEvent, long startTime);
    }
}
