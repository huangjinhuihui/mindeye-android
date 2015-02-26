/**
 * 
 */
package com.wholeally.mindeye_android_new.sharepreference;

import java.lang.reflect.Field;

import com.wholeally.mindeye_android_new.app.MindeyeApplication;

import android.content.SharedPreferences;

/**
 * @author huangjh
 *
 * 2015-1-30 上午10:59:27
 */
public class MyShared {
	
	private static final String SHAREDNAME = "mindeye";
	/**
	 * 该应用是否是首次登录  1：是    其它：不是
	 */
	public static final String FIRST_LOGIN="FIRST_LOGIN";

	/**批量存储bean对象属性
	 * @param obj
	 */
	public static void saveObject(Object obj){
		SharedPreferences sp = MindeyeApplication.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		SharedPreferences.Editor editor = sp.edit();
		
		Class<?> objclass = obj.getClass();
		Field[] fields = objclass.getDeclaredFields();
		for (Field f : fields) {
			try {
				f.setAccessible(true);//在类的外面获取此类的私有成员变量的value时 ,必须进行此操作
				editor.putString(f.getName(), String.valueOf(f.get(obj)));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		editor.commit();
	}
	
	/**删除指定的对象
	 */
	public static void removeObject(Class<?> objclass){
		SharedPreferences sp = MindeyeApplication.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		SharedPreferences.Editor editor = sp.edit();
		Field[] fields = objclass.getDeclaredFields();
		for (Field f : fields) {
			try {
				f.setAccessible(true);
				editor.remove(f.getName());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} 
		}
		editor.commit();
	}
	
	/**保存key所对应的value值
	 */
	public static void saveData(String key, Object value) {
		SharedPreferences sp = MindeyeApplication.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		SharedPreferences.Editor editor = sp.edit();
		if (value instanceof String) {
			editor.putString(key, value.toString());
		} else if (value instanceof Boolean) {
			editor.putBoolean(key, Boolean.getBoolean(value.toString()));
		} else if (value instanceof Float) {
			editor.putFloat(key, Float.parseFloat(value.toString()));
		} else if (value instanceof Integer) {
			editor.putInt(key, Integer.parseInt(value.toString()));
		} else if (value instanceof Long) {
			editor.putLong(key, Long.parseLong(value.toString()));
		} else {
			editor.putString(key, value.toString());
		}
		editor.commit();
	}
	
	/**删除相应的key时,即所对应的值即为删除
	 */
	public static void removeData(String key) {
		try {
			SharedPreferences sp = MindeyeApplication.getContext()
					.getSharedPreferences(SHAREDNAME, 0);
			if (sp.contains(key))
				sp.edit().remove(key).commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getString(String key, String defaultValue) {
		SharedPreferences sp = MindeyeApplication.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		return sp.getString(key, defaultValue);
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		SharedPreferences sp = MindeyeApplication.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		return sp.getBoolean(key, defaultValue);
	}

	public static float getFloat(String key, float defaultValue) {
		SharedPreferences sp = MindeyeApplication.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		return sp.getFloat(key, defaultValue);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return int 没有返回-1
	 */
	public static int getInt(String key, int defaultValue) {
		try {
			SharedPreferences sp = MindeyeApplication.getContext()
					.getSharedPreferences(SHAREDNAME, 0);
			return sp.getInt(key, defaultValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	public static long getLong(String key, long defaultValue) {
		SharedPreferences sp = MindeyeApplication.getContext()
				.getSharedPreferences(SHAREDNAME, 0);
		return sp.getLong(key, defaultValue);
	}
	
}
