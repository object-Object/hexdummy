package io.github.artynova.hexdummy.forge

import dev.architectury.platform.forge.EventBuses
import io.github.artynova.hexdummy.HexDummy
import io.github.artynova.hexdummy.forge.datagen.HexDummyModels
import io.github.artynova.hexdummy.forge.datagen.HexDummyRecipes
import net.minecraft.data.DataProvider
import net.minecraft.data.DataProvider.Factory
import net.minecraft.data.PackOutput
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

/**
 * This is your loading entrypoint on forge, in case you need to initialize
 * something platform-specific.
 */
@Mod(HexDummy.MODID)
class HexDummyForge {
    init {
        MOD_BUS.apply {
            EventBuses.registerModEventBus(HexDummy.MODID, this)
            addListener(ForgeHexDummyClient::init)
            addListener(::gatherData)
        }
        HexDummy.init()
    }

    private fun gatherData(event: GatherDataEvent) {
        event.apply {
            val efh = existingFileHelper
            addProvider(includeClient()) { HexDummyModels(it, efh) }
            addProvider(includeServer()) { HexDummyRecipes(it) }
        }
    }
}

fun <T : DataProvider> GatherDataEvent.addProvider(run: Boolean, factory: (PackOutput) -> T) =
    generator.addProvider(run, Factory { factory(it) })
