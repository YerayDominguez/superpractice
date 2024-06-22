# Fase de compilación
# Usa una imagen de Maven con una versión compatible con tu proyecto
FROM maven:3.9-eclipse-temurin-21-jammy AS build

# Copia el código fuente y el archivo POM de tu proyecto al contenedor
COPY src /home/app/src
COPY pom.xml /home/app

# Compila tu aplicación
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Fase de ejecución
# Usa una imagen de OpenJDK compatible con Java 21 para ejecutar tu aplicación
FROM openjdk:21-slim

# Copia el artefacto compilado desde la fase de compilación
COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar

# Expone el puerto en el que tu aplicación escuchará
EXPOSE 8080

# Define el comando para iniciar tu aplicación
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
