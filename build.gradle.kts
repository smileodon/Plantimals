plugins {
    java
    id("io.papermc.paperweight.userdev") version "1.5.11" // Check for new versions at https://plugins.gradle.org/plugin/io.papermc.paperweight.userdev
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("xyz.jpenilla.run-paper") version "2.2.2"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


group = "de.smileodon"
version = "1.0-SNAPSHOT"



repositories {
    mavenCentral()
}


dependencies {
    paperweight.paperDevBundle("1.20.4-R0.1-SNAPSHOT")
    bukkitLibrary("de.eldoria.util", "debugging", "2.0.3")
    bukkitLibrary("de.eldoria.util", "jackson-configuration", "2.0.3")
    bukkitLibrary("de.eldoria.jacksonbukkit", "jackson-bukkit", "1.2.0")
    bukkitLibrary("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.2")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
    test {
        useJUnitPlatform()
    }
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("1.20.4")
    }
}

bukkit {
    name = "Plantimals"
    main = "de.smileodon.plantimals.PlantimalsPlugin"
    authors = listOf("Aljosha")
    website = "https://smileodon.de"
    apiVersion = "1.20"

}