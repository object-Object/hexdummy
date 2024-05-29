@file:OptIn(ExperimentalStdlibApi::class)

package io.github.artynova.hexdummy.forge.datagen

import io.github.artynova.hexdummy.HexDummy
import io.github.artynova.hexdummy.items.ItemDebugger
import io.github.artynova.hexdummy.items.ItemDebugger.DebugState
import io.github.artynova.hexdummy.items.ItemDebugger.StepMode
import io.github.artynova.hexdummy.items.ItemEvaluator
import io.github.artynova.hexdummy.items.ItemEvaluator.EvalState
import io.github.artynova.hexdummy.registry.HexDummyItems
import io.github.artynova.hexdummy.utils.itemPredicate
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.client.model.generators.ModelBuilder
import net.minecraftforge.client.model.generators.ModelFile
import net.minecraftforge.common.data.ExistingFileHelper

class HexDummyModels(output: PackOutput, efh: ExistingFileHelper) : ItemModelProvider(output, HexDummy.MODID, efh) {
    override fun registerModels() {
        basicItem(HexDummyItems.DUMMY_ITEM.id)
            .parent(ModelFile.UncheckedModelFile("item/handheld_rod"))
    }
}

// utility function for adding multiple possibly missing layers to a generated item model
fun <T : ModelBuilder<T>> T.layers(start: Int, vararg layers: String?): T {
    var index = start
    for (layer in layers) {
        if (layer != null) {
            texture("layer$index", layer)
            index += 1
        }
    }
    return this
}
