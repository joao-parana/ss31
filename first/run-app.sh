#!/bin/bash

set -e

if [ $1 = 'build' ];
then
  mvn clean
  mvn package && rm -rf tomcat8x/webapps/my-calendar*
  cp target/ss31-first-1.0.war tomcat8x/webapps/ss31-first.war
fi
# 
rm -rf tomcat8x/logs/*.log 
rm -rf tomcat8x/logs/*.txt
cd tomcat8x/bin/
./run-tomcat.sh
