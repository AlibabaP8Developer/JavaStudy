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
(4) 将资料中提供的MySQL安装包上传到Linux并解压
