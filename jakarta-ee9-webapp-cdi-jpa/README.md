## Version
- java 18+
- java version "20.0.1" 2023-04-18
- apache-tomcat-10.1.10
- maven-3.8.701.20230209-1606
- mysql-8.0.33

## START MySQL
```
    $ docker run --name mysql -d -e MYSQL_ROOT_PASSWORD=123456 -v /home/kevin/db_data:/var/lib/mysql -p 3306:3306 mysql:5.7
```

# CONFIGURE TOMCAT
```
	// Adding user admin
	$ vi $TOMCAT_HOME/conf/tomcat-users.xml
		<user username="admin" password="admin" roles="admin,manager-gui,manager-script"/>

	// Change Port to 9000
	$  vi $TOMCAT_HOME/conf/server.xml
		<Connector port="9000" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443"
               maxParameterCount="1000"
               />

	// Access out of localhost
	$ vi $TOMCAT_HOME/webapps/manager/META-INF/context.xml
		//Replace
			allow="127\.\d+\.\d+\.\d+|::1|0:0:0:0:0:0:0:1"
		//with option1
			allow=".*"
		//or with option2
			allow="^.*$"

    // Increasing size of *.war if necessary
    $ vi $TOMCAT_HOME/webapps/manager/WEB-INF/web.xml
        <!-- 50MB max -->
        <max-file-size>52428800</max-file-size>
        <max-request-size>52428800</max-request-size>

	// To watch logs TOMCAT
	$ tail -100f $TOMCAT_HOME/logs/catalina.out
	
	// To watch logs TOMCAT Listeners
	$ tail -100f $TOMCAT_HOME/localhost.2023-07-17.log
```

# CONFIGURE TOMCAT WITH MYSQL
```
    // *New library: https://mvnrepository.com/artifact/com.mysql/mysql-connector-j/8.4.0
    $ wget https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.4.0/mysql-connector-j-8.4.0.jar
    $ curl https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.4.0/mysql-connector-j-8.4.0.jar -o mysql-connector-j-8.4.0.jar
    $ cp mysql-connector-j-8.4.0.jar $TOMCAT_HOME/lib


    // *Old library
	// From Maven we get data to download MySQL Java Connector
	<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
	<dependency>
	    <groupId>mysql</groupId>
	    <artifactId>mysql-connector-java</artifactId>
	    <version>8.0.33</version>
	</dependency>

	// Download MySQL Java Conneector
	$ mvn org.apache.maven.plugins:maven-dependency-plugin:2.4:get \
	-DremoteRepositories=http://download.java.net/maven/2 \
	-Dartifact=mysql:mysql-connector-java:8.0.33 \
	-Ddest=./mysql-connector-java.jar
	
	$ cp ./mysql-connector-java.jar $TOMCAT_HOME/lib
```

# CONFIGURE TOMCAT WITH DATASOURCE MySQL
```
	$ mkdir jakarta-ee9-webapp-cdi-jpa/src/main/webapp/META-INF/context.xml
		<Resource name="jdbc/MySQLDB" auth="Container"
			type="javax.sql.DataSource" maxTotal="100" maxIdle="30"
			maxWaitMillis="10000" username="root" password="123456"
			driverClassName="com.mysql.cj.jdbc.Driver"
			url="jdbc:mysql://localhost:3306/enterprise?serverTimezone=Europe/Madrid" />
	$ mkdir jakarta-ee9-webapp-cdi-jpa/src/main/webapp/WEB-INF/web.xml
		<resource-ref>
			<description>DB Connection</description>
			<res-ref-name>jdbc/MySQLDB</res-ref-name>
			<res-type>javax.sql.DataSource</res-type>
			<res-auth>Container</res-auth>
		</resource-ref>
```

# START/STOP TOMCAT
```
	$ sh $TOMCAT_HOME/bin/startup.sh
	$ sh $TOMCAT_HOME/bin/shutdown.sh
```

# CONFIGURE MAVEN to execute on console 
```
    $ vi $MAVEN_HOME/conf/settings.xml
        <pluginGroups>
            <pluginGroup>org.apache.tomcat.maven</pluginGroup>
        </pluginGroups>
```

# CONFIGURE INTELLIJ IDEA 
```
    Watch How To Run in IntelliJ: "Run - IntelliJ.png"
```

# Configure Project in Eclipse
```
	$ mvn eclipse:eclipse
```

# RUN 
```
    $ mvn tomcat7:deploy    # Old: mvn tomcat:deploy
    $ mvn tomcat7:redeploy
    $ mvn tomcat7:undeploy     # Undeploy
```

# DEPLOY
```
	$ curl http://localhost:9000/manager
	
	$ curl http://localhost:9000/jakarta-ee9-webapp-cdi-jpa
	$ curl http://localhost:9000/jakarta-ee9-webapp-cdi-jpa/login.html
	$ curl http://localhost:9000/jakarta-ee9-webapp-cdi-jpa/logout
	$ curl http://localhost:9000/jakarta-ee9-webapp-cdi-jpa/products.html		"redirect to list.jsp"
	$ curl http://localhost:9000/jakarta-ee9-webapp-cdi-jpa/cart/add
	$ curl http://localhost:9000/jakarta-ee9-webapp-cdi-jpa/cart/view 			"redirect to cart.jsp"

	$ curl http://localhost:9000:9000/jakarta-ee9-webapp-cdi-jpa/form.jsp    			"error first invoke /product/form the next link"
	$ curl http://localhost:9000/jakarta-ee9-webapp-cdi-jpa/product/form			"Securized with LoginFilter"
	$ curl http://localhost:9000/jakarta-ee9-webapp-cdi-jpa/product/form?id=1	"Securized with LoginFilter"
	$ curl http://localhost:9000/jakarta-ee9-webapp-cdi-jpa/product/delete?id=1
```

# DEBUG REMOTELY TOMCAT
```
	1) Start Tomcat in debug mode
		$ su // log as root
		$ vi $TOMCAT_HOME/bin/catalina.sh jpda start

		   By default JPDA_ADDRESS is defined as "localhost:8000" in catalina.sh
		   Change to a different port as need or localhost to 0.0.0.0 for remote debug
		   
			if [ -z "$JPDA_ADDRESS" ]; then
				JPDA_ADDRESS="0.0.0.0:8000"
		  	fi
		  	
	  	$ sh $TOMCAT_HOME/bin/catalina.sh jpda start

	2.1) In IntelliJ 
		Click Run > Edit Configurations
		Click + icon on the top-left toolbar
		Click Remote
		Enter a name you want in Name input box
		Enter 8000 in Port input box under Settings section and the Host
		Click Apply, then OK
		Run > Debug..., Click the configuration you just created

	2.2) In Eclipse
		Watch How To Configure in Eclipse: Debug - Eclipse.png
```
