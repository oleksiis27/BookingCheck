plugins {
    id("java")
    id("io.freefair.lombok") version "8.10.2"
    id("io.qameta.allure") version "2.12.0"
}

group = "oleksiiS"
version = "1.0-SNAPSHOT"

val allureVersion = "2.12.0"

repositories {
    mavenCentral()
}

allure {
    report {
        version.set(allureVersion)
    }
    adapter {
        aspectjWeaver.set(true)
        frameworks {
            junit5 {
                adapterVersion.set(allureVersion)
            }
        }
    }
}

dependencies {
    implementation(platform("org.junit:junit-bom:5.10.0"))
    implementation("org.junit.jupiter:junit-jupiter")
    implementation("io.rest-assured:rest-assured:5.4.0")
    implementation("io.rest-assured:json-schema-validator:5.4.0")
    implementation("io.qameta.allure:allure-rest-assured:2.12.0")
    implementation("io.qameta.allure:allure-junit5:2.7.0")
    implementation("org.assertj:assertj-core:3.24.2")
    implementation("com.github.javafaker:javafaker:1.0.2")
    implementation("org.aeonbits.owner:owner:1.0.4")
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("ch.qos.logback:logback-classic:1.4.5")
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        lifecycle {
            events("started", "skipped", "failed", "standard_error", "standard_out")
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.SHORT
        }
    }
}