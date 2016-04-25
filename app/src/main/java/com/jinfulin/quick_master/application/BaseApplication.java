package com.jinfulin.quick_master.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

public class BaseApplication extends Application {
	private static Handler handler;
	private static Context context;
	private static int mainThreadId;
	private static Thread mainThread;
	@Override
	public void onCreate() {
		super.onCreate();
		//Handler对象
		handler = new Handler();
		//Context
		context = getApplicationContext();
		//主线程id,获取当前方法运行线程id,此方法运行在主线程中,所以获取的是主线程id
		mainThreadId = android.os.Process.myTid();
		//主线程对象
		mainThread = Thread.currentThread();
	}
	
	public static Handler getHandler() {
		return handler;
	}
	public static Context getContext() {
		return context;
	}
	public static int getMainThreadId() {
		return mainThreadId;
	}
	public static Thread getMainThread() {
		return mainThread;
	}
}
