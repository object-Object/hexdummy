dependencyResolutionManagement {
    versionCatalogs {
        // allow referencing the version catalog from the main project
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}
