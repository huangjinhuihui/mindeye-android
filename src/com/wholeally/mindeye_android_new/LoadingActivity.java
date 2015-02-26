/**
 * 
 */
package com.wholeally.mindeye_android_new;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.MindeyeInterface.Init;
import com.wholeally.mindeye_android_new.sharepreference.MyShared;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author huangjh
 *
 * 2015-1-30 上午11:10:44
 */
public class LoadingActivity extends BaseActivity{
	
	private Context context;
	Timer timer;
	TimerTask timerTask;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading); 
		initView();
	}
	
	public void initView(){
		super.initView();
		context=this;
		timer = new Timer();
		timerTask = new TimerTask() {
			public void run() {
				if (MyShared.getInt(MyShared.FIRST_LOGIN, 0)==1) {
					startActivity(new Intent(context, LoginActivity.class));
				//	startActivity(new Intent(context, WelcomeActivity.class));
					finish();
				}else {
					startActivity(new Intent(context, WelcomeActivity.class));
					finish();
				}
			}
		};
		timer.schedule(timerTask, 3000);
	}
	
	protected void onPause() {
		super.onPause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	@Override
	protected void onDestroy() {
		//Logger.LOG(false, "onDestroy");
		timerTask.cancel();
		timer.cancel();
		super.onDestroy();
	}
}
