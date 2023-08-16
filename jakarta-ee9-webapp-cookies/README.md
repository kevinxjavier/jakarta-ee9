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
    $ mvn tomcat7:redeploy
```

# DEPLOY
```
	$ curl http://localhost:8080/manager

	$ curl http://localhost:8080/jakarta-ee9-webapp-cookies
	$ curl http://localhost:8080/jakarta-ee9-webapp-cookies/login.html
	$ curl http://localhost:8080/jakarta-ee9-webapp-cookies/logout
	$ curl http://localhost:8080/jakarta-ee9-webapp-cookies/products.html
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