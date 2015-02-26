/**
 * 
 */
package com.wholeally.mindeye_android_new;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.shilian.mindeye.android.R;

/**
 * @author huangjh
 *
 * 2015-2-6 上午9:13:55
 */
public class AboutWeActivity extends BaseActivity implements OnClickListener{

	private Context context;
	private TextView textView_common_titlebar_titles;//标题
	private ImageView imageView_common_titlebar_rights;//导航右边图片
	private TextView text_AboutWeActivity_calls;//拨号
	private TextView text_AboutWeActivity_weblinks;//链接
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_we);
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.more_activity_about_we);
		imageView_common_titlebar_rights=(ImageView) findViewById(R.id.imageView_common_titlebar_right);
		imageView_common_titlebar_rights.setVisibility(View.GONE);
		text_AboutWeActivity_calls=(TextView) findViewById(R.id.text_AboutWeActivity_call);
		text_AboutWeActivity_weblinks=(TextView) findViewById(R.id.text_AboutWeActivity_weblink);
		text_AboutWeActivity_weblinks.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		text_AboutWeActivity_weblinks.setTextColor(this.getResources().getColor(R.color.blue_one));
	}
	
	public void event(){
		super.event();
		text_AboutWeActivity_calls.setOnClickListener(this);
		text_AboutWeActivity_weblinks.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.text_AboutWeActivity_call://拨号
			Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + text_AboutWeActivity_calls.getText().toString()));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			break;
			
		case R.id.text_AboutWeActivity_weblink://链接
			Uri uri = Uri.parse(text_AboutWeActivity_weblinks.getText().toString());  
			 Intent intenta = new Intent(Intent.ACTION_VIEW,uri);
			 startActivity(intenta);
			break;

		default:
			break;
		}
	}
	
}
