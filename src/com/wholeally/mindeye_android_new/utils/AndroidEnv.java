/**
 * 
 */
package com.wholeally.mindeye_android_new.utils;

import android.os.Environment;

/**
 * android 公共环境
 * @author zhengjingzhong
 *
 * 2015-2-10 上午10:24:09
 */
public class AndroidEnv {

	/** 手机sdCard的路径 */
	public static final String SDCARD_PATH = Environment
			.getExternalStorageDirectory().getPath();
}
