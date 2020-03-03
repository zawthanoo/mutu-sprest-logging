common-logging
-----------------
It is a component/lib for common logging process for `spring-boot` REST api development by using `log4j`. 

 1. Http Request & Response logging
 2. Runtime exception logging
 

Configuration
-------------
1.Configure logger for `mutu.sprest.logging` in `log4j2.xml`

```xml
<AsyncLogger name="mutu.sprest.logging" level="debug">
	<AppenderRef ref="Console" />
</AsyncLogger>
```

sample `log4j2.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration>
	<Appenders>
		<Console name="Console" target="SYSTEM_OUT">
			<JsonLayout properties="false" stacktraceAsString="true" includeStacktrace="true" objectMessageAsJsonObject="true">
			</JsonLayout> 			
 		</Console>		
	</Appenders>
	<Loggers>
		<AsyncLogger name="mutu.sprest.logging" level="debug" additivity="false">
			<AppenderRef ref="Console" />
		</AsyncLogger>
		<AsyncLogger name="your.app.packages" level="debug" additivity="false">
			<AppenderRef ref="Console" />
		</AsyncLogger>
		<AsyncRoot level="error">
			<AppenderRef ref="Console" />
		</AsyncRoot>
	</Loggers>
</Configuration>
```

2.Make sure to do component scan for `mutu.sprest.logging` and `@EnableAspectJAutoProxy` in `spring-boot` application.

```java
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages= {"your.app.packages", "mutu.sprest.logging"})
public class Application extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```
3.In `appllication.yaml`, the following config is **required** where your application is required or not. the value of `log4jThreadContextList` must be empty or CSV. That CSV values is used as http-request's keys to log in every logging process by the feature `ThreadContext` of `log4j`. Check in `Feature` section.

```yml
header-key-list:
  log4jThreadContextList:
  or  
  log4jThreadContextList: HEADER_KEY_1, HEADER_KEY_2, HEADER_KEY_3

```

Feature
--------
Sometime, we need to log and track the value/key in every logging process for every http request. Eg, `correlationId`,`processFlow` are passed as HTTP header among micro-services. If you configure `log4jThreadContextList: correlationId, processFlow`, the `common-logging` component will retrieve the value of http header by configuration keys and put into `ThreadContext`. So, you can configure in `json-layout` in `log4j2.xml` as below.

```xml
<JsonLayout properties="false" stacktraceAsString="true" includeStacktrace="true" eventEol="true" compact="true" objectMessageAsJsonObject="true">
	<KeyValuePair key="correlationId" value="$${ctx:correlationId}"/>
	<KeyValuePair key="processFlow" value="$${ctx:processFlow}"/>
</JsonLayout>
```
So, you will see the `correlationId` and `processFlow` in every logging process.

Integration with ELK (Elasticsearch, Logstash, Kibana)
-------
Integration with `ELK` by using `log4j` `socket` appenders with `jsonlayout` 

 1. Install and run `elasticsearch`
 2. Install and run `kibana`
 3. Install and run `logstash`
 
	logstash.bat -f logstash-log4j2.conf
 
log4j2.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration>
	<Appenders>
		<Socket name="Socket" host="localhost" port="4560" protocol="tcp">
			<JsonLayout properties="false" stacktraceAsString="true" includeStacktrace="true" eventEol="true" compact="true" objectMessageAsJsonObject="true">
				<KeyValuePair key="correlationId" value="$${ctx:correlationId}"/>
				<KeyValuePair key="processFlow" value="$${ctx:processFlow}"/>
				<KeyValuePair key="initrator" value="$${ctx:initrator}"/>
				<KeyValuePair key="logtime" value="$${date:yyyy-MM-dd'T'HH:mm:ss}" />
			</JsonLayout> 			
		</Socket>
		<Console name="Console" target="SYSTEM_OUT">
			<JsonLayout properties="false" stacktraceAsString="true" includeStacktrace="true" eventEol="true" compact="true" objectMessageAsJsonObject="true">
				<KeyValuePair key="correlationId" value="$${ctx:correlationId}"/>
				<KeyValuePair key="processFlow" value="$${ctx:processFlow}"/>
				<KeyValuePair key="initrator" value="$${ctx:initrator}"/>
				<KeyValuePair key="logtime" value="$${date:yyyy-MM-dd'T'HH:mm:ss}" />
			</JsonLayout>				
		</Console>		
	</Appenders>
	<Loggers>
		<AsyncLogger name="com.mutu" level="debug" additivity="false">
			<AppenderRef ref="Console" />
			<AppenderRef ref="Socket" />
		</AsyncLogger>
		<AsyncLogger name="org.apache.catalina.core" level="off" additivity="false">
			<AppenderRef ref="Console" />
		</AsyncLogger>		
		<AsyncRoot level="error">
			<AppenderRef ref="Console" />
		</AsyncRoot>
	</Loggers>
</Configuration>
```

logstash-log4j2.conf

```conf
input {
  tcp {
    port => 4560
	type => "json"
    codec => json {
      charset => "UTF-8"
    }	
  }
}

filter {
	json {
		source => "message"
		skip_on_invalid_json => true
	}
	if [message][payload]{
		mutate {
		    add_field => { "payload" => "%{[message][payload]}"}
			add_field => { "log" => "%{[message][process]}"}
			remove_field => [ "[message]" ]
		}
	}
	if [message] =~ /.*/ {
		mutate {
			rename => { "message" => "log" }
		}
	}
	mutate {
		remove_field => [ "[loggerFqcn]" ]
	}	
}

output {
  elasticsearch {
    hosts => ["localhost:9200"]
    index => "logstash"
  }
}
```

Note: if you would like to see `log4j` json log without any changes, you can remove `logstash` filter part.
 
 

