FROM amazoncorretto:21.0.1 as build

WORKDIR /app

COPY . .

RUN ./mvnw --no-transfer-progress clean package -DskipTests=true -Dcheckstyle.skip=true

FROM amazoncorretto:21.0.1

COPY --from=build /app/target/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
