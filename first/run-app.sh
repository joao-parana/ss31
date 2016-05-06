#!/bin/bash

set -e

if [ $1 = 'build' ];
then
  mvn package && rm -rf tomcat8x/webapps/my-calendar*
  cp target/ss31-first-1.0.war tomcat8x/webapps/my-calendar.war
fi
# 
cd tomcat8x/bin/
./run-tomcat.sh
