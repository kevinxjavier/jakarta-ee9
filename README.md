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
```

# Run 
```
	$ java -jar target/jakarta-ee9-0.0.1-SNAPSHOT.jar
```