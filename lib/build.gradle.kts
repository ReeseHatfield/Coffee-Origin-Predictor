plugins {
    `java-library`
    application

}

application {
    mainClass.set("predictor.Driver")
}

repositories {
    mavenCentral()
}

dependencies {
    api("org.apache.commons:commons-math3:3.6.1")
    implementation("com.google.guava:guava:31.1-jre")
    implementation("nz.ac.waikato.cms.weka:SMOTE:1.0.3")
    implementation("nz.ac.waikato.cms.weka:weka-stable:3.8.6")
    implementation("nz.ac.waikato.cms.weka:tiny-weka:3.9.15955")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}
