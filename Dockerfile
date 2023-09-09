FROM maven:3.9.4-eclipse-temurin-11 as compile
WORKDIR meucafofo
RUN git clone https://github.com/meucafofo/projeto-backend.git
COPY projeto-backend/ /meucafofo/.
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:11 as exec-ENV
WORKDIR meucafofo
COPY /meucafofo/target/meucafofo-servico*.jar /meucafofo/app.jar
ENTRYPOINT ["java","-jar","/meucafofo/app.jar"]