/**
 * 
 */
package com.wholeally.mindeye_android_new;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shilian.mindeye.android.R;

/**
 * @author huangjh
 *
 * 2015-2-10 上午10:58:10
 */
public class VoiceSetActivity extends BaseActivity{

	private Context context;
	private TextView textView_common_titlebar_titles;//标题
	private ImageView imageView_common_titlebar_rights;//导航右边图片
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_voice_set);
		initView();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.voice_set_activity_voiceset);
		imageView_common_titlebar_rights=(ImageView) findViewById(R.id.imageView_common_titlebar_right);
		imageView_common_titlebar_rights.setVisibility(View.GONE);
	}
	
}
