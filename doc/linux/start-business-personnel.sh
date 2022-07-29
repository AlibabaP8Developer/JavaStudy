#!/bin/sh
echo =================================
echo 自动化部署脚步启动
echo =================================

echo 停止原来运行的工程
APP_NAME_PER=dcp-personnel-service

echo 查找dcp-personnel-service服务进程

tpid=`ps -ef|grep $APP_NAME_PER|grep -v grep|grep -v kill|awk '{print $2}'`
echo dcp-personnel-service服务进程是：$tpid

if [ ${tpid} ]; then
    echo 'Stop Process...'
    kill -15 $tpid
fi
sleep 2
tpid=`ps -ef|grep $APP_NAME_PER|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Kill Process!'
    kill -9 $tpid
else
    echo 'Stop Success!'
fi

echo dcp-personnel-service服务已停止


echo 启动项目

/home/dcp/17-start-dcp-personnel-service-7017.sh


echo 项目启动完成