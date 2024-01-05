FROM bellsoft/liberica-openjdk-alpine:17
ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY build/libs/*-ms.jar app.jar
CMD ["java","-jar","/app.jar"]
EXPOSE 8084/tcp
