# Stage 1: Build ứng dụng với Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Thêm mã nguồn vào image
WORKDIR /app
COPY . .

# Build ứng dụng và tạo file jar
RUN mvn clean package -DskipTests

# Stage 2: Tạo image chạy ứng dụng từ file jar đã build
FROM eclipse-temurin:17-jdk-alpine

# Tạo thư mục để chứa ứng dụng
WORKDIR /app

# Copy file jar từ image build sang image runtime
COPY --from=build /app/target/*.jar app.jar

# Cấu hình cổng để Docker có thể tiếp nhận yêu cầu từ ứng dụng
EXPOSE 8080

# Lệnh để chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
