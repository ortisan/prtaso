# Projeto PRTASO

### Criação do datasource no Wildfly10


UNIX
```sh
$JBOSS_HOME/bin/jboss-cli.sh

module add --name=org.postgresql --slot=main --resources=/Users/marcelo/.m2/repository/org/postgresql/postgresql/9.4.1211/postgresql-9.4.1211.jar --dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgresql",driver-class-name=org.postgresql.Driver)
data-source add --jndi-name=java:/prtasoDS --name=prtasoDS --connection-url=jdbc:postgresql://localhost:5432/prtaso --driver-name=postgres --user-name=postgres --password=123456
```

WINDOWS
```sh
%JBOSS_HOME%/bin/jboss-cli.bat

module add --name=org.postgresql --slot=main --resources=postgresql-9.4.1211.jar --dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgresql",driver-class-name=org.postgresql.Driver)
data-source add --jndi-name=java:/prtasoDS --name=prtasoDS --connection-url=jdbc:postgresql://localhost:5432/prtaso --driver-name=postgres --user-name=postgres --password=123456
```

### Migrar Base de Dados

```
mvn flyway:migrate
```

##Usuários

##Criar Usuário:

Post para http://localhost:8080/prtaso/api/users

```sh
curl --request POST --url http://localhost:8080/prtaso/api/users --header 'content-type: application/json' --data '{"name":"Marcelo Ortiz de Santana","username":"marcelo","password": "123456"}'
```

### Obter Todos

Get para http://localhost:8080/prtaso/api/users

```sh
curl --request GET --url http://localhost:8080/prtaso/api/users --header 'content-type: application/json'
```


### Por id

Get para http://localhost:8080/prtaso/api/users/:slug

```sh
curl --request GET --url http://localhost:8080/prtaso/api/users/1 --header 'content-type: application/json'
```

###Login:

Post para http://localhost:8080/prtaso/api/signin

```sh
curl --request POST --url http://localhost:8080/prtaso/api/signin --header 'content-type: application/json' --data '{"username":"marcelo","password": "123456"}'  
```

##Topicos

###Criar tópicos:

Post para http://localhost:8080/prtaso/api/topics

```sh
curl --request POST \
  --url http://localhost:8080/prtaso/api/topics \
  --header 'content-type: application/json' \
  --data '{"name": "Teste", "message": "Mensagem teste", "sendDate":"2007-12-03T10:15:30.00Z"}'  
```

### Obter Tópicos

Get para http://localhost:8080/prtaso/api/topics

```sh
curl --request GET --url http://localhost:8080/prtaso/api/topics --header 'content-type: application/json'
```

## Recursos

### [Fluxo de login do twitter](https://dev.twitter.com/web/sign-in/implementing)

https://developers.facebook.com/docs/apis-and-sdks#third-party-sdks
https://developers.facebook.com/docs/pages/publishing
