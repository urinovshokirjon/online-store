FROM alpine/java:17
COPY target/*.jar online_store.jar
ENTRYPOINT ["java", "-jar", "online_store.jar"]
