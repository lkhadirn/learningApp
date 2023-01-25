plugins {
    java
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.graalvm.buildtools.native") version "0.9.18"
    id("org.flywaydb.flyway") version "9.8.1"
}

flyway {
//	url = "jdbc:postgresql://localhost:5432/testdb"
//	user = "gregtaube"
//	password = ""

    url = "jdbc:postgresql://respiroproduction.postgres.database.azure.com:5432/teoriproven"
    user = "respiro"
    password = "sjong-lerings-baller1"
}


group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_19
java.targetCompatibility = JavaVersion.VERSION_19


configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.flywaydb:flyway-core")
    implementation("org.postgresql:postgresql")
//	implementation ("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.jsoup:jsoup:1.15.3")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
//	implementation("org.springframework.session:spring-session-core")
//	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
//	testImplementation("org.springframework.security:spring-security-test")
//	implementation("org.springframework.boot:spring-boot-starter-security")
}



tasks.withType<Test> {
    useJUnitPlatform()
}
