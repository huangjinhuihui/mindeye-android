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
 * 2015-2-13 上午11:29:10
 */
public class RegisterAgreeMentActivity extends BaseActivity implements OnClickListener{
	
	private Context context;
	/** 标题 */
	private TextView textView_common_titlebar_titles;
	/** 标题栏右边 */
	private TextView text_common_titlebar_agrees;//同意
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_xieyi);
		initView();
		event();
	}

	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles = (TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles
				.setText(R.string.register_activity_register_xieyi);
		text_common_titlebar_agrees=(TextView) findViewById(R.id.text_common_titlebar_agree);
		text_common_titlebar_agrees.setVisibility(View.VISIBLE);		
	}
	
	public void event(){
		super.event();
		text_common_titlebar_agrees.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.text_common_titlebar_agree://同意
			
			break;

		default:
			break;
		}
	}
}
