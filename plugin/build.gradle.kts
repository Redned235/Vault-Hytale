dependencies {
    api(projects.api)
    compileOnly(files("../libs/HytaleServer.jar"))
}

tasks.jar {
    archiveClassifier.set("unshaded")
}

tasks.named("build") {
    dependsOn(tasks.shadowJar)
}

tasks.shadowJar {
    archiveFileName.set("Vault.jar")
    archiveClassifier.set("")
}
