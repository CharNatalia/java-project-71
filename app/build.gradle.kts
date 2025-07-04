
plugins {
    id("java")
    id("com.github.ben-manes.versions") version "0.51.0"
    application
    checkstyle
    jacoco
    id("org.sonarqube") version "6.2.0.5505"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4.2")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "hexlet.code.App"
}

checkstyle {
    toolVersion = "10.12.4"
    configFile = file("$rootDir/config/checkstyle/checkstyle.xml")
}

sonar {
    properties {
        property("sonar.projectKey", "CharNatalia_java-project-71")
        property("sonar.organization", "charnatalia")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}

sonarqube {
    properties {
        property("sonar.coverage.jacoco.xmlReportPaths", "$buildDir/reports/jacoco/test/jacocoTestReport.xml")
    }
}
