#!/bin/sh

apache_path=/home/cdot/software/apache-tomcat-8.5.41
rm -rf build dist 
ant -f build.xml
cp dist/* $apache_path/webapps
cd $apache_path/bin
././shutdown.sh
sleep 3
./startup.sh
