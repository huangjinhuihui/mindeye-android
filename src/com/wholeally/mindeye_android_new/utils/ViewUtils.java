/**
 * 
 */
package com.wholeally.mindeye_android_new.utils;

import com.shilian.mindeye.android.R;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 视图相关的公共方法
 * @author zhengjingzhong
 *
 * 2015-2-5 下午3:03:08
 */
public class ViewUtils {

	private Activity activity;
	
	private Context context;

	public ViewUtils(Activity activity) {
		this.activity = activity;
	}
	
	public ViewUtils(Context context) {
		this.context = context;
	}

	/**
	 * 用系统的toast显示提示信息
	 * @param resId
	 */
	public void showToast(int resId) {
		Toast toast = Toast.makeText(activity, resId, Toast.LENGTH_LONG);
		toast.show();
		toast.setGravity(Gravity.CENTER, 0, 0);
	}
	
	/**
	 * 用自定义的toast显示提示信息
	 * @param resId
	 */
	public void showToastCustom(int resId) {
		LayoutInflater inflater = activity.getLayoutInflater();  
		View layout = inflater.inflate(R.layout.custom_toast,  null);
		TextView textView_custom_toast = (TextView)layout.findViewById(R.id.textView_custom_toast);
		textView_custom_toast.setText(resId);
		
		Toast toast = new Toast(activity);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);  
		toast.setView(layout);
		toast.show();
	}
	
	/**
	 * 用自定义的toast显示提示信息
	 * @param resId
	 */
	public void showToastCustom(String str) {
		LayoutInflater inflater = activity.getLayoutInflater();  
		View layout = inflater.inflate(R.layout.custom_toast,  null);
		TextView textView_custom_toast = (TextView)layout.findViewById(R.id.textView_custom_toast);
		textView_custom_toast.setText(str);
		
		Toast toast = new Toast(activity);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);  
		toast.setView(layout);
		toast.show();
	}
}
