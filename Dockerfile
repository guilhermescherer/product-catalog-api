FROM openjdk:17-oracle

WORKDIR /productcatalog

COPY target/*.jar /productcatalog/app.jar

EXPOSE 8080

CMD java -XX:+UseContainerSupport -Xmx512m -jar app.jar --server.port=$PORT