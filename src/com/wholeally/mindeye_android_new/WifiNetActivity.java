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
 * 2015-2-10 上午11:13:48
 */
public class WifiNetActivity extends BaseActivity implements OnClickListener{

	private Context context;
	private TextView textView_common_titlebar_titles;//标题
	private ImageView imageView_common_titlebar_rights;//导航右边图片
	private EditText text_WifiNetActivity_wifitexts;//网络名称
	private EditText text_WifiNetActivity_wifipasstexts;//网络密码
    private RelativeLayout relative_WifiNetActivity_wifilinks;//连接
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wifi_net);
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.tab_device_detail_activity_wifinet);
		imageView_common_titlebar_rights=(ImageView) findViewById(R.id.imageView_common_titlebar_right);
		imageView_common_titlebar_rights.setVisibility(View.GONE);
		text_WifiNetActivity_wifitexts=(EditText) findViewById(R.id.text_WifiNetActivity_wifitext);
		text_WifiNetActivity_wifipasstexts=(EditText) findViewById(R.id.text_WifiNetActivity_wifipasstext);
		relative_WifiNetActivity_wifilinks=(RelativeLayout) findViewById(R.id.relative_WifiNetActivity_wifilink);
	}
	
	public void event(){
		super.event();
		relative_WifiNetActivity_wifilinks.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.relative_WifiNetActivity_wifilink://连接
			
			break;

		default:
			break;
		}
	}
	
}
