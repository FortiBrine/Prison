
tasks {

    jar {

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from (
            configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
        )

    }

}

dependencies {
    implementation(libs.coroutines)
    implementation(libs.dagger)
    ksp(libs.dagger)

    compileOnly(libs.spigot)
}

