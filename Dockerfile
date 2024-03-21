FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true
EXPOSE 8082
ENV DB_SERVER=localhost DB_PORT=3306 DB_NAME=timesheetdb
ENV DB_USER=fiap56 DB_PASSWORD=fiap56
ENV DB_ROOT_PASSWORD=fiap56
ENV JWT_SECRET=12345678
ENTRYPOINT ["java", "-jar","/home/app/target/timesheet-1.0.0-SNAPSHOT.jar"]
