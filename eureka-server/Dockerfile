# 1. Adım: OpenJDK tabanlı bir Java ortamı kullan
FROM openjdk:17-jdk-alpine

# 2. Adım: Uygulama dosyalarını /app dizinine kopyala
COPY target/eureka-server-0.0.1-SNAPSHOT.jar /app/eureka-server.jar

# 3. Adım: Çalışma dizinini /app olarak ayarla
WORKDIR /app

EXPOSE 8761
# 4. Adım: Uygulamayı başlat
CMD ["java", "-jar", "eureka-server.jar"]
