package net.hexdummy.forge;

import dev.architectury.platform.forge.EventBuses;
import net.hexdummy.HexDummy;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * This is your loading entrypoint on forge, in case you need to initialize
 * something platform-specific.
 */
@Mod(HexDummy.MOD_ID)
public class HexDummyForge {
    public HexDummyForge() {
        // Submit our event bus to let architectury register our content on the right time
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(HexDummy.MOD_ID, bus);
        bus.addListener(HexDummyClientForge::init);
        HexDummy.init();
    }
}
