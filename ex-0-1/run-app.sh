#!/bin/bash

set -e

APP_NAME=ss31-ex-0-1
rm -rf tomcat8x/work/* ; rm -rf tomcat8x/temp/* ; rm -rf tomcat8x/logs/* ;rm -rf tomcat8x/webapps/$APP_NAME*
if [ $1 = 'build' ];
then
  mvn clean
  mvn package && rm -rf tomcat8x/webapps/$APP_NAME*
  cp target/$APP_NAME-1.0.war tomcat8x/webapps/$APP_NAME.war
fi
# 
rm -rf tomcat8x/logs/*.log 
rm -rf tomcat8x/logs/*.txt
cd tomcat8x/bin/
./run-tomcat.sh

