/**
 * 
 */
package com.wholeally.mindeye_android_new.custom.view;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.LoginActivity;
import com.wholeally.mindeye_android_new.MainActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author huangjh
 * 
 *         2015-2-10 下午4:57:51
 */
public class CustomDialog {

	public static int tag = 1;
	public static AlertDialog alert_dialog;

	/**
	 * 功能:版本更新及版本升级框 操作提示(确定，取消)
	 * 
	 * @param context
	 *            当前窗口对象
	 * @param title
	 *            标题（可null 为默认“版本更新”）
	 * @param info
	 *            提示信息
	 * @param okOnClickListener
	 *            更新监听
	 */
	public static AlertDialog versionUpdateDialog(final Context context,
			String title, String info, OnClickListener okOnClickListener) {
		try {
			if (alert_dialog != null) {
				alert_dialog.cancel();
			}
			alert_dialog = new AlertDialog.Builder(context).create();
			alert_dialog.setCancelable(false);
			alert_dialog.show();
			Window window = alert_dialog.getWindow();
			WindowManager.LayoutParams lp = window.getAttributes();
			lp.width = LoginActivity.screenWidth - 50; // 宽度
			lp.x = 0;
			lp.y = -50;
			window.setAttributes(lp);
			// *** 主要就是在这里实现这种效果的.
			// 设置窗口的内容页面,dialog_version_update.xml文件中定义view内容
			window.setContentView(R.layout.dialog_version_update);
			// 为确认按钮添加事件,执行退出应用操作
			Button button_dialog_version_update_cancels = (Button) window
					.findViewById(R.id.button_dialog_version_update_cancel);
			Button button_dialog_version_update_updates = (Button) window
					.findViewById(R.id.button_dialog_version_update_update);
			TextView text_dialog_version_update_titles = (TextView) window
					.findViewById(R.id.text_dialog_version_update_title);
			TextView text_dialog_version_update_texts = (TextView) window
					.findViewById(R.id.text_dialog_version_update_text);
			if (title != null) {
				text_dialog_version_update_titles.setText(title);
			}
			text_dialog_version_update_texts.setText(info);
			text_dialog_version_update_texts
					.setMovementMethod(new ScrollingMovementMethod());
			button_dialog_version_update_updates
					.setOnClickListener(okOnClickListener);
			button_dialog_version_update_cancels
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							alert_dialog.cancel();
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alert_dialog;
	}

	/**
	 * 功能:添加设备(手动或扫描添加) 操作提示(确定，取消)
	 * 
	 * @param context
	 *            当前窗口对象
	 * 
	 * @param okOnClickListener
	 *            确定监听
	 */
	public static AlertDialog addDeviceDialog(final Context context,
			OnClickListener okOnClickListener) {
		try {
			if (alert_dialog != null) {
				alert_dialog.cancel();
			}
			alert_dialog = new AlertDialog.Builder(context).create();
			alert_dialog.setCancelable(false);
			alert_dialog.show();
			Window window = alert_dialog.getWindow();
			WindowManager.LayoutParams lp = window.getAttributes();
			lp.width = LoginActivity.screenWidth - 50; // 宽度
			lp.x = 0;
			lp.y = -50;
			window.setAttributes(lp);
			// *** 主要就是在这里实现这种效果的.
			// 设置窗口的内容页面,add_device_way_dialog.xml文件中定义view内容
			window.setContentView(R.layout.add_device_way_dialog);
			Button button_add_device_dialog_cancels = (Button) window
					.findViewById(R.id.button_add_device_dialog_cancel);
			Button button_add_device_dialog_oks = (Button) window
					.findViewById(R.id.button_add_device_dialog_ok);
			final TextView text_add_device_dialog_saomiaos = (TextView) window
					.findViewById(R.id.text_add_device_dialog_saomiao);
			final TextView text_add_device_dialog_manuals = (TextView) window
					.findViewById(R.id.text_add_device_dialog_manual);
			text_add_device_dialog_saomiaos
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							tag = 1;
							text_add_device_dialog_saomiaos
									.setBackgroundColor(context.getResources()
											.getColor(R.color.gray_dnine));
							text_add_device_dialog_manuals
									.setBackgroundColor(context.getResources()
											.getColor(R.color.gray_two));
						}
					});
			text_add_device_dialog_manuals
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							tag = 2;
							text_add_device_dialog_saomiaos
									.setBackgroundColor(context.getResources()
											.getColor(R.color.gray_two));
							text_add_device_dialog_manuals
									.setBackgroundColor(context.getResources()
											.getColor(R.color.gray_dnine));
						}
					});
			button_add_device_dialog_oks.setOnClickListener(okOnClickListener);
			button_add_device_dialog_cancels
					.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							alert_dialog.cancel();
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alert_dialog;
	}
	
	/**
	 * 功能:拍照或从相册中选择(修改头像)
	 * 
	 * @param context
	 *            当前窗口对象
	 * @param title  标题
	 * @param takephoto  第一item项文本
	 * @param photochoose  第二item项文本
	 * @param takePhotoListener拍照监听或本地相册监听
	 * @param photoChooseListener从相册中选择监听   或系统相册监听
	 */
	public static AlertDialog updateHeadDialog(final Context context,String title,String takephoto,String photochoose,
			OnClickListener takePhotoListener,OnClickListener photoChooseListener) {
		try {
			if (alert_dialog != null) {
				alert_dialog.cancel();
			}
			alert_dialog = new AlertDialog.Builder(context).create();
			alert_dialog.setCancelable(false);
			alert_dialog.show();
			Window window = alert_dialog.getWindow();
			WindowManager.LayoutParams lp = window.getAttributes();
			lp.width = LoginActivity.screenWidth - 50; // 宽度
			lp.x = 0;
			lp.y = -50;
			window.setAttributes(lp);
			// *** 主要就是在这里实现这种效果的.
			// 设置窗口的内容页面,add_device_way_dialog.xml文件中定义view内容
			window.setContentView(R.layout.update_head_dialog);
			Button button_update_head_dialog_cancels = (Button) window
					.findViewById(R.id.button_update_head_dialog_cancel);
			TextView text_update_head_dialog_takephotos = (TextView) window
					.findViewById(R.id.text_update_head_dialog_takephoto);
			TextView text_update_head_dialog_photochooses = (TextView) window
					.findViewById(R.id.text_update_head_dialog_photochoose);
			TextView text_update_head_dialog_titles=(TextView) window.findViewById(R.id.text_update_head_dialog_title);
			text_update_head_dialog_titles.setText(title);
			text_update_head_dialog_takephotos.setText(takephoto);
			text_update_head_dialog_photochooses.setText(photochoose);
			text_update_head_dialog_takephotos.setOnClickListener(takePhotoListener);
			text_update_head_dialog_photochooses.setOnClickListener(photoChooseListener);
			button_update_head_dialog_cancels
					.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							alert_dialog.cancel();
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alert_dialog;
	}
	
	/**
	 * 功能:文本信息错误提示框(如:账号信息错误等)
	 * 
	 * @param context
	 *            当前窗口对象
	 * @param button_info_error_dialog  信息文本错误
	 * @param button_info_errors_dialog  信息文本错误提示
	 */
	public static AlertDialog textInfoErrorDialog(final Context context,String button_info_error_dialog,String button_info_errors_dialog) {
		try {
			if (alert_dialog != null) {
				alert_dialog.cancel();
			}
			alert_dialog = new AlertDialog.Builder(context).create();
			alert_dialog.setCancelable(false);
			alert_dialog.show();
			Window window = alert_dialog.getWindow();
			WindowManager.LayoutParams lp = window.getAttributes();
			lp.width = LoginActivity.screenWidth - 50; // 宽度
			lp.x = 0;
			lp.y = -50;
			window.setAttributes(lp);
			// *** 主要就是在这里实现这种效果的.
			// 设置窗口的内容页面,add_device_way_dialog.xml文件中定义view内容
			window.setContentView(R.layout.text_info_error_dialog);
			Button button_info_error_dialogs = (Button) window
					.findViewById(R.id.button_info_error_dialog);
			button_info_error_dialogs.setText(button_info_error_dialog);
			Button button_info_errors_dialogs=(Button) window.findViewById(R.id.button_info_errors_dialog);
			button_info_errors_dialogs.setText(button_info_errors_dialog);
			Button button_info_error_cancels=(Button) window.findViewById(R.id.button_info_error_cancel);
			button_info_error_cancels
					.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							alert_dialog.cancel();
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alert_dialog;
	}
	
}
