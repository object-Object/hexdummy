package net.hexdummy.networking;

import dev.architectury.networking.NetworkChannel;
import net.minecraft.server.level.ServerPlayer;

import static net.hexdummy.HexDummy.id;

public class HexDummyNetworking {
    private static final NetworkChannel CHANNEL = NetworkChannel.create(id("networking_channel"));

    public static void init() {
        CHANNEL.register(SetLookPitchS2CMsg.class, SetLookPitchS2CMsg::encode, SetLookPitchS2CMsg::new, SetLookPitchS2CMsg::apply);
    }

    public static <T> void sendToServer(T message) {
        CHANNEL.sendToServer(message);
    }

    public static <T> void sendToPlayer(ServerPlayer player, T message) {
        CHANNEL.sendToPlayer(player, message);
    }

    public static <T> void sendToPlayers(Iterable<ServerPlayer> players, T message) {
        CHANNEL.sendToPlayers(players, message);
    }
}
