pluginManagement {
    repositories {
        // Repositories where you can get 
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://maven.architectury.dev/") }
        maven { url = uri("https://maven.fabricmc.net/") }
        maven { url = uri("https://maven.minecraftforge.net/") }
        maven { url = uri("https://maven.blamejared.com/") }
    }
}

rootProject.name = "Hex Dummy"
include("common", "fabric", "forge")
