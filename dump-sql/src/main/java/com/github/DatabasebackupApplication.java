package com.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 要执行监听器需要加注解 @ServletComponentScan
 */
@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class DatabasebackupApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabasebackupApplication.class, args);
	}
}