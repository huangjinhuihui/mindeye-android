/**
 * 
 */
package com.wholeally.mindeye_android_new;

import java.io.IOException;
import java.util.Vector;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.camera.CameraManager;
import com.wholeally.mindeye_android_new.camera.CaptureActivityHandler;
import com.wholeally.mindeye_android_new.camera.InactivityTimer;
import com.wholeally.mindeye_android_new.custom.view.ViewfinderView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.TextView;

/**
 * @author huangjh
 *
 * 2015-2-13 下午3:45:59
 */
public class CaptureActivity extends BaseActivity implements Callback{

	private Context context;
	private boolean hasSurface;
	private TextView textView_common_titlebar_titles;//标题
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private InactivityTimer inactivityTimer;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capture);
		//初始化 CameraManager
		CameraManager.init(getApplication());
		initView();
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.manual_add_device_activity_title);
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
	}

	public void event(){
		super.event();
	}
	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceChanged(android.view.SurfaceHolder, int, int, int)
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}
	}

	/* (non-Javadoc)
	 * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		hasSurface = false;
	}
	
	public Handler getHandler() {
		return handler;
	}
	
	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}
	
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;
	}
	
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
		System.gc();
	}
	
	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}
	
	public void drawViewfinder() {
		viewfinderView.drawViewfinder();
	}
	
	public void handleDecode(Result obj, Bitmap barcode) {
		inactivityTimer.onActivity();
		viewfinderView.drawResultBitmap(barcode);
//		playBeepSoundAndVibrate();
//		txtResult.setText(obj.getBarcodeFormat().toString() + ":"
//				+ obj.getText());
//		
//		if(obj != null && obj.getText() != null && obj.getText() != "")
//		{
//			//后来的二维码扫描后的内容有两行，第一行是设备ID，第二行是网卡地址，两行之间有换行符分隔
//			String text = obj.getText();
//			System.out.println("barcode text:" + text);
//			String splitTexts[] = text.split("\r\n");
//			for (int i=0; i<splitTexts.length; i++)
//			{
//				System.out.println("barcode splitTexts["+i+"]:" + splitTexts[i]);
//			}
//			
//			Intent intent = new Intent();
//			intent.setClass(this, AddDeviceActivity.class);
//			intent.putExtra("deviceID", splitTexts[0].trim());
//			startActivity(intent);
//			finish();
//    	}
	}
	
}
