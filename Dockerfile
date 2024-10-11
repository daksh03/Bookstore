FROM openjdk:17

WORKDIR /app

COPY /target/bookstore-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8989

ENTRYPOINT [ "java", "-jar", "app.jar" ]