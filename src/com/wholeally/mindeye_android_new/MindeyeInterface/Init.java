package com.wholeally.mindeye_android_new.MindeyeInterface;

import android.view.View;

/**
 * 给每个Activity实现的
 * @author zhengjingzhong
 *
 * 2015-1-30 下午3:17:32
 */
public interface Init
{
	/**
	 * 初始化布局
	 */
	public void initView();
	
	/**
	 * 注册监听
	 */
	public void event();
	
	/**
	 * 回退
	 */
	public void goBack(View v);

	/**
	 * @param v
	 */
	void myOnClick(View v);

}
