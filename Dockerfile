FROM openjdk:11
ADD target/spring-employee-mysql.jar spring-employee-mysql.jar
EXPOSE 8086
ENTRYPOINT ["java" , "-jar" ,"spring-employee-mysql.jar"]
