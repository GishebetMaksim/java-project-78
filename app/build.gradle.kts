plugins {
    application
    checkstyle
    id("org.sonarqube") version "6.2.0.5505"
    id("jacoco")
}

application {
    mainClass.set("hexlet.code.App")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports { xml.required.set(true)
    }
}

sonar {
    properties {
        property("sonar.projectKey", "GishebetMaksim_java-project-78")
        property("sonar.organization", "gishebetmaksim")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
