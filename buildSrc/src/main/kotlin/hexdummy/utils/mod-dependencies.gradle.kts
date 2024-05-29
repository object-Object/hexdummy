package hexdummy.utils

plugins {
    id("me.modmuss50.mod-publish-plugin")
}

// plugin config

abstract class HexDummyModDependenciesExtension(private val project: Project) {
    abstract val filesMatching: ListProperty<String>
    abstract val anyVersion: Property<String>

    abstract val mapVersions: Property<VersionMapper.() -> Unit>

    private val versionCatalog by lazy {
        project.extensions.getByType<VersionCatalogsExtension>().named("libs")
    }

    fun mapVersions(action: VersionMapper.() -> Unit) {
        mapVersions = action
    }

    fun getVersions(): Map<String, String> = versionCatalog.versionAliases.associate {
        val mapper = VersionMapper(
            name = it.replace(".", "_"),
            constraint = versionCatalog.findVersion(it).get(),
        )
        mapVersions.get().invoke(mapper)
        mapper.name to (mapper.version ?: anyVersion.get())
    }

    fun requires(slug: String) = requires(slug, slug)

    fun requires(curseforgeSlug: String, modrinthSlug: String) = project.run {
        publishMods {
            curseforge { requires(curseforgeSlug) }
            modrinth { requires(modrinthSlug) }
        }
    }

    fun optional(slug: String) = optional(slug, slug)

    fun optional(curseforgeSlug: String, modrinthSlug: String) = project.run {
        publishMods {
            curseforge { optional(curseforgeSlug) }
            modrinth { optional(modrinthSlug) }
        }
    }

    data class VersionMapper(
        val name: String,
        val constraint: VersionConstraint,
    ) {
        var version: String? = constraint.requiredVersion.takeIf { it.isNotEmpty() }

        fun replace(old: String, new: String) {
            version = version?.replace(old, new)
        }

        fun replace(old: Regex, new: String) {
            version = version?.replace(old, new)
        }
    }
}

val extension = extensions.create<HexDummyModDependenciesExtension>("hexdummyModDependencies")

// build logic

val modVersion: String by project

tasks.withType<ProcessResources>().configureEach {
    // allow referencing values from libs.versions.toml in Fabric/Forge mod configs
    val dependencyVersions = mapOf(
        "modVersion" to modVersion,
        "versions" to extension.getVersions(),
    )

    // for incremental builds
    inputs.properties(dependencyVersions)

    filesMatching(extension.filesMatching.get()) {
        expand(dependencyVersions)
    }
}
