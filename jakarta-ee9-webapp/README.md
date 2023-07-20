# Tools
```
	- apache-tomcat-10.1.10
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
		//with
			allow=".*"

	// To watch logs TOMCAT
	$ tail -100f $TOMCAT_HOME/logs/catalina.out

	// To watch logs TOMCAT Listeners
	$ tail -100f $TOMCAT_HOME/localhost.2023-07-17.log
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
	$ sh $TOMCAT_HOME/bin/startup.sh
	$ mvn tomcat7:redeploy
```

# DEPLOY
```
	$ curl http://localhost:8080/manager
	
	$ curl http://localhost:8080/webapp/
	$ curl http://localhost:8080/webapp/info
```
