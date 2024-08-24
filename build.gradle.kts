import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.springframework.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.jetbrains.kotlin.spring)
}

group = "com.github.pedroluis02.springbootsamples"
version = "0.0.1-SNAPSHOT"
description = "SpringBoot v3 Security v6 Sample"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.jetbrains.kotlin.reflect)
    implementation(libs.fasterxml.jackson.kotlin)

    implementation(libs.springframework.boot.starter.web)
    implementation(libs.springframework.boot.starter.security)

    implementation(libs.jsonwebtoken.jjwt.api)
    implementation(libs.jsonwebtoken.jjwt.impl)
    implementation(libs.jsonwebtoken.jjwt.jackson)

    testImplementation(libs.springframework.boot.starter.test)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
