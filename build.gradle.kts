import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("jvm") version "1.9.23"
    id("com.vanniktech.maven.publish") version "0.28.0"
}

group = "dev.surly"
version = "0.1.0"

repositories {
    mavenCentral()
}

val junitVersion = "5.10.1"
val kotlinVersion: String? = buildscript.configurations["classpath"]
    .resolvedConfiguration
    .firstLevelModuleDependencies
    .find { it.moduleName == "org.jetbrains.kotlin.jvm.gradle.plugin" }
    ?.moduleVersion

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    testImplementation(platform("org.junit:junit-bom:${junitVersion}"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "21"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

mavenPublishing {
    coordinates("dev.surly", "hello-world", "0.1.0")
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)
    signAllPublications()
    pom {
        name.set("Hello World")
        description.set("A simple hello world library")
        inceptionYear.set("2024")
        url.set("https://github.com/thesurlydev/hello-world/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("thesurlydev")
                name.set("Shane Witbeck")
                url.set("https://github.com/thesurlydev/")
            }
        }
        scm {
            url.set("https://github.com/thesurlydev/hello-world/")
            connection.set("scm:git:git://github.com/thesurlydev/hello-world.git")
            developerConnection.set("scm:git:ssh://git@github.com/thesurlydev/hello-world.git")
        }
    }
}

