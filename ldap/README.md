# 

```
Usuário                senha     Observação
---------------------+---------+-----------------------------------------
uid=admin,ou=system    secret    Conta de Administração do Diretório LDAP
admin1@example.com     admin1    Conta de usuário no grupo Admin
parana@example.com     user1     Conta de usuário ordinário
user1@example.com      user1     Conta de usuário ordinário
user2@example.com      user2     Conta de usuário ordinário
```

Usuários podem ser vistos em `src/main/resources/ldif/calendar.ldif` 

Use os comandos abaixo:

```
mvn package && rm -rf tomcat8x/webapps/my-calendar*
cp target/chapter05.01-calendar-1.0.war tomcat8x/webapps/my-calendar.war
cd tomcat8x/bin/
./run-tomcat.sh
```

Por conveniencia foi criada a shel `run-app.sh` que contém este código. Assim basta executar :

```
./run-app.sh
```
 
Esta shell tem o seguinte conteúdo

```
#!/bin/bash

set -e

if [ $1 = 'build' ];
then
  mvn package && rm -rf tomcat8x/webapps/my-calendar*
  cp target/chapter05.01-calendar-1.0.war tomcat8x/webapps/my-calendar.war
fi
# 
cd tomcat8x/bin/
./run-tomcat.sh
```



