#!/bin/sh

JAVA_HOME=/usr/local/jdk1.6.0_45
CLASSPATH=$JAVA_HOME/lib:$JAVA_HOME/jre/lib
MAVEN_HOME=/usr/local/apache-maven-3.0.5
PATH=$MAVEN_HOME/bin:$JAVA_HOME/bin:/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin

case $1 in
	release)
		mvn clean package -Prelease
	;;
	dev)
		mvn clean package -Pdev
	;;
	*)
		cat << HELP
$0 {release|dev}
HELP
	;;
esac
