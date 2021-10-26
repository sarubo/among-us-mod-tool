plugins {
    val kotlinVersion = "1.5.31"
    this.kotlin("jvm").version(kotlinVersion)
    this.kotlin("plugin.serialization").version(kotlinVersion)
    this.application
}

group = "sarubo"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    this.implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
    this.implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
}

val mainClassNamePath = "sarubo.MainKt"

application {
    this.mainClass.set(mainClassNamePath)
}

val jvmMinorVersionString = "1.8"
tasks {
    this.compileKotlin {
        this.kotlinOptions {
            jvmTarget = jvmMinorVersionString
        }
    }
    this.jar {
        this.duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        this.manifest {
            this.attributes["Main-Class"] = mainClassNamePath
        }
        configurations.compileClasspath.configure {
            this.forEach { file: File ->
                this@jar.from(zipTree(file.absoluteFile))
            }
        }
    }
}