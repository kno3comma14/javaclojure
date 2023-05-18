#!/bin/sh
# Execute the main class of the Java code
LEINCP=`lein classpath`
MVNCP=`mvn -q exec:exec -Dexec.executable=echo -Dexec.args="%classpath"`
lein javac
java -cp "$LEINCP" knowcomma.core.Knowcomma
