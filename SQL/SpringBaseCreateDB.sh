#!/bin/sh

export DERBY_HOME=${JAVA_HOME}/db/

echo ${DERBY_HOME}

java -jar ${DERBY_HOME}/lib/derbyrun.jar ij SpringBaseCreateSQL.sql
