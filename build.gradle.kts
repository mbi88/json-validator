import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    id("ru.vyarus.quality") version "5.0.0"
    id("java-library")
    id("jacoco")
    id("maven-publish")
}

group = "com.mbi"
version = "1.0"

val suitesDir = "src/test/resources/suites"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("org.testng:testng:7.11.0")
    implementation("org.json:json:20250517")
    implementation("io.rest-assured:rest-assured:5.5.1")
    implementation("com.github.everit-org.json-schema:org.everit.json.schema:1.14.4")
}

tasks.test {
    jvmArgs("--enable-preview")

    useTestNG {
        // Automatically include all XML test suite files from suitesDir
        fileTree(suitesDir).matching { include("*.xml") }.files.forEach { suites(it) }
    }

    testLogging {
        events("passed", "skipped", "failed")
        exceptionFormat = TestExceptionFormat.FULL
        showStandardStreams = true
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("reports/coverage"))
    }
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<Javadoc> {
    (options as StandardJavadocDocletOptions).addBooleanOption("Xdoclint:none", true)
}

quality {
    // Enable all supported static analysis tools
    checkstyle = true
    pmd = true
    codenarc = true
    spotbugs = true
}

tasks.check {
    dependsOn(tasks.jacocoTestReport)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "json-validator"
            from(components["java"])
        }
    }
}
