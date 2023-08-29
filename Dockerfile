FROM openjdk:17
EXPOSE 8080
COPY /target/moradia-estudantil*.jar moradia-estudantil.jar
ENTRYPOINT ["java","-jar","/moradia-estudantil.jar"]