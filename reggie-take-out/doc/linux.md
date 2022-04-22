# 项目部署
## 1.通过shell脚本自动部署项目
(1) 在Linux中安装Git
```shell
yum list git
yum install git
```
(2) 使用Git克隆代码
```shell
cd /usr/local/
git clone https://gitee.com/pandaCodeSoftDev/renren-fast.git
```
(3) 将资料中提供的maven安装包上传到Linux，在Linux中安装maven
```shell
tar -zxvf apache-maven-3.5.4-bin.tar.gz -C /usr/local/
vim /etc/profile  #修改配置文件，加入如下内容
export MAVEN_HOME=/usr/local/apache-maven-3.5.4
export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin/:$PATH

source /etc/profile
mvn -version
vim /usr/local/apache-maven-3.5.4/conf/settings.xml
修改配置文件内容:
<localRepository>/usr/local/repository</localRepository>
```
(4) 将doc中提供的shell脚本上传到Linux
(5) 为用户授权
```shell
chmod命令是控制用户对文件的权限的命令
r（读）、w（写）、x（执行）
chmod 777 xxx.sh 为所有用户授予读写、执行权限
chmod 755 xxx.sh 为文件拥有者授予读、写、执行权限，同组用户和其他用户授予读、执行权限
chmod 210 xxx.sh 为文件拥有者授予写权限，同组用户授予执行权限，其他用户没有任何权限
```

