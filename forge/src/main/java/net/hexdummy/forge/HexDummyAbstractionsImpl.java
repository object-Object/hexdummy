package net.hexdummy.forge;

import net.hexdummy.HexDummyAbstractions;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class HexDummyAbstractionsImpl {
    /**
     * This is the actual implementation of {@link HexDummyAbstractions#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
	
    public static void initPlatformSpecific() {
        HexDummyConfigForge.init();
    }
}
