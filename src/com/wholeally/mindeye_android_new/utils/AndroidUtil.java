/**
 * 
 */
package com.wholeally.mindeye_android_new.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;

/**
 * <p>
 * android公共
 * </p>
 * 
 * @author chenshaorong
 */
public class AndroidUtil {

	private Activity activity;
	public static final int IMAGE_CODE = 0;
	public static final int CAPTURE_CODE = 1;
	public static final int VIDEO_CODE = 2;
	public static Uri photoUri;

	public AndroidUtil(Activity activity) {
		this.activity = activity;
	}

	/**
	 * 打开系统网络设置界面<br/>
	 * ps:startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
	 */
	public void showNetworkSetting() {
		Intent intent = new Intent("/");
		ComponentName comp = new ComponentName("com.android.settings",
				"com.android.settings.WirelessSettings");
		intent.setComponent(comp);
		intent.setAction("android.intent.action.VIEW");
		activity.startActivity(intent);
	}

	/**
	 * 打开相册
	 */
	public void showSystemImage() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		activity.startActivityForResult(intent, IMAGE_CODE);
	}
	
	/**
	 * 存储图片到手机默认相册
	 * @param bitmap
	 * @param title
	 * @param description
	 */
	public void saveImageToAlbum(Bitmap bitmap, String title, String description) {
		MediaStore.Images.Media.insertImage(activity.getContentResolver(), bitmap, title, description);
		activity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
	}
	
	/**
	 * 打开相机
	 */
	public void showCamera() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		
		SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String filename = timeStampFormat.format(new Date());
		ContentValues values = new ContentValues();
		values.put(Media.TITLE, filename);
		photoUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
		
		activity.startActivityForResult(intent, CAPTURE_CODE);
	}
	
	/**
	 * 打开相机
	 */
	public void showVideo() {
		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		// 设置视频的品质为高
		intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
		activity.startActivityForResult(intent, VIDEO_CODE);
	}

	/**
	 * 安装apk包
	 * 
	 * @param apkPath
	 */
	public void installApk(String apkPath) {
		File apkfile = new File(apkPath);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		activity.startActivity(i);
	}
	
	/**
	 * 手机震动
	 * @param activity
	 * @param milliseconds 震动的时长，单位是毫秒
	 */
	public static void Vibrate(final Activity activity, long milliseconds) {  
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);  
        vib.vibrate(milliseconds);  
    }  
	
	/**
	 * 手机震动
	 * @param activity
	 * @param pattern 自定义震动模式，[静止时长，震动时长，静止时长，震动时长。。。]
	 * @param isRepeat 是否反复震动，如果是true，反复震动，如果是false，只震动一次
	 */
    public static void Vibrate(final Activity activity, long[] pattern, boolean isRepeat) {  
        Vibrator vib = (Vibrator) activity.getSystemService(Service.VIBRATOR_SERVICE);  
        vib.vibrate(pattern, isRepeat ? 1 : -1);  
    }
    
    /**
     * 应用退出
     * android2.2之后
     * 需要加<uses-permission android:name="android.permission.RESTART_PACKAGES" />权限设置
     * @param activity
     */
    public static void exitApp(final Activity activity) {
		ActivityManager activityMgr = (ActivityManager) activity.getSystemService(Activity.ACTIVITY_SERVICE);
		activityMgr.killBackgroundProcesses(activity.getPackageName());
	}

}
