FROM openjdk:17
MAINTAINER prashamjadhwani
COPY target/hedgenet_backend-0.0.1-SNAPSHOT.jar hedgenet_backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/hedgenet_backend-0.0.1-SNAPSHOT.jar"]


