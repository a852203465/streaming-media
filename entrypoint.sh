#!/bin/bash

java -version

# 运行JAR
cd /home/app
java -jar -Xms1024m -Xmx2048m -Djava.security.egd=file:/dev/./urandom ./app.jar &

# 运行SRS
cd /home/srs-3.0 && ./srs -c ./srs.conf

