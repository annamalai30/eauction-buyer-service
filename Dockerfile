FROM amazoncorretto:11-alpine-jdk
ADD target/eauction-buyer-service-0.0.1-SNAPSHOT.jar eauction-buyer-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/eauction-buyer-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081