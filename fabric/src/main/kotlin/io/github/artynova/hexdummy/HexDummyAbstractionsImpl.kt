@file:JvmName("HexDummyAbstractionsImpl")

package io.github.artynova.hexdummy.fabric

import io.github.artynova.hexdummy.registry.HexDebugRegistrar
import net.minecraft.core.Registry

fun <T : Any> initRegistry(registrar: HexDebugRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}
