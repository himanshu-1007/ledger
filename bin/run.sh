#!/bin/bash


if [ -z "$1" ]
  then
    echo "Please provide file path"
    exit 1
fi

filePath=$1

if [ ! -f $filePath ]; then
   echo "File $FILE does not exist."
   echo "Exiting the application, please provide a valid file path"
   exit 1
fi

#Not skipping tests for demo purpose
#mvn package -DskipTests
mvn package
java -jar target/navi-executable.jar $filePath
