#!/bin/sh
mkdir -p out/production/
javac -Xlint:unchecked -cp src/ -d out/production/ src/cfalcione/cs303/Main.java
