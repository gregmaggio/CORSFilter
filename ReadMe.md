


	<filter>
		<filter-name>CORSFilter</filter-name>
		<filter-class>ca.datamagic.filter.CORSFilter</filter-class>
		<init-param>
			<param-name>allow.origins</param-name>
			<param-value>http://localhost:4200,http://datamagic.ca,https://datamagic.ca</param-value>
		</init-param>
		<init-param>
			<param-name>allow.methods</param-name>
			<param-value>GET,POST,HEAD,OPTIONS,PUT,DELETE</param-value>
		</init-param>
		<init-param>
			<param-name>allow.headers</param-name>
			<param-value>Origin,Content-Type,Accept,Authorization,X-Request-With,location</param-value>
		</init-param>
		<init-param>
			<param-name>allow.credentials</param-name>
			<param-value>true</param-value>
		</init-param>
	</filter>


	<filter-mapping>
		<filter-name>CORSFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

  