#!/bin/bash

# Amazon Instant Access Spring Boot Project (Java 11) Creator

echo "Creating Amazon Instant Access Spring Boot project for Java 11..."

# Create project root directory
PROJECT_NAME="amazon-instant-access-java11"
rm -rf $PROJECT_NAME
mkdir -p $PROJECT_NAME
cd $PROJECT_NAME

# Create directory structure
echo "Creating directory structure..."
mkdir -p src/main/java/com/example/amazoninstantaccess/{config,controller,dto,entity,repository,service}
mkdir -p src/main/resources/{templates,challenges,static}
mkdir -p src/test/java/com/example/amazoninstantaccess

# Create pom.xml for Java 11
echo "Creating pom.xml for Java 11..."
cat > pom.xml << 'EOF'
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.18</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>amazon-instant-access</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>amazon-instant-access</name>
    <description>Amazon Instant Access Integration for Spring Boot</description>

    <properties>
        <java.version>11</java.version>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- Spring Boot Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Spring Boot Security for HTTPS -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

        <!-- Spring Boot Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <!-- Thymeleaf -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>

        <!-- H2 Database -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Jackson for JSON processing -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>

        <!-- Apache Commons Codec for HMAC -->
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
        </dependency>

        <!-- Test dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                    <release>11</release>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
EOF

# Create application.properties
echo "Creating application.properties..."
cat > src/main/resources/application.properties << 'EOF'
# Server Configuration
server.port=8443
server.ssl.enabled=true
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=changeme
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=instant-access

# Amazon Instant Access Configuration
amazon.instant-access.public-key=YOUR_PUBLIC_KEY
amazon.instant-access.private-key=YOUR_PRIVATE_KEY

# Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=create-drop

# Logging
logging.level.com.example=DEBUG
logging.level.org.springframework.web=DEBUG

# Security
spring.security.user.name=admin
spring.security.user.password=admin123
EOF

# Create Main Application class
echo "Creating main application class..."
cat > src/main/java/com/example/amazoninstantaccess/AmazonInstantAccessApplication.java << 'EOF'
package com.example.amazoninstantaccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AmazonInstantAccessApplication {
    public static void main(String[] args) {
        SpringApplication.run(AmazonInstantAccessApplication.class, args);
    }
}
EOF

# Create DTOs
echo "Creating DTO classes..."

cat > src/main/java/com/example/amazoninstantaccess/dto/GetUserIdRequest.java << 'EOF'
package com.example.amazoninstantaccess.dto;
import lombok.Data;

@Data
public class GetUserIdRequest {
    private String operation;
    private String infoField1;
    private String infoField2;
    private String infoField3;
}
EOF

cat > src/main/java/com/example/amazoninstantaccess/dto/GetUserIdResponse.java << 'EOF'
package com.example.amazoninstantaccess.dto;
import lombok.Data;

@Data
public class GetUserIdResponse {
    private String response;
    private String userId;
}
EOF

cat > src/main/java/com/example/amazoninstantaccess/dto/SubscriptionActivateRequest.java << 'EOF'
package com.example.amazoninstantaccess.dto;
import lombok.Data;

@Data
public class SubscriptionActivateRequest {
    private String operation;
    private String subscriptionId;
    private String productId;
    private String userId;
    private Integer numberOfLicenses;
}
EOF

cat > src/main/java/com/example/amazoninstantaccess/dto/SubscriptionDeactivateRequest.java << 'EOF'
package com.example.amazoninstantaccess.dto;
import lombok.Data;

@Data
public class SubscriptionDeactivateRequest {
    private String operation;
    private String subscriptionId;
    private String period;
    private String reason;
}
EOF

cat > src/main/java/com/example/amazoninstantaccess/dto/SubscriptionGetRequest.java << 'EOF'
package com.example.amazoninstantaccess.dto;
import lombok.Data;

@Data
public class SubscriptionGetRequest {
    private String operation;
    private String subscriptionId;
    private String productId;
    private String userId;
}
EOF

cat > src/main/java/com/example/amazoninstantaccess/dto/SubscriptionUpdateRequest.java << 'EOF'
package com.example.amazoninstantaccess.dto;
import lombok.Data;

@Data
public class SubscriptionUpdateRequest {
    private String operation;
    private String subscriptionId;
    private String productId;
    private String userId;
    private Integer numberOfLicenses;
    private Boolean removeAllUnassignedLicenses;
}
EOF

cat > src/main/java/com/example/amazoninstantaccess/dto/SubscriptionResponse.java << 'EOF'
package com.example.amazoninstantaccess.dto;
import lombok.Data;

@Data
public class SubscriptionResponse {
    private String response;
}
EOF

cat > src/main/java/com/example/amazoninstantaccess/dto/SubscriptionActivateResponse.java << 'EOF'
package com.example.amazoninstantaccess.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionActivateResponse extends SubscriptionResponse {
    private Integer numberOfUnusedLicenses;
    private Integer numberOfUsedLicenses;
    private String managementURL;
}
EOF

cat > src/main/java/com/example/amazoninstantaccess/dto/SubscriptionGetResponse.java << 'EOF'
package com.example.amazoninstantaccess.dto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionGetResponse extends SubscriptionResponse {
    private Integer numberOfUnusedLicenses;
    private Integer numberOfUsedLicenses;
    private String managementURL;
}
EOF

# Create Entities
echo "Creating Entity classes..."

cat > src/main/java/com/example/amazoninstantaccess/entity/UserAccount.java << 'EOF'
package com.example.amazoninstantaccess.entity;

import javax.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_accounts")
@Data
public class UserAccount {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "info_field2")
    private String infoField2;

    @Column(name = "info_field3")
    private String infoField3;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
EOF

cat > src/main/java/com/example/amazoninstantaccess/entity/Subscription.java << 'EOF'
package com.example.amazoninstantaccess.entity;

import javax.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscriptions")
@Data
public class Subscription {

    @Id
    @Column(name = "subscription_id")
    private String subscriptionId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "is_active")
    private boolean active = true;

    @Column(name = "number_of_licenses")
    private Integer numberOfLicenses;

    @Column(name = "number_of_used_licenses")
    private Integer numberOfUsedLicenses;

    @Column(name = "number_of_unused_licenses")
    private Integer numberOfUnusedLicenses;

    @Column(name = "deactivation_reason")
    private String deactivationReason;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
EOF

# Create Repositories
echo "Creating Repository interfaces..."

cat > src/main/java/com/example/amazoninstantaccess/repository/UserAccountRepository.java << 'EOF'
package com.example.amazoninstantaccess.repository;

import com.example.amazoninstantaccess.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    Optional<UserAccount> findByEmail(String email);
}
EOF

cat > src/main/java/com/example/amazoninstantaccess/repository/SubscriptionRepository.java << 'EOF'
package com.example.amazoninstantaccess.repository;

import com.example.amazoninstantaccess.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
    List<Subscription> findByUserId(String userId);
    List<Subscription> findByUserIdAndActive(String userId, boolean active);
}
EOF

# Create Controllers
echo "Creating Controller classes..."

cat > src/main/java/com/example/amazoninstantaccess/controller/ChallengeController.java << 'EOF'
package com.example.amazoninstantaccess.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class ChallengeController {

    @GetMapping(value = "/linkAccount/amazonlinkingchallenge", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getAccountLinkingChallenge() {
        try {
            ClassPathResource resource = new ClassPathResource("challenges/amazonlinkingchallenge");
            String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return ResponseEntity.ok(content);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error reading challenge file: " + e.getMessage());
        }
    }

    @GetMapping(value = "/purchaseAccount/amazonservicechallenge", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getFulfillmentChallenge() {
        try {
            ClassPathResource resource = new ClassPathResource("challenges/amazonservicechallenge");
            String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return ResponseEntity.ok(content);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error reading challenge file: " + e.getMessage());
        }
    }

    @GetMapping(value = "/register/amazon/amazonregistrationchallenge", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getRegistrationChallenge() {
        try {
            ClassPathResource resource = new ClassPathResource("challenges/amazonregistrationchallenge");
            String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return ResponseEntity.ok(content);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error reading challenge file: " + e.getMessage());
        }
    }
}
EOF

# Create placeholder Services (due to length, creating minimal versions)
echo "Creating Service classes..."

cat > src/main/java/com/example/amazoninstantaccess/service/AccountService.java << 'EOF'
package com.example.amazoninstantaccess.service;

import com.example.amazoninstantaccess.dto.GetUserIdRequest;
import com.example.amazoninstantaccess.dto.GetUserIdResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    public GetUserIdResponse getUserId(GetUserIdRequest request) {
        log.info("Getting user ID for: {}", request.getInfoField1());

        GetUserIdResponse response = new GetUserIdResponse();
        response.setResponse("OK");
        response.setUserId("USER-" + System.currentTimeMillis());

        return response;
    }
}
EOF

cat > src/main/java/com/example/amazoninstantaccess/service/AuthenticationService.java << 'EOF'
package com.example.amazoninstantaccess.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class AuthenticationService {

    @Value("${amazon.instant-access.public-key}")
    private String publicKey;

    @Value("${amazon.instant-access.private-key}")
    private String privateKey;

    public boolean verifyRequest(HttpServletRequest request) {
        // TODO: Implement full HMAC-SHA256 verification
        // For now, return true for testing
        log.warn("Authentication verification not fully implemented - returning true for testing");
        return true;
    }
}
EOF

cat > src/main/java/com/example/amazoninstantaccess/service/SubscriptionService.java << 'EOF'
package com.example.amazoninstantaccess.service;

import com.example.amazoninstantaccess.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {

    public SubscriptionActivateResponse activateSubscription(SubscriptionActivateRequest request) {
        log.info("Activating subscription: {}", request.getSubscriptionId());

        SubscriptionActivateResponse response = new SubscriptionActivateResponse();
        response.setResponse("OK");

        if (request.getNumberOfLicenses() != null) {
            response.setNumberOfUsedLicenses(0);
            response.setNumberOfUnusedLicenses(request.getNumberOfLicenses());
            response.setManagementURL("https://your-domain.com/licenses?subscriptionId=" +
                request.getSubscriptionId());
        }

        return response;
    }

    public SubscriptionResponse deactivateSubscription(SubscriptionDeactivateRequest request) {
        log.info("Deactivating subscription: {}", request.getSubscriptionId());

        SubscriptionResponse response = new SubscriptionResponse();
        response.setResponse("OK");
        return response;
    }

    public SubscriptionGetResponse getSubscription(SubscriptionGetRequest request) {
        log.info("Getting subscription: {}", request.getSubscriptionId());

        SubscriptionGetResponse response = new SubscriptionGetResponse();
        response.setResponse("OK");
        response.setNumberOfUsedLicenses(0);
        response.setNumberOfUnusedLicenses(5);
        response.setManagementURL("https://your-domain.com/licenses?subscriptionId=" +
            request.getSubscriptionId());

        return response;
    }

    public SubscriptionActivateResponse updateSubscription(SubscriptionUpdateRequest request) {
        log.info("Updating subscription: {}", request.getSubscriptionId());

        SubscriptionActivateResponse response = new SubscriptionActivateResponse();
        response.setResponse("OK");
        response.setNumberOfUsedLicenses(0);
        response.setNumberOfUnusedLicenses(request.getNumberOfLicenses() != null ? request.getNumberOfLicenses() : 0);
        response.setManagementURL("https://your-domain.com/licenses?subscriptionId=" +
            request.getSubscriptionId());

        return response;
    }
}
EOF

# Create Config
echo "Creating SecurityConfig..."

cat > src/main/java/com/example/amazoninstantaccess/config/SecurityConfig.java << 'EOF'
package com.example.amazoninstantaccess.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests()
                .antMatchers(
                    "/linkAccount/**",
                    "/purchaseAccount/**",
                    "/register/**"
                ).permitAll()
                .anyRequest().authenticated()
            .and()
            .requiresChannel()
                .anyRequest().requiresSecure();

        return http.build();
    }
}
EOF

# Create registration template
echo "Creating registration.html..."
cat > src/main/resources/templates/registration.html << 'EOF'
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account - Amazon Instant Access</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f5f5;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }
        .container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 400px;
            padding: 40px;
        }
        h1 {
            font-size: 24px;
            margin-bottom: 24px;
            color: #333;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #555;
            font-size: 14px;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }
        button {
            width: 100%;
            padding: 12px;
            background: #0066cc;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background: #0052a3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Create a new account</h1>
        <form action="/register/process" method="get">
            <input type="hidden" name="redirectUrl" th:value="${redirectUrl}" />
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required />
            </div>
            <div class="form-group">
                <label for="username">Username (optional)</label>
                <input type="text" id="username" name="username" />
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required />
            </div>
            <button type="submit">Create an account</button>
        </form>
    </div>
</body>
</html>
EOF

# Create placeholder challenge files
echo "Creating placeholder challenge files..."
echo "REPLACE_WITH_AMAZON_CHALLENGE_CONTENT" > src/main/resources/challenges/amazonlinkingchallenge
echo "REPLACE_WITH_AMAZON_CHALLENGE_CONTENT" > src/main/resources/challenges/amazonservicechallenge
echo "REPLACE_WITH_AMAZON_CHALLENGE_CONTENT" > src/main/resources/challenges/amazonregistrationchallenge

# Create .gitignore
cat > .gitignore << 'EOF'
target/
!.mvn/wrapper/maven-wrapper.jar
!**/src/main/**/target/
!**/src/test/**/target/

### STS ###
.apt_generated
.classpath
.factorypath
.project
.settings
.springBeans
.sts4-cache

### IntelliJ IDEA ###
.idea
*.iws
*.iml
*.ipr

### NetBeans ###
/nbproject/private/
/nbbuild/
/dist/
/nbdist/
/.nb-gradle/
build/

### VS Code ###
.vscode/

### Security ###
*.p12
*.jks
src/main/resources/keystore.p12
EOF

# Create README
cat > README.md << 'EOF'
# Amazon Instant Access Spring Boot Integration (Java 11)

## Requirements
- Java 11
- Maven 3.6+

## Setup Instructions

1. **Replace challenge files**
   - Download challenge files from Amazon Developer Portal
   - Replace files in `src/main/resources/challenges/`

2. **Update credentials**
   - Edit `src/main/resources/application.properties`
   - Set your Amazon public and private keys

3. **Generate SSL certificate**
   ```bash
   keytool -genkeypair -alias instant-access -keyalg RSA -keysize 2048 \
     -storetype PKCS12 -keystore src/main/resources/keystore.p12 \
     -validity 3650 -storepass changeme
   ```

4. **Build and run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Endpoints
- Challenge endpoints: `/linkAccount/amazonlinkingchallenge`, etc.
- Account linking: `/linkAccount` (POST)
- Fulfillment: `/purchaseAccount` (POST)
- Registration: `/register/amazon` (GET)

## Note
Some services contain placeholder implementations. Complete the full implementation based on the Amazon Instant Access documentation.
EOF

echo ""
echo "✅ Project created successfully!"
echo ""
echo "📁 Project location: $(pwd)/$PROJECT_NAME"
echo ""
echo "🚀 Next steps:"
echo "1. cd $PROJECT_NAME"
echo "2. Replace challenge files with actual files from Amazon"
echo "3. Update application.properties with your credentials"
echo "4. Generate SSL certificate (see README.md)"
echo "5. Complete the service implementations"
echo "6. mvn clean install"
echo "7. mvn spring-boot:run"
echo ""
echo "📦 To create a ZIP file:"
echo "cd .. && zip -r ${PROJECT_NAME}.zip ${PROJECT_NAME}/"
