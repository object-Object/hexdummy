// A convention plugin that should be applied to all Minecraft-related subprojects, including common.

package hexdummy

plugins {
    id("hexdummy.kotlin")
    id("hexdummy.utils.json5")

    `maven-publish`
    id("dev.architectury.loom")
}

val modId: String by project
val platform: String by project

base.archivesName = "${modId}-$platform"

loom {
    silentMojangMappingsLicense()
    accessWidenerPath = project(":common").file("src/main/resources/hexdummy.accesswidener")
}

dependencies {
    minecraft(libs.minecraft)

    mappings(loom.layered {
        officialMojangMappings()
        parchment(libs.parchment)
    })

    annotationProcessor(libs.bundles.asm)
}

sourceSets {
    main {
        kotlin {
            srcDir(file("src/main/java"))
        }
        resources {
            srcDir(file("src/generated/resources"))
        }
    }
}

tasks {
    val artifactsTask = register<Copy>("githubArtifacts") {
        from(remapJar, remapSourcesJar, get("javadocJar"))
        into(rootDir.toPath() / "build" / "githubArtifacts")
    }

    build {
        dependsOn(artifactsTask)
    }
}
