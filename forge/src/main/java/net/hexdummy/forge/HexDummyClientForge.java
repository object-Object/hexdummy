package net.hexdummy.forge;

import net.hexdummy.HexDummyClient;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Forge client loading entrypoint.
 */
public class HexDummyClientForge {
    public static void init(FMLClientSetupEvent event) {
        HexDummyClient.init();
    }
}
