#! /bin/sh
echo '即将启动数据同步系统'
java -jar -Xms2048m -Xmx2048m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=256m target/deliverc-1.0.jar
