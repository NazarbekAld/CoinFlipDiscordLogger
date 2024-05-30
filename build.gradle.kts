plugins {
    kotlin("jvm") version "1.9.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.nazarxexe"
version = "1.0"

repositories {
    mavenCentral()
    maven {
        name = "spigot-repo"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        name = "JitPack"
        url = uri("https://jitpack.io")
    }
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    flatDir {
        dir("libs")
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.4-R0.1-SNAPSHOT")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    compileOnly("net.zithium:deluxecoinflip:2.7.11")
    // https://mvnrepository.com/artifact/club.minnced/discord-webhooks
    implementation("club.minnced:discord-webhooks:0.8.4")
    testImplementation("com.github.seeseemelk:MockBukkit-v1.20:3.9.0")
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}