db: myFlywayConfig.conf
	mvn clean flyway:migrate -Dflyway.configFiles=myFlywayConfig.conf