/**
 * 
 */
package com.wholeally.mindeye_android_new;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shilian.mindeye.android.R;

/**
 * @author huangjh
 *
 * 2015-2-10 下午2:45:29
 */
public class SystemSetActivity extends BaseActivity{
	
	private TextView textView_common_titlebar_titles;//标题
	private ImageView imageView_common_titlebar_rights;//导航右边图片
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_system_set);
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.system_set_activity_systemset);
		imageView_common_titlebar_rights=(ImageView) findViewById(R.id.imageView_common_titlebar_right);
		imageView_common_titlebar_rights.setVisibility(View.GONE);
	}

}
