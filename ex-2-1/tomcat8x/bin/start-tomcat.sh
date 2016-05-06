#!/bin/bash

set -e

export CATALINA_BASE=../
export CATALINA_HOME=../../../tomcat8x/
$CATALINA_HOME/bin/catalina.sh start
