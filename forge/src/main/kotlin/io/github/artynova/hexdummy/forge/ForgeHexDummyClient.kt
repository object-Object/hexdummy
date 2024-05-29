package io.github.artynova.hexdummy.forge

import io.github.artynova.hexdummy.HexDummyClient
import io.github.artynova.hexdummy.config.HexDummyConfig
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.LOADING_CONTEXT

object ForgeHexDummyClient {
    fun init(event: FMLClientSetupEvent) {
        HexDummyClient.init()
        LOADING_CONTEXT.registerExtensionPoint(ConfigScreenFactory::class.java) {
            ConfigScreenFactory { _, parent -> HexDummyConfig.getConfigScreen(parent) }
        }
    }
}
