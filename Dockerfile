FROM eclipse-temurin:11
WORKDIR /app
COPY target/springboot-ecshop-rest-api-0.0.1-SNAPSHOT.jar /app/springboot-ecshop-api.jar
ENTRYPOINT ["java","-jar","springboot-ecshop-api.jar"]