#!/bin/bash
/home/dcp/0-start-eureka-8761.sh
sleep 5 
/home/dcp/1-start-gateway-7000.sh
sleep 5 
/home/dcp/2-start-sys-service-7003.sh
sleep 5 
/home/dcp/3-start-auth-service-7001.sh
sleep 5 
/home/dcp/4-start-file-service-7002.sh
#sleep 5
#/home/dcp/5-start-ws-service-7009.sh
sleep 5
/home/dcp/12-start-ums-service-7012.sh
sleep 5
/home/dcp/13-start-log-service-7013.sh
sleep 5
/home/dcp/14-start-uum-service-7014.sh
sleep 5
/home/dcp/15-start-msg-service-7015.sh
sleep 5
/home/dcp/16-start-workflow-service-7016.sh
sleep 5
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
/home/dcp/29-start-dcp-job-service-7029.sh
sleep 5
/home/dcp/30-start-search-service-7030.sh
sleep 5
/home/dcp/31-start-dcp-resourceshare-service-7031.sh
sleep 5
/home/dcp/32-start-dcp-fileextend-service-7032.sh