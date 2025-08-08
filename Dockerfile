FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/despesas-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9090
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
