import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties
import java.util.regex.Pattern.compile

plugins {
    java
    kotlin("jvm") version "2.0.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("com.ncorti.ktfmt.gradle") version "0.19.0"
}

group = "com.monkeyteam"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "spigotmc-repo"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21-R0.1-SNAPSHOT")
    compile ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

tasks {
    processResources {
        expand("version" to project.version)
    }

    register<Exec>("upload") {
        mustRunAfter("shadowJar")
        val fileName = "${project.name}-${project.version}-all.jar"
        val sourceDirectory = "${rootDir.absolutePath}/build/libs/"
        val destinationDirectory = getLocalProperty("DESTINATION_DIRECTORY")
        val ftp = getLocalProperty("FTP")
        commandLine("scp ${sourceDirectory}${fileName} ${ftp}:${destinationDirectory}${fileName}".split(" "))
        doLast {
            println("Upload done !")
        }
    }
}

fun getLocalProperty(key: String, file: String = "local.properties"): Any {
    val properties = Properties()
    val localProperties = File(file)
    if (localProperties.isFile) {
        InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    } else error("File from not found")

    return properties.getProperty(key)
}

//def targetJavaVersion = 21
//java {
//    def javaVersion = JavaVersion.toVersion(targetJavaVersion)
//    sourceCompatibility = javaVersion
//    targetCompatibility = javaVersion
//    if (JavaVersion.current() < javaVersion) {
//        toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
//    }
//}
//
//tasks.withType(JavaCompile).configureEach {
//    options.encoding = 'UTF-8'
//
//    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible()) {
//        options.release.set(targetJavaVersion)
//    }
//}
//
