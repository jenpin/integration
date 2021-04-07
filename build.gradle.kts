
plugins {
    kotlin("jvm") version "1.3.60"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        events("passed", "failed", "skipped")
        setExceptionFormat("full")
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}


application {
    mainClassName = "helper.OrchestratorKt"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    api("com.google.code.gson:gson:2.8.5")

    testImplementation("org.assertj:assertj-core:3.18.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

sourceSets {

    main {

    }
    create("intTest") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

val intTestImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.implementation.get())
}

val intTestRuntimeOnly: Configuration by configurations.getting {
    extendsFrom(configurations.runtimeOnly.get())
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

}


val integrationTest = task<Test>("integrationTest") {
    description = "Runs integration tests"
    group = "verification"

    useJUnitPlatform()

    testClassesDirs = sourceSets["intTest"].output.classesDirs
    classpath = sourceSets["intTest"].runtimeClasspath
    outputs.upToDateWhen { false }
    shouldRunAfter("test")

    testLogging {
        events("passed", "failed", "skipped")
        setExceptionFormat("full")
    }

}

dependencies {
    intTestImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    intTestRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}