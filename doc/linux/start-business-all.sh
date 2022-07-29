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

#----------

APP_NAME_WORK=dcp-work-service

echo 查找dcp-work-service服务进程

tpid=`ps -ef|grep $APP_NAME_WORK|grep -v grep|grep -v kill|awk '{print $2}'`

echo dcp-work-service服务进程是：$tpid

if [ ${tpid} ]; then
    echo 'Stop Process...'
    kill -15 $tpid
fi
sleep 2
tpid=`ps -ef|grep $APP_NAME_WORK|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Kill Process!'
    kill -9 $tpid
else
    echo 'Stop Success!'
fi

echo dcp-work-service服务已停止

#----------------

APP_NAME_PRO=dcp-project-service

echo 查找dcp-project-service服务进程

tpid=`ps -ef|grep $APP_NAME_PRO|grep -v grep|grep -v kill|awk '{print $2}'`

echo dcp-project-service服务进程是：$tpid

if [ ${tpid} ]; then
    echo 'Stop Process...'
    kill -15 $tpid
fi
sleep 2
tpid=`ps -ef|grep $APP_NAME_PRO|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Kill Process!'
    kill -9 $tpid
else
    echo 'Stop Success!'
fi

echo dcp-project-service服务已停止

#-------------------

APP_NAME_CMS=dcp-cms-service

echo 查找dcp-cms-service服务进程

tpid=`ps -ef|grep $APP_NAME_CMS|grep -v grep|grep -v kill|awk '{print $2}'`

echo dcp-cms-service服务进程是：$tpid

if [ ${tpid} ]; then
    echo 'Stop Process...'
    kill -15 $tpid
fi
sleep 2
tpid=`ps -ef|grep $APP_NAME_CMS|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Kill Process!'
    kill -9 $tpid
else
    echo 'Stop Success!'
fi


echo dcp-cms-service服务已停止

#--------------------

APP_NAME_UC=dcp-usercenter-service

echo 查找dcp-usercenter-service服务进程

tpid=`ps -ef|grep $APP_NAME_UC|grep -v grep|grep -v kill|awk '{print $2}'`

echo dcp-usercenter-service服务进程是：$tpid

if [ ${tpid} ]; then
    echo 'Stop Process...'
    kill -15 $tpid
fi
sleep 2
tpid=`ps -ef|grep $APP_NAME_UC|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Kill Process!'
    kill -9 $tpid
else
    echo 'Stop Success!'
fi

echo dcp-usercenter-service服务已停止

#-----------------------------

APP_NAME_OP=dcp-operation-service

echo 查找dcp-operation-service服务进程

tpid=`ps -ef|grep $APP_NAME_OP|grep -v grep|grep -v kill|awk '{print $2}'`

echo dcp-operation-service服务进程是：$tpid

if [ ${tpid} ]; then
    echo 'Stop Process...'
    kill -15 $tpid
fi
sleep 2
tpid=`ps -ef|grep $APP_NAME_OP|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Kill Process!'
    kill -9 $tpid
else
    echo 'Stop Success!'
fi

echo dcp-operation-service服务已停止

#-----------------------------

APP_NAME_M=dcp-meeting-service

echo 查找dcp-meeting-service服务进程

tpid=`ps -ef|grep $APP_NAME_M|grep -v grep|grep -v kill|awk '{print $2}'`

echo dcp-meeting-service服务进程是：$tpid

if [ ${tpid} ]; then
    echo 'Stop Process...'
    kill -15 $tpid
fi
sleep 2
tpid=`ps -ef|grep $APP_NAME_M|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Kill Process!'
    kill -9 $tpid
else
    echo 'Stop Success!'
fi

echo dcp-meeting-service服务已停止

#-----------------------------

APP_NAME_RES=dcp-resourceshare-service
echo 查找dcp-resourceshare-service服务进程
tpid=`ps -ef|grep $APP_NAME_RES|grep -v grep|grep -v kill|awk '{print $2}'`
echo dcp-resourceshare-service服务进程是：$tpid
if [ ${tpid} ]; then
    echo 'Stop Process...'
    kill -15 $tpid
fi
sleep 2
tpid=`ps -ef|grep $APP_NAME_RES|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo 'Kill Process!'
    kill -9 $tpid
else
    echo 'Stop Success!'
fi
echo dcp-resourceshare-service服务已停止

#-----------------------------

APP_NAME_CUS=dcp-customer-service
echo 查找dcp-customer-service服务进程
tpid=`ps -ef|grep $APP_NAME_CUS|grep -v grep|grep -v kill|awk '{print $2}'`

echo dcp-customer-service服务进程是：$tpid

if [ ${tpid} ]; then
    echo 'Stop Process...'
    kill -15 $tpid
fi
sleep 2
tpid=`ps -ef|grep $APP_NAME_CUS|grep -v grep|grep -v kill|awk '{print $2}'`

echo dcp-customer-service服务进程是：$tpid

if [ ${tpid} ]; then
    echo 'Kill Process!'
    kill -9 $tpid
else
    echo 'Stop Success!'
fi
echo dcp-customer-service服务已停止
#-----------------------------

echo 启动项目

/home/dcp/17-start-dcp-personnel-service-7017.sh
sleep 5 
/home/dcp/21-start-dcp-work-service-7021.sh
sleep 5 
/home/dcp/22-start-dcp-project-service-7022.sh
sleep 5
/home/dcp/24-start-dcp-cms-service-7024.sh
sleep 5
/home/dcp/25-start-dcp-usercenter-service-7025.sh
sleep 5
/home/dcp/26-start-dcp-operation-service-7026.sh
sleep 5 
/home/dcp/27-start-dcp-meeting-service-7027.sh
sleep 5
/home/dcp/28-start-dcp-customer-service-7028.sh
sleep 5
/home/dcp/31-start-dcp-resourceshare-service-7031.sh


echo 项目启动完成