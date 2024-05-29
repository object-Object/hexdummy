// alternative title: OTJFPOPKPCPBP (Only The JSON5 Flattening Part Of PKPCPBP)

package hexdummy.utils

import at.petrak.pkpcpbp.filters.FlatteningJson5Transmogrifier
import at.petrak.pkpcpbp.filters.Json5Transmogrifier

// https://github.com/gamma-delta/PKPCPBP/blob/786194a590/src/main/java/at/petrak/pkpcpbp/PKSubprojPlugin.java#L84
tasks.withType<ProcessResources>().configureEach {
    outputs.upToDateWhen { false }

    filesMatching(listOf("assets/**/*.flatten.json5", "data/**/*.flatten.json5")) {
        path = path.replace(".flatten.json5", ".json")
        filter<FlatteningJson5Transmogrifier>()
    }

    filesMatching(listOf("assets/**/*.json5", "data/**/*.json5")) {
        path = path.replace(".json5", ".json")
        filter<Json5Transmogrifier>()
    }
}
