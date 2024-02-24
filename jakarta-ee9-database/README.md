## Version
- java 18+ 
- java version "20.0.1" 2023-04-18
- apache-tomcat-10.1.10
- maven-3.8.701.20230209-1606
- mysql-8.0.33

# Generate
```
	$ curl https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.1.0&packaging=jar&jvmVersion=17&groupId=com.kevinpina&artifactId=jakarta-ee9&name=jJakarta EE9&description=Demo%20project%20for%20Spring%20Boot%20with%20JDBC%20and%20JPA&packageName=com.kevinpina&dependencies=mysql
```

# Create Database enterprise
```
    exec script: init_script.sql
```

# Compile
```
	$ mvn clean compile package
	$ mvn clean compile package -DskipTests
```

# Run 
```
	$ java -jar target/jakarta-ee9-0.0.1-SNAPSHOT.jar
```
