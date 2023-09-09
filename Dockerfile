FROM openjdk:11

RUN apt-get update \
  && apt-get install -y ca-certificates curl git gnupg dirmngr wget --no-install-recommends \
  && rm -rf /var/lib/apt/lists/*

RUN wget https://dlcdn.apache.org/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.tar.gz \
  && tar -xf apache-maven-3.9.4-bin.tar.gz \
  && cp -r apache-maven-3.9.4/ /opt/apache-maven-3.9.4

ENV PATH="/opt/apache-maven-3.9.4/bin:${PATH}"
WORKDIR /meucafofo
RUN git clone https://github.com/meucafofo/projeto-backend.git
WORKDIR /meucafofo/projeto-backend
RUN echo $PATH \ 
  && mvn clean package -Dmaven.test.skip=true \
  && cp target/meucafofo-servico*.jar /meucafofo/app.jar
ENTRYPOINT ["java","-jar","/meucafofo/app.jar"]