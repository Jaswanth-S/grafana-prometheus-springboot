This POC is how to monitor spring-boot application using grafana and promethues

-------------- Steps involved to check the steup  -------------

1)sample springboot application which connects to mongodb
	
 
 ******  main implementation in springboot application ******
	
	a)dependencies to be added in pom.xml

	 	 <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>

		<dependency>
			<groupId>io.micrometer</groupId>
			<artifactId>micrometer-registry-prometheus</artifactId>
		</dependency>
	
	b)add following line in application.properties file
		
	
		management.endpoints.web.exposure.include=*	 



 ******* test the working of springboot application *******

	a) start application using command -- mvn spring-boot:run
	b) start monngodb using command -- mongo
	
	check the end point --- http://localhost:8080/api/v1/users 

	c) now check http://localhost:8080/actuator
		we can see promethues in the result under prometheus as key

 	d)now check http://localhost:8080/actuator/prometheus	
		Opening it, you will see data formatted specific for Prometheus.
		This shows prometheus is correctly picked up like metrics and health from actuator
		

2)Let's configure Prometheus

	a) create Prometheus.yml with below content

		global:
  			scrape_interval: 10s

		scrape_configs:
 		      - job_name: 'spring_micrometer'
    			metrics_path: '/actuator/prometheus'
   			scrape_interval: 5s
    			static_configs:
      				- targets: ['172.23.239.59:8080']


	Give your system ip address in my case it is 172.23.239.59, Since we are using Docker to run Prometheus, it will be 		running in a Docker network that won't understand localhost
	
	b) run the below command

		docker run -d -p 9090:9090 -v $PWD/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus
			 
	c) now open  http://localhost:9090 , you can see the prometheus dashboard

	  open	http://localhost:9090/targets, you can see springboot appplication up and running

3)Let's configure grafana


	a) Let's start off by running Grafana using Docker

		docker run -d -p 3000:3000 grafana/grafana

	b)If you visit http://localhost:3000, you will be redirected to a login page:
		
		use admin as username and password

	c)create datasource (prometheus) and add prometheus dashboard url 
		(http://172.23.239.59:9090) as url and add server/browser in Whitelisted Cookies

	d)then click Save & Test, we can see toaster message Data Source is working 

	e)now we can add JVM dashboard and can configure 
		
	we can add any type of dashboard

