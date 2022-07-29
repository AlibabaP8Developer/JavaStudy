#!/bin/sh
echo =================================
echo 自动化部署脚步启动
echo =================================

echo 停止原来运行的工程
APP_NAME=spring-boot-tool-1.0-SNAPSHOT

tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Stop Process...'
    kill -15 $tpid
fi
sleep 2
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Kill Process!'
    kill -9 $tpid
else
    echo 'Stop Success!'
fi

echo 启动项目
nohup java -jar spring-boot-tool-1.0-SNAPSHOT.jar --spring.redis.host=139.198.181.54 --spring.redis.port=6379 --spring.redis.password=123456  &> spring-boot-tool.log &
echo 项目启动完成