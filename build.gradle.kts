import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties
import java.io.FileInputStream

plugins {
    kotlin("jvm") version "1.7.22"
}

group = "de.skycave"
version = "1.0.0"
archivesName.set("Template") // TODO

val localProperties = Properties()
localProperties.load(FileInputStream(rootProject.file("local.properties")))

repositories {
    mavenCentral()
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
    maven {
        url = uri("https://maven.pkg.github.com/skycavemc/skycavelib")
        credentials {
            username = localProperties.getProperty("gpr.user")
            password = localProperties.getProperty("gpr.key")
        }
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
    compileOnly("de.skycave:skycavelib:2.0.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
