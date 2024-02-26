package fr.catcore.fdlink.api.minecraft.compat;

import fr.catcore.fdlink.api.minecraft.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.UUID;

public class PlayerEntityCompat implements PlayerEntity {

    private final ServerPlayerEntity playerEntity;

    public PlayerEntityCompat(ServerPlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }

    @Override
    public String getPlayerName() {
        return this.playerEntity.getName().getString();
    }

    @Override
    public UUID getUUID() {
        return this.playerEntity.getUuid();
    }
}
