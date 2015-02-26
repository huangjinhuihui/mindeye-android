/**
 * 
 */
package com.wholeally.mindeye_android_new;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.shilian.mindeye.android.R;

/**
 * @author huangjh
 *
 * 2015-2-11 下午3:43:41
 */
public class ManualAddDeviceActivity extends BaseActivity implements OnClickListener{
	
	private Context context;
	private TextView textView_common_titlebar_titles;//标题
	private TextView text_common_titlebar_textadds;//添加
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manual_add_device);
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.manual_add_device_activity_title);
		text_common_titlebar_textadds=(TextView) findViewById(R.id.text_common_titlebar_textadd);
		text_common_titlebar_textadds.setVisibility(View.VISIBLE);
	}
	
	public void event(){
		super.event();
		text_common_titlebar_textadds.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.text_common_titlebar_textadd://添加
			
			break;

		default:
			break;
		}
	}

}
