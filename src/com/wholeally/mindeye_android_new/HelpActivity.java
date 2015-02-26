/**
 * 
 */
package com.wholeally.mindeye_android_new;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shilian.mindeye.android.R;

/**
 * @author huangjh
 *
 * 2015-2-5 下午4:07:30
 */
public class HelpActivity extends BaseActivity implements OnClickListener{
	
	private Context context;
	private TextView textView_common_titlebar_titles;//标题
	private ImageView imageView_common_titlebar_rights;//导航右边图片
	private RelativeLayout relative_HelpActivity_deviceuses;//设备使用帮助
	private RelativeLayout relative_HelpActivity_appuses;//应用使用帮助

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.help_activity_help);
		imageView_common_titlebar_rights=(ImageView) findViewById(R.id.imageView_common_titlebar_right);
		imageView_common_titlebar_rights.setVisibility(View.GONE);
		relative_HelpActivity_deviceuses=(RelativeLayout) findViewById(R.id.relative_HelpActivity_deviceuse);
		relative_HelpActivity_appuses=(RelativeLayout) findViewById(R.id.relative_HelpActivity_appuse);
	}
	
	public void event(){
		super.event();
		relative_HelpActivity_deviceuses.setOnClickListener(this);
		relative_HelpActivity_appuses.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.relative_HelpActivity_deviceuse://设备使用帮助
			startActivity(new Intent(context,WebViewDeviceActivity.class));
			break;
			
		case R.id.relative_HelpActivity_appuse://应用使用帮助
			startActivity(new Intent(context,WebViewDeviceActivity.class));
			break;

		default:
			break;
		}
	}
	
}
