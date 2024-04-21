package net.hexdummy.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.hexdummy.HexDummyClient;

/**
 * Fabric client loading entrypoint.
 */
public class HexDummyClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HexDummyClient.init();
    }
}
