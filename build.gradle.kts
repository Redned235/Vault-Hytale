plugins {
    id("java")
    id("maven-publish")
    id("com.gradleup.shadow") version "9.0.0-beta12"
}

group = "me.redned.hytale"
version = "1.0.0"

allprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "com.gradleup.shadow")

    publishing {
        publications.create<MavenPublication>("library") {
            artifact(tasks.shadowJar)
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(files("libs/HytaleServer.jar"))
}
