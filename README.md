# 介绍

代码里包含详细的代码示例，不懂之处可以在代码中体会理解！！！

common：常用工具类

java8: java基础和Java8新特性

java-nio：NIO

juc：多线程

leetcode：leetcode题目案例、面试总结、整理大厂面试题

springboot：springboot demo代码案例、讲义

spring-cloud-alibaba：微服务案例

  ｜- eureka-server：集成了eureka，目前使用的nacos，eureka相关配置暂时被屏蔽了，如需使用调整注释中配置信息即可
  
  ｜- feign-api：整合了feign远程调用组件
  
  ｜- gateway：整合了spring cloud gateway
  
  ｜- order-service：订单服务demo
  
  ｜- user-service：用户服务demo
  
  ｜- doc：sql脚本、nacos文档、使用说明
  
  ｜- spring-boot-admin-server：服务监控指标

spring-cloud-gateway包含过滤器

通过网关访问方式：http://localhost:10010/order/102?authorization=admin

服务监控指标：http://localhost:10000/applications
  
软考资源<br>

资源正在持续更新中...

# Java
## 基础&新特性

<p>
  <a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/java8/src/main/resources/java-basic-01.md">
  Java基础常见知识点&面试题总结(1)
  </a>
</p>
<p>
  <a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/java8/src/main/resources/java-basic-02.md">
  Java基础常见知识点&面试题总结(2)
  </a>
</p>
<p>
  <a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/java8/src/main/resources/java-basic-03.md">
  Java基础常见知识点&面试题总结(3)
  </a>
</p>

<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/java8/src/main/resources/java-8.md">
  Java8新特性.md</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/java8/src/main/resources/JDK8%E6%96%B0%E7%89%B9%E6%80%A7.pdf">
  Java8新特性.pdf</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/java11/doc/java9.md">
  Java9新特性</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/java11/doc/java10.md">
  Java10新特性</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/java11/doc/JDK11%26JDK12%E6%96%B0%E7%89%B9%E6%80%A7%E8%AF%A6%E8%A7%A3.pdf">
  Java11&12新特性PDF</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/java11/doc/java11.md">
  Java11新特性</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/java11/doc/java12-13.md">
  Java12&13新特性</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/java11/doc/java14-15.md">
  Java14&15新特性</a>
</p>

## 集合

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/leetcode/src/main/resources/%E9%9D%A2%E8%AF%95%E6%80%BB%E7%BB%93/Java%E9%9B%86%E5%90%88%E5%B8%B8%E8%A7%81%E7%9F%A5%E8%AF%86%E7%82%B9%26%E9%9D%A2%E8%AF%95%E9%A2%98%E6%80%BB%E7%BB%93(2).md">
  Java集合常见知识点&面试题总结(2)</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/leetcode/src/main/resources/%E9%9D%A2%E8%AF%95%E6%80%BB%E7%BB%93/Java%E9%9B%86%E5%90%88%E5%B8%B8%E8%A7%81%E7%9F%A5%E8%AF%86%E7%82%B9%26%E9%9D%A2%E8%AF%95%E9%A2%98%E6%80%BB%E7%BB%93(1).md">
  Java集合常见知识点&面试题总结(1)</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/leetcode/src/main/resources/%E9%9D%A2%E8%AF%95%E6%80%BB%E7%BB%93/Java%E9%9B%86%E5%90%88%E4%BD%BF%E7%94%A8%E6%B3%A8%E6%84%8F%E4%BA%8B%E9%A1%B9%E6%80%BB%E7%BB%93.md">
  Java集合使用注意事项总结
  </a>
</p>

## 重要知识点

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/leetcode/src/main/resources/%E9%9D%A2%E8%AF%95%E6%80%BB%E7%BB%93/%E5%8F%8D%E5%B0%84%E6%9C%BA%E5%88%B6.md">
  反射机制</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/leetcode/src/main/resources/%E9%9D%A2%E8%AF%95%E6%80%BB%E7%BB%93/%E4%B8%BA%E4%BB%80%E4%B9%88%20Java%20%E4%B8%AD%E5%8F%AA%E6%9C%89%E5%80%BC%E4%BC%A0%E9%80%92.md">
  为什么 Java 中只有值传递</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/leetcode/src/main/resources/%E9%9D%A2%E8%AF%95%E6%80%BB%E7%BB%93/%E5%9F%BA%E7%A1%80%E7%AF%87%E8%AE%B2%E4%B9%89.md">
  算法篇：二分查找、排序、设计模式等</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/leetcode/src/main/resources/%E9%9D%A2%E8%AF%95%E6%80%BB%E7%BB%93/BigDecimal%E8%A7%A3%E5%86%B3%E6%B5%AE%E7%82%B9%E6%95%B0%E8%BF%90%E7%AE%97%E7%B2%BE%E5%BA%A6%E4%B8%A2%E5%A4%B1%E9%97%AE%E9%A2%98.md">
  BigDecimal解决浮点数运算精度丢失问题
  </a>
</p>

## 常用框架
### Spring
<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/leetcode/src/main/resources/%E9%9D%A2%E8%AF%95%E6%80%BB%E7%BB%93/SpringBoot%E5%B8%B8%E7%94%A8%E6%B3%A8%E8%A7%A3%E6%80%BB%E7%BB%93.md">
  SpringBoot常用注解总结.md</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/leetcode/src/main/resources/%E9%9D%A2%E8%AF%95%E6%80%BB%E7%BB%93/springboot%E8%87%AA%E5%8A%A8%E8%A3%85%E9%85%8D%E5%8E%9F%E7%90%86.md">
  SpringBoot自动装配原理</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/leetcode/src/main/resources/%E9%9D%A2%E8%AF%95%E6%80%BB%E7%BB%93/Spring%E4%BA%8B%E5%8A%A1%E6%80%BB%E7%BB%93.md">
  Spring事务总结</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/leetcode/src/main/resources/%E9%9D%A2%E8%AF%95%E6%80%BB%E7%BB%93/Spring%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F%E6%80%BB%E7%BB%93.md">
  Spring设计模式总结</a>
</p>

### SpringCloud

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/spring-cloud-alibaba/doc/SpringCloud%E5%AE%9E%E7%94%A8%E7%AF%8701.md">
  SpringCloud、Eureka注册中心、Ribbon负载均衡、Nacos注册中心
</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/spring-cloud-alibaba/doc/SpringCloud%E5%AE%9E%E7%94%A8%E7%AF%8702.md">
  Nacos配置管理、Feign远程调用、SpringCloud Gateway服务网关
</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/spring-cloud-alibaba/doc/nacos%E9%9B%86%E7%BE%A4%E6%90%AD%E5%BB%BA.md">
  nacos集群搭建</a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/spring-cloud-alibaba/doc/Nacos%E5%AE%89%E8%A3%85%E6%8C%87%E5%8D%97.md">
  Nacos安装指南</a>
</p>

## 并发

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/juc/src/main/resources/%E9%9D%A2%E8%AF%95%E6%80%BB%E7%BB%93/%E5%B9%B6%E5%8F%91%E7%AF%87%E8%AE%B2%E4%B9%89.md">
  并发篇讲义 JUC、多线程
  </a>
</p>

<p>
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/java-nio/nio-demo/src/main/resources/%E8%AF%BE%E4%BB%B6/01_%E5%B0%9A%E7%A1%85%E8%B0%B7_Java%20NIO_%E8%AF%BE%E4%BB%B6_V1.0.pdf">
  Java NIO网络编程</a>
</p>

# 数据库
## MySql
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/doc/database/basis.md">数据库基础知识</a>

<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/doc/database/character-set.md">MySQl字符集</a>

<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/doc/database/mysql/a-thousand-lines-of-mysql-study-notes.md">一千行MySQL学习笔记</a>

<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/doc/database/mysql/how-sql-executed-in-mysql.md">一条SQL语句在 MySQL 中如何被执行的?</a>

<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/doc/database/mysql/innodb-implementation-of-mvcc.md">InnoDB存储引擎对MVCC的实现</a>

<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/doc/database/mysql/mysql-high-performance-optimization-specification-recommendations.md">MySQL 高性能优化规范建议</a>

<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/doc/database/mysql/mysql-index.md">MySQL 索引详解</a>

<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/doc/database/mysql/mysql-logs.md">MySQL三大日志(binlog、redo log和undo log)详解</a>

<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/doc/database/mysql/mysql-questions-01.md">MySQL知识点&面试题总结</a>

<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/doc/database/mysql/some-thoughts-on-database-storage-time.md">关于数据库中如何存储时间的一点思考</a>

<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/doc/database/mysql/transaction-isolation-level.md">事务隔离级别(图文详解)</a>

## Redis
<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/doc/database/Redis/3-commonly-used-cache-read-and-write-strategies.md">3种常用的缓存读写策略</a>

<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/doc/database/Redis/redis-memory-fragmentation.md">Redis 内存碎片</a>

<a href="https://github.com/lzhjavagithub/JavaStudy/blob/master/doc/database/Redis/redis-questions-01.md">Redis知识点&面试题总结</a>

# 开源项目精选
<p>
  <a href="https://github.com/yangzongzhuan/RuoYi">
    ruoyi单体</a>：一直想做一款后台管理系统，看了很多优秀的开源项目但是发现没有合适的。于是利用空闲休息时间开始自己写了一套后台系统。如此有了若依。她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA。所有前端后台代码封装过后十分精简易上手，出错概率低。同时支持移动客户端访问。系统会陆续更新一些实用功能。
</p>
<p>
  <a href="https://github.com/yangzongzhuan/RuoYi-Vue">
    ruoyi-vue前后端分离</a>
</p>
<p>
  <a href="https://github.com/yangzongzhuan/RuoYi-Cloud">ruoyi微服务</a>
</p>
<p>
  <a href="https://github.com/macrozheng/mall">
    mall项目是一套电商系统
  </a>：包括前台商城系统及后台管理系统，基于SpringBoot+MyBatis实现，采用Docker容器化部署。前台商城系统包含首页门户、商品推荐、商品搜索、商品展示、购物车、订单流程、会员中心、客户服务、帮助中心等模块。后台管理系统包含商品管理、订单管理、会员管理、促销管理、运营管理、内容管理、统计报表、财务管理、权限管理、设置等模块。
</p>
<p>
  <a href="https://github.com/macrozheng/mall-swarm">mall-swarm是一套微服务商城系统
  </a>：采用了 Spring Cloud Hoxton & Alibaba、Spring Boot 2.3、Oauth2、MyBatis、Elasticsearch、Docker、Kubernetes等核心技术，同时提供了基于Vue的管理后台方便快速搭建系统。mall-swarm在电商业务的基础集成了注册中心、配置中心、监控中心、网关等系统功能。文档齐全，附带全套Spring Cloud教程。
</p>
<p>
  <a href="https://github.com/macrozheng/mall-learning">
  mall学习教程</a>：架构、业务、技术要点全方位解析。mall项目（40k+star）是一套电商系统，使用现阶段主流技术实现。涵盖了SpringBoot 2.3.0、MyBatis 3.4.6、Elasticsearch 7.6.2、RabbitMQ 3.7.15、Redis 5.0、MongoDB 4.2.5、Mysql5.7等技术，采用Docker容器化部署。
</p>
<p>
  <a href="https://github.com/wuyouzhuguli/SpringAll">
    Spring 系列教程</a>：该仓库为个人博客https://mrbird.cc中Spring系列源码，包含Spring Boot、Spring Boot & Shiro、Spring Cloud，Spring Boot & Spring Security & Spring Security OAuth2，如果该系列教程对您有帮助的话，还请点个star给予精神支持！
</p>

# 关于作者

本人大专学历+网络教育本科学历，5年+Java开发经验，平时工作中的学习笔记和开发经验总结归纳，涵盖了Java基础、并发、多线程、高性能、高可用、数据库、redis、数据结构和算法、设计模式、互联网大厂面试经验和面试题

我在大二期间一直坚持每天在csdn写博客，那时候就是随意地在博客平台上发发自己的学习笔记和自己写的程序，积累学习经验和开发经验，将我认为好的资源、学习经验和开发经验向大家分享；
可能还有人问我在大学期间赚了多少钱？
在校期间，我还通过办培训班、接私活、技术培训、编程竞赛等方式变现 20w+，成功实现“经济独立”。我用自己赚的钱去了齐国、楚国、金陵、秦国等地历史古迹旅游，还给家里补贴了很多，减轻了父母的负担。

身边也有很多小伙伴经常问我：“我现在写博客还晚么？”

我觉得哈！如果你想做什么事情，尽量少问迟不迟，多问自己值不值得，只要你觉得有意义，就尽快开始做吧！人生很奇妙，我们每一步的重大决定，都会对自己未来的人生轨迹产生影响。是好还是坏，也只有我们自己知道了！

对我自己来说，坚持写博客这一项决定对我人生轨迹产生的影响是非常正面的！所以，我也推荐大家养成坚持写博客的习惯。

很多老读者应该比较清楚，我是 18 年专科毕业的，因为是大专学历应届生找工作比较艰难，那个时候感觉基本啥也不会，刚毕业好不容易去了某家外包公司“养老”锻炼了两年多。
现在是小米公司

# 友情链接

<a href="https://blog.csdn.net/qq_30398499?spm=1000.2115.3001.5343">CSDN博客</a>




