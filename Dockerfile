
# multi-stage build를 사용하여 Spring Boot 애플리케이션을 Docker 이미지로 패키징

# gralew clean build

FROM openjdk:17-slim as build
WORKDIR /application
ARG JAR_FILE=build/libs/item-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE}  application.jar
RUN java -Djarmode=layertools -jar application.jar extract


FROM openjdk:17-slim
WORKDIR /application
COPY --from=build application/dependencies/ ./
COPY --from=build application/spring-boot-loader/ ./
COPY --from=build application/snapshot-dependencies/ ./
COPY --from=build application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"] 
