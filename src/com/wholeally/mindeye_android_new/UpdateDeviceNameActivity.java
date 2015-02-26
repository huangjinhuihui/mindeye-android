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
 * 2015-2-7 下午4:56:29
 */
public class UpdateDeviceNameActivity extends BaseActivity implements OnClickListener{

	private Context context;
	private TextView textView_common_titlebar_titles;//标题
	private ImageView imageView_common_titlebar_rights;//导航右边图片
	private EditText edit_UpdateDeviceNameActivity_edits;//填写设备名称
	private RelativeLayout relative_UpdateDeviceNameActivity_confirms;//确认
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_device_name);
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.update_device_name_activity_name);
		imageView_common_titlebar_rights=(ImageView) findViewById(R.id.imageView_common_titlebar_right);
		imageView_common_titlebar_rights.setVisibility(View.GONE);
		edit_UpdateDeviceNameActivity_edits=(EditText) findViewById(R.id.edit_UpdateDeviceNameActivity_edit);
		relative_UpdateDeviceNameActivity_confirms=(RelativeLayout) findViewById(R.id.relative_UpdateDeviceNameActivity_confirm);
	}
	
	public void event(){
		super.event();
		relative_UpdateDeviceNameActivity_confirms.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.relative_UpdateDeviceNameActivity_confirm://确认
			
			break;

		default:
			break;
		}
	}
	
}
