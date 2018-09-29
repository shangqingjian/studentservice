package com.huawei.StudentService.sysinit;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ThreadManager {

	private static final Logger logger = Logger.getLogger(ThreadManager.class);
	// cpu核心线程数
	private static final int CPU_CORE_NUM = Runtime.getRuntime().availableProcessors();

	private static ExecutorService cachedExecutor = null;
	private static ExecutorService fixedExecutor = null;
	private static ScheduledExecutorService scheduledExecutor = null;
	
	@PostConstruct
	private void init() {
		cachedExecutor = getDefaultCachedExecutor();
		fixedExecutor = getDefaultFixedExecutor();
		scheduledExecutor = getDefaultScheduledExecutor();
	}

	private static ExecutorService createExecutorService(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit) {
		LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
		return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	private static ExecutorService getDefaultCachedExecutor() {
		if (null == cachedExecutor) {
			int corePoolSize = CPU_CORE_NUM * 4;
			int maximumPoolSize = CPU_CORE_NUM * 8 - 1;
			long keepAliveTime = 10L;
			cachedExecutor = createExecutorService(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS);
		}
		return cachedExecutor;
	}

	private static ExecutorService getDefaultFixedExecutor() {
		if (null == fixedExecutor) {
			int corePoolSize = CPU_CORE_NUM;
			int maximumPoolSize = CPU_CORE_NUM * 2 - 1;
			long keepAliveTime = 60L;
			fixedExecutor = createExecutorService(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS);
		}
		return fixedExecutor;
	}

	private static ScheduledExecutorService getDefaultScheduledExecutor() {
		if (null == scheduledExecutor) {
			int corePoolSize = CPU_CORE_NUM * 4 - 1;
			scheduledExecutor = new ScheduledThreadPoolExecutor(corePoolSize);
		}
		return scheduledExecutor;
	}

	/**
	 * 适合执行花费时间不长，但频率较高的任务
	 * @param run
	 */
	public static void executeShortTimeTask(Runnable run) {
		getDefaultCachedExecutor().execute(run);
	}

	/**
	 * 适合执行需要花费时间较长，但不是经常执行的任务
	 * @param run
	 */
	public static void executeLongTimeTask(Runnable run) {
		getDefaultFixedExecutor().execute(run);
	}

	/**
	 * 提交异步任务，带返回值
	 * @param <T>
	 * @param run
	 * @return
	 */
	public static <T> T submitTask(Callable<T> run, long timeout, TimeUnit unit) {
		Future<T> result = getDefaultCachedExecutor().submit(run);
		T t = null;
		try {
			t = result.get(timeout, unit);
		}catch (Exception e) {
			logger.error("Can not get result from callable task.", e);
		}
		return t;
	}

	/**
	 * 延迟一定时间后，开始以一定的时间间隔执行任务
	 * @param run
	 * @param delay
	 * @param period
	 * @param unit
	 */
	public static void startScheduledTask(Runnable run, long delay, long period, TimeUnit unit) {
		getDefaultScheduledExecutor().scheduleAtFixedRate(run, delay, period, unit);
	}

}
