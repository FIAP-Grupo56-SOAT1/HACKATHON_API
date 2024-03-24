FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true
EXPOSE 8080
ENV DB_SERVER=localhost
ENV DB_PORT=3306
ENV DB_NAME=timesheetdb
ENV DB_USER=fiap56
ENV DB_PASSWORD=fiap56
ENV DB_ROOT_PASSWORD=fiap56
ENV JWT_SECRET=12345678
ENV TOKEN_EXPIRATION_MINUTES=60
ENV TIME_ZONE="America/Sao_Paulo"
ENV SENDER_MAIL=wilianfiap@gmail.com
ENV SENDER_MAIL_PASSWORD="cczi ddwy seen kygq"
ENV MAIL_HOST=smtp.gmail.com
ENV MAIL_PORT=587
ENV TZ=America/Sao_Paulo
ENTRYPOINT ["java", "-jar","/home/app/target/timesheet-1.0.0-SNAPSHOT.jar"]
