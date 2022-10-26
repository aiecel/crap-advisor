import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("kapt") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    kotlin("plugin.allopen") version "1.6.21"
}

kapt {
    arguments {
        arg("mapstruct.defaultComponentModel", "spring")
        arg("mapstruct.unmappedTargetPolicy", "ignore")
    }
}

allOpen {
    annotations(
        "javax.persistence.Entity",
        "javax.persistence.MappedSuperclass",
        "javax.persistence.Embedabble"
    )
}

group = "com.aiecel"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

val springBootVersion = "2.7.5"
val openApiVersion = "1.6.12"
val mapstructVersion = "1.5.3.Final"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.4")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.2")
    implementation("org.springdoc:springdoc-openapi-data-rest:$openApiVersion")
    implementation("org.springdoc:springdoc-openapi-ui:$openApiVersion")
    implementation("org.springdoc:springdoc-openapi-kotlin:$openApiVersion")
    implementation("org.mapstruct:mapstruct:$mapstructVersion")
    developmentOnly("org.springframework.boot:spring-boot-devtools:$springBootVersion")
    kapt("org.mapstruct:mapstruct-processor:$mapstructVersion")
    runtimeOnly("org.postgresql:postgresql:42.5.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
