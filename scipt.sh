#!/bin/sh

apache_path=/usr/local/tomcat
rm -rf build dist 
ant -f build.xml
cp dist/* $apache_path/webapps
cd $apache_path/bin
./shutdown.sh
sleep 3
./startup.sh
