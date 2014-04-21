#!/bin/bash
javac *.java
rmiregistry -J-Djava.net.preferIPv4Stack=true 54322 &
java -Djava.security.policy=servidor.permisos -Djava.net.preferIPv4Stack=true -Djava.rmi.server.hostname=127.0.0.1 ChatServer localhost 54321 54322
