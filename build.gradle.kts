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
    implementation("com.jgoodies:jgoodies-forms:1.8.0")
    implementation("com.jgoodies:jgoodies-common:1.8.1")


    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation ("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-junit-jupiter:5.6.0")


}

tasks.test {
    useJUnitPlatform()
}

application {
//    mainClass.set("com.objectcomputing.greatdivider.GreatDividerGui")
    mainClass.set("com.objectcomputing.jgoodies.GreatDividerJGoodies")
}