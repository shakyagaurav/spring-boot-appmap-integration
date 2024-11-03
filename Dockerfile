FROM openjdk:17-oracle
VOLUME /tmp
COPY target/spring-boot-appmap-integration-0.0.1-SNAPSHOT.jar spring-boot-appmap-integration-.jar
ENTRYPOINT ["java","-jar","/spring-boot-appmap-integration-.jar"]