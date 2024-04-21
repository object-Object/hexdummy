package net.hexdummy.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.hexdummy.HexDummyAbstractions;

import java.nio.file.Path;

public class HexDummyAbstractionsImpl {
    /**
     * This is the actual implementation of {@link HexDummyAbstractions#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
	
    public static void initPlatformSpecific() {
        HexDummyConfigFabric.init();
    }
}
