/**
 * 
 */
package com.wholeally.mindeye_android_new.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import android.app.Activity;

/**
 * <p>中间临时数据</p>
 * @author chenshaorong
 */
public class TempValue {
	
	/** 全局临时map */
	public static Map<String, Object> tempMap = new ConcurrentHashMap<String, Object>();
	
	/**
	 * @return the tempMap
	 */
	public static Map<String, Object> getTempMap() {
		return tempMap;
	}

	/**
	 * @param tempMap the tempMap to set
	 */
	public static void setTempMap(Map<String, Object> tempMap) {
		TempValue.tempMap = tempMap;
	}
	
}
