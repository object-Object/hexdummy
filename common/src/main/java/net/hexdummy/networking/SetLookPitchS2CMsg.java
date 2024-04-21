package net.hexdummy.networking;

import dev.architectury.networking.NetworkManager;
import net.minecraft.network.FriendlyByteBuf;
import net.hexdummy.client.LookClient;
import java.util.function.Supplier;

/**
 * Class that implements the functionality necessary to create, send, and execute a command (in this case rotate view)
 * from the server to a client in architectury.
 */
public class SetLookPitchS2CMsg {
    private final float pitch;

    /**
     * Constructor for creation on the server.
     * @param pitch Head pitch parameter.
     */
    public SetLookPitchS2CMsg(float pitch) {
        this.pitch = pitch;
    }

    /**
     * Constructor for recreation on the client from a {@link FriendlyByteBuf} onto which it was encoded
     * on the server using {@link #encode(FriendlyByteBuf)}.
     * @param buf Buffer onto which the message was encoded
     */
    public SetLookPitchS2CMsg(FriendlyByteBuf buf) {
        this.pitch = buf.readFloat();
    }

    /**
     * Method that encodes the message, preparing it for transmission over the network.
     * @param buf Buffer onto which the message will be encoded
     */
    public void encode(FriendlyByteBuf buf) {
        buf.writeFloat(pitch);
    }

    /**
     * Executes the command.
     * @param contextSupplier Supplier of the context that is used to schedule the timely execution of the message.
     */
    public void apply(Supplier<NetworkManager.PacketContext> contextSupplier) {
        contextSupplier.get().queue(() -> LookClient.setLookPitch(pitch));
    }
}
