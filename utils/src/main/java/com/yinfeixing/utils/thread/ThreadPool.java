package com.yinfeixing.utils.thread;

import java.util.concurrent.*;

public class ThreadPool {
	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 1000, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(100));
	
	public static Future submit(Callable t){
		return executor.submit(t);
	}
}
