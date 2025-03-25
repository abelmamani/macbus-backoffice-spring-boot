FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY adapter/target/macbusProyectoFinal.war macbusProyectoFinal.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "macbusProyectoFinal.war"]