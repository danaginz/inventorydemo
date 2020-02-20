FROM openjdk:8
ADD target/inventorydemo-0.0.1-SNAPSHOT.jar inventorydemo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "inventorydemo-0.0.1-SNAPSHOT.jar"]
