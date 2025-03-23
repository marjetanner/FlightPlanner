# FlightPlanner

# Projekti seadistamine IntelliJ IDEA-s

Spring Boot + Vue projekt IntelliJ IDEA-s. 

---

## 1. Klooni projekt GitHubist
Kui sul pole veel projekti arvutis, ava terminal ja klooni see:

Ava Gradle paneel (paremal).

Vajuta "Reload All Gradle Projects" nuppu.

Kui Gradle pole installitud, laadi alla: https://gradle.org/install/

Tee gradlew failid käivitatavaks:chmod +x gradlew
./gradlew build
IntelliJ-s mine File > Project Structure > Project Settings > SDKs.

Vali JDK 17.

# H2 andmebaasi seadistamine

Lisa src/main/resources/application.properties faili juhul kui see sealt puudb. Kui olemas siis muuda username ja password:
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Käivita Spring Boot rakendus ja ava H2 Console:
http://localhost:8080/h2-console

# Vue.js frontend seadistamine
Mine front endi kausta: flight_front

Kontrolli, kas Node.js ja npm on installitud:node -v
npm -v
# Paigalda sõltuvused:npm install
# Käivita Vue: npm run dev

# Käivita Spring Boot backend: terminalis ./gradlew bootRun  Gradle või vajuta Run. 
______________________________________________
# Mine http://localhost:8080/
