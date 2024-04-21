package net.hexdummy;

import net.hexdummy.registry.HexDummyIotaTypeRegistry;
import net.hexdummy.registry.HexDummyItemRegistry;
import net.hexdummy.registry.HexDummyPatternRegistry;
import net.hexdummy.networking.HexDummyNetworking;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is effectively the loading entrypoint for most of your code, at least
 * if you are using Architectury as intended.
 */
public class HexDummy {
    public static final String MOD_ID = "hexdummy";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


    public static void init() {
        LOGGER.info("Hex Dummy says hello!");

        HexDummyAbstractions.initPlatformSpecific();
        HexDummyItemRegistry.init();
        HexDummyIotaTypeRegistry.init();
        HexDummyPatternRegistry.init();
		HexDummyNetworking.init();

        LOGGER.info(HexDummyAbstractions.getConfigDirectory().toAbsolutePath().normalize().toString());
    }

    /**
     * Shortcut for identifiers specific to this mod.
     */
    public static Identifier id(String string) {
        return new Identifier(MOD_ID, string);
    }
}
