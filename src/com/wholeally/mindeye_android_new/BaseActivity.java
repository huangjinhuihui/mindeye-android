/**
 * 
 */
package com.wholeally.mindeye_android_new;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wholeally.mindeye_android_new.MindeyeInterface.Init;
import com.wholeally.mindeye_android_new.utils.ResourcesImageLoader;
import com.wholeally.mindeye_android_new.utils.ResourcesImageLoaderConfiguration;

/**
 * @author huangjh
 *
 * 2015-2-3 下午4:42:54
 */
public class BaseActivity extends Activity implements Init{
  
	/**
	 * 是否返回键退出程序
	 */
	protected boolean isClose = false;//是否返回键退出程序
	/**
	 * 是否页面已经退出
	 */
	protected boolean isDestroy = false;
	/**
	 * list图片加载器
	 */
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	/**
	 * 资源图片加载器
	 */
	protected ResourcesImageLoader resourcesImageLoader = ResourcesImageLoader.getInstance();
	protected ResourcesImageLoaderConfiguration configuration =new ResourcesImageLoaderConfiguration().setCacheInMemory(true);
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		context = this;
		super.onCreate(savedInstanceState);
	}

	@Override
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
		super.onDestroy();
	}

	public void myOnClick(View v){
		
	}
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		
	}
	
	protected void imageStop() {
		try {
			imageLoader.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.wholeally.mindeye_android_new.MindeyeInterface.Init#goBack(android.view.View)
	 */
	@Override
	public void goBack(View v) {
		// TODO Auto-generated method stub
		finish();
	}
	
}
