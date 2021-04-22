FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar Pedido-0.0.1-SNAPSHOT
ENTRYPOINT ["java","-jar","/Pedido-0.0.1-SNAPSHOT"]

