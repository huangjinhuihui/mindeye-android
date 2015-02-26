package com.wholeally.mindeye_android_new.app;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.wholeally.mindeye_android_new.utils.Utils;

import android.app.Application;
import android.content.Context;


public class MindeyeApplication extends Application
{
	    
	private static MindeyeApplication instance;
	protected ImageLoaderConfiguration config;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		setImageLoaderConfiguration();
	}
	
	public void setImageLoaderConfiguration() {
		if (config == null) {
			config = Utils.getImageLoaderConfiguration(this);
		}
		// 必须初始化
		ImageLoader.getInstance().init(config);
	}
	
	public MindeyeApplication() {
		instance = this;
	}

	public static Context getContext() {
		return instance;
	}
	
}
