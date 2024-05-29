package io.github.artynova.hexdummy.fabric

import io.github.artynova.hexdummy.HexDummy
import net.fabricmc.api.ModInitializer

object FabricHexDummy : ModInitializer {
    override fun onInitialize() {
        HexDummy.init()
    }
}
