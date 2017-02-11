# Projeto PRTASO

### Criação do datasource no Wildfly10

```sh
$JBOSS_HOME/bin/jboss-cli.sh

module add --name=org.postgresql --slot=main --resources=/Users/marcelo/.m2/repository/org/postgresql/postgresql/9.4.1211/postgresql-9.4.1211.jar --dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgresql",driver-class-name=org.postgresql.Driver)
data-source add --jndi-name=java:/prtasoDS --name=prtasoDS --connection-url=jdbc:postgresql://localhost:5432/prtaso --driver-name=postgres --user-name=postgres --password=123456
```

### Migrar Base de Dados

```
mvn flyway:migrate
```

###Criar Usuário:

Post para http://localhost:8080/prtaso/api/user

```sh
curl --request POST \
  --url http://localhost:8080/prtaso/api/user \
  --header 'content-type: application/json' \
  --data '{"name":"Marcelo Ortiz de Santana",\n"username":"marcelo",\n"password": "123456"}'

```

###Login:

Post para http://localhost:8080/prtaso/api/login

```sh
curl --request POST \
  --url http://localhost:8080/prtaso/api/login \
  --header 'content-type: application/json' \
  --data '{"username":"marcelo","password": "123456"}'  
  
```

###Criação de tópicos:

Post para http://localhost:8080/prtaso/api/topic

```sh
curl --request POST \
  --url http://localhost:8080/prtaso/api/topic \
  --header 'content-type: application/json' \
  --data '{"name": "Teste", "message": "Mensagem teste", "sendDate":"2007-12-03T10:15:30.00Z"}'  
```

