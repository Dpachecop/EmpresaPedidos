# Usamos Amazon Corretto 17, que es super estable y compatible con Mac
FROM amazoncorretto:17

WORKDIR /app

# Copiamos el jar (Asegúrate de que este nombre coincida con el de tu carpeta target)
COPY target/EmpresaPedidos-0.0.1-SNAPSHOT.jar app.jar

# Ejecutamos
ENTRYPOINT ["java", "-jar", "app.jar"]