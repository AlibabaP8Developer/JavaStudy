package com.github.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 监听器，注解方式
 */
@WebListener
public class TaskManager implements ServletContextListener {
	/**
	 * 每天的毫秒数
	 */
	public static final long PERIOD_DAY = 10;
	/**
	 * 一周内的毫秒数
	 */
	public static final long PERIOD_WEEK = PERIOD_DAY * 7;
	/**
	 * 无延迟
	 */
	public static final long NO_DELAY = 0;
	/**
	 * 定时器
	 */
	private Timer timer;

	/**
	 * 在Web应用启动时初始化任务
	 */
	public void contextInitialized(ServletContextEvent event) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0); // 控制时
		calendar.set(Calendar.MINUTE, 0); // 控制分
		calendar.set(Calendar.SECOND, 5); // 控制秒
		Date date = calendar.getTime();
		// 定义定时器
		timer = new Timer("数据库表备份", true);
		// 启动备份任务
		timer.schedule(new DatabaseAllController(),date);
	}

	/**
	 * 在Web应用结束时停止任务
	 */
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel(); // 定时器销毁
	}
}