FROM openjdk:17

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml lombok.config ./
RUN ./mvnw dependency:go-offline

COPY src ./src
COPY .git/ ./.git/

CMD ["./mvnw", "spring-boot:run"]