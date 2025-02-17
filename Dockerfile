FROM openjdk:8-jre-slim

WORKDIR /app

# 复制应用文件
COPY target/tethys-1.0-SNAPSHOT.jar /app/

EXPOSE 18080

CMD ["java", "-jar", "/app/tethys-1.0-SNAPSHOT.jar"]