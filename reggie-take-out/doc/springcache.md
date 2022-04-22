# Spring Cache常用注解
@EnableCaching：开启缓存注解功能
@Cacheable：在方法执行前spring先查看缓存中是否有数据，如果有数据，则直接返回缓存数据；
            若没有数据，调用方法并将方法返回值放到缓存中
@CachePut：将方法的返回值放到缓存中
@CacheEvict：将一条或多条数据从缓存中删除

使用缓存技术只需在项目中导入相关缓存技术的依赖包，在启动类上加
@EnableCaching开启缓存支持即可
