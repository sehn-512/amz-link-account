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
