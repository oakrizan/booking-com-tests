import io.qameta.allure.gradle.AllureExtension
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

val allureVersion by extra("2.13.6")

plugins {
    kotlin("jvm") version "1.3.61"

    id("io.qameta.allure") version "2.8.1"
    id("com.diffplug.gradle.spotless") version "3.15.0"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("org.springframework.boot") version "2.2.4.RELEASE"
}

apply(plugin = "kotlin")
apply(plugin = "io.qameta.allure")
apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

val compileKotlin: KotlinCompile by tasks
val compileTestKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.jvmTarget = "1.8"
compileKotlin.kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict")
compileTestKotlin.kotlinOptions.jvmTarget = "1.8"
compileTestKotlin.kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=straict")

repositories {
    mavenCentral()
    jcenter()
}

ext {
    set("junit-jupiter.version", "5.6.0")
    set("cucumberVersion", "6.7.0")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.codeborne:selenide:5.15.1")
    implementation("com.sun.xml.bind:jaxb-osgi:2.3.2")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.+")
    implementation("io.github.serpro69:kotlin-faker:1.4.0")
    implementation("io.qameta.allure:allure-selenide:$allureVersion")
    implementation("io.qameta.allure:allure-cucumber-jvm:2.13.6")

    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.vintage:junit-vintage-engine")
    testImplementation("io.cucumber:cucumber-spring:6.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:latest.release")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:latest.release")
    testImplementation("io.cucumber:cucumber-java:latest.release")
    testImplementation("io.cucumber:cucumber-java8:latest.release")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:latest.release")

    api("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "junit")
        exclude(group = "org.hamcrest")
        exclude(group = "org.mockito")
        exclude(group = "org.seleniumhq.selenium")
    }
}

//configure<SpotlessExtension> {
//    format("misc") {
//        target("*/src/**/*.kt")
//        endWithNewline()
//        trimTrailingWhitespace()
//    }
//    kotlin {
//        target("*/src/**/*.kt")
//        ktlint("0.29.0")
//    }
//    kotlinGradle {
//        target("**/*gradle.kts")
//        ktlint("0.29.0")
//    }
//}

configure<AllureExtension> {
    val allureResultsDir = "allure-results"
    val allureReportDir = "allure-report"
    version = allureVersion
    resultsDir = file("$rootDir/$allureResultsDir")
    reportDir = file("$rootDir/$allureReportDir")
    useJUnit5 {
        version = allureVersion
    }
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            events( "passed", "skipped", "failed" )
            showExceptions = true
            exceptionFormat = FULL
            showStackTraces = true
        }

        if (project.hasProperty("browser")) {
            systemProperty("selenide.browser", project.property("browser") ?: "chrome")
        }
        systemProperty("selenide.headless", "false")
        if (project.hasProperty("grid")) {
            systemProperty("browser.remote", "true")
            systemProperty("selenide.remote", "http://${project.property("grid")}:4444/wd/hub")
        }
    }
}
val cucumberRuntime by configurations.creating {
    extendsFrom(configurations["testImplementation"])
}

configurations.all {
    if (name.contains("cucumberRuntime")) {
        attributes.attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class.java, Usage.JAVA_RUNTIME))
    }
}

task("cucumber") {
    dependsOn("assemble")
    dependsOn("compileTestJava")
    doLast {
        javaexec {
            main = "io.cucumber.core.cli.Main"
            classpath = cucumberRuntime + sourceSets.main.get().output + sourceSets.test.get().output
            args = listOf("--plugin", "pretty", "--glue", "com.booking.selenide", "src/test/resources")
        }
    }
}

tasks.withType<BootJar> {
    enabled = false
}
tasks.withType<Jar> {
    enabled = false
}
