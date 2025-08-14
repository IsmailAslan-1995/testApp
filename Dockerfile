FROM openjdk:21

COPY testapp-0.0.1-SNAPSHOT.jar testapp.jar

ENTRYPOINT ["java","-jar","/.jar"]