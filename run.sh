#!/bin/bash
mvn clean package exec:java -Dexec.mainClass=fr.guillaumerose.Map -Dexec.args=$1
