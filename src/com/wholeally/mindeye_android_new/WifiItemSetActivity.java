/**
 * 
 */
package com.wholeally.mindeye_android_new;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shilian.mindeye.android.R;

/**
 * @author huangjh
 *
 * 2015-2-9 上午10:59:16
 */
public class WifiItemSetActivity extends BaseActivity implements OnClickListener{

	private Context context;
	private TextView textView_common_titlebar_titles;//标题
	private ImageView imageView_common_titlebar_rights;//导航右边图片
	private EditText text_WifiItemSetActivity_wifitexts;//wifi名称
	private EditText text_WifiItemSetActivity_wifipasstexts;//wifi密码
	private ImageView image_WifiItemSetActivity_wifiimages;//wifi列表
	private RelativeLayout relative_WifiItemSetActivity_wifilinks;//连接
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wifi_item_set);
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.wifi_activity_title);
		imageView_common_titlebar_rights=(ImageView) findViewById(R.id.imageView_common_titlebar_right);
		imageView_common_titlebar_rights.setVisibility(View.GONE);
		text_WifiItemSetActivity_wifitexts=(EditText) findViewById(R.id.text_WifiItemSetActivity_wifitext);
		image_WifiItemSetActivity_wifiimages=(ImageView) findViewById(R.id.image_WifiItemSetActivity_wifiimage);
		relative_WifiItemSetActivity_wifilinks=(RelativeLayout) findViewById(R.id.relative_WifiItemSetActivity_wifilink);
		text_WifiItemSetActivity_wifipasstexts=(EditText) findViewById(R.id.text_WifiItemSetActivity_wifipasstext);
	}
	
	public void event(){
		super.event();
		image_WifiItemSetActivity_wifiimages.setOnClickListener(this);
		relative_WifiItemSetActivity_wifilinks.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_WifiItemSetActivity_wifiimage://wifi列表
			
			break;
			
		case R.id.relative_WifiItemSetActivity_wifilink://连接
			
			break;

		default:
			break;
		}
	}
	
}
