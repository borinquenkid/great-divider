plugins {
    id ("java")
    id ("application")
}

group = "com.objectcomputing"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
repositories {
    mavenCentral()
}

dependencies {
    implementation ("com.miglayout:miglayout-swing:11.1")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation ("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("com.objectcomputing.greatdivider.GreatDividerGui")
}