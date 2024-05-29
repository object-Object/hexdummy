package io.github.artynova.hexdummy.fabric

import io.github.artynova.hexdummy.HexDummyClient
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType.CLIENT
import net.fabricmc.api.Environment

@Environment(CLIENT)
object FabricHexDummyClient : ClientModInitializer {
    override fun onInitializeClient() {
        HexDummyClient.init()
    }
}
