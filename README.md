common-logging
-----------------
It is a component/lib for common logging process for `spring-boot` REST api development by using `log4j`. 

 1. Http Request & Response logging
 2. Run time exception logging
 
Maven Config
-------------
add new repo config in `setting.xml` under in `.m2` directory.
	
	<profile>
		<id>Mule</id>
		<activation>
			<activeByDefault>true</activeByDefault>
		</activation>
		<repositories>
			<repository>
				<id>MuleRepository</id>
				<name>MuleRepository</name>
				<url>http://ec2-18-136-39-77.ap-southeast-1.compute.amazonaws.com:8081/repository/maven-public/</url>
				<layout>default</layout>
				<releases>
					<enabled>true</enabled>
				</releases>
				<snapshots>
					<enabled>true</enabled>
				</snapshots>
			</repository>
		</repositories>
	</profile>

pom.xml config


	<dependency>
	  <groupId>io.wavemoney</groupId>
	  <artifactId>mutu-sprest-logging</artifactId>
	  <version>1.0.0</version>
	</dependency>		


	