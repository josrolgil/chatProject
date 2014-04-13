#!/bin/bash
javac *.java
java -Djava.security.policy=cliente.permisos -Djava.net.preferIPv4Stack=true -Djava.rmi.server.hostname=127.0.0.1  ChatClient localhost 54322
