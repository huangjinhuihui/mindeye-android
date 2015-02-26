/**
 * 
 */
package com.wholeally.mindeye_android_new;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.custom.view.CustomDialog;

/**
 * @author huangjh
 *
 * 2015-2-7 上午11:38:58
 */
public class TabDeviceDetailActivity extends BaseActivity implements OnClickListener{

	AlertDialog alert_dialog_version ;
	private Context context;
	private TextView textView_common_titlebar_titles;//标题
	private ImageView imageView_common_titlebar_rights;//导航右边图片
	private RelativeLayout relative_tab_device_detail_activity_heads;//我的摄像头
	private RelativeLayout relative_tab_device_detail_activity_imagesets;//图像设置
	private RelativeLayout relative_tab_device_detail_activity_videosets;//录像设置
	private RelativeLayout relative_tab_device_detail_activity_voicesets;//声音设置
	private RelativeLayout relative_tab_device_detail_activity_wifinets;//WIFI网络
	private RelativeLayout relative_tab_device_detail_activity_savestatuss;//存储状态
	private RelativeLayout relative_tab_device_detail_activity_deviceversions;//设备版本
	private TextView text_tab_device_detail_activity_textversions;//设备版本号
	private RelativeLayout relative_tab_device_detail_activity_systemsets;//系统设置
	private TextView text_tab_device_detail_activity_textimes;//在线时长
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_device_detail);
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.tab_device_detail_activity_devicedetail);
		imageView_common_titlebar_rights=(ImageView) findViewById(R.id.imageView_common_titlebar_right);
		imageView_common_titlebar_rights.setVisibility(View.GONE);
		relative_tab_device_detail_activity_heads=(RelativeLayout) findViewById(R.id.relative_tab_device_detail_activity_head);
		relative_tab_device_detail_activity_imagesets=(RelativeLayout) findViewById(R.id.relative_tab_device_detail_activity_imageset);
		relative_tab_device_detail_activity_videosets=(RelativeLayout) findViewById(R.id.relative_tab_device_detail_activity_videoset);
		relative_tab_device_detail_activity_voicesets=(RelativeLayout) findViewById(R.id.relative_tab_device_detail_activity_voiceset);
		relative_tab_device_detail_activity_wifinets=(RelativeLayout) findViewById(R.id.relative_tab_device_detail_activity_wifinet);
		relative_tab_device_detail_activity_savestatuss=(RelativeLayout) findViewById(R.id.relative_tab_device_detail_activity_savestatus);
		relative_tab_device_detail_activity_deviceversions=(RelativeLayout) findViewById(R.id.relative_tab_device_detail_activity_deviceversion);
		text_tab_device_detail_activity_textversions=(TextView) findViewById(R.id.text_tab_device_detail_activity_textversion);
		relative_tab_device_detail_activity_systemsets=(RelativeLayout) findViewById(R.id.relative_tab_device_detail_activity_systemset);
		text_tab_device_detail_activity_textimes=(TextView) findViewById(R.id.text_tab_device_detail_activity_textime);
	}
	
	public void event(){
		super.event();
		relative_tab_device_detail_activity_heads.setOnClickListener(this);
		relative_tab_device_detail_activity_imagesets.setOnClickListener(this);
		relative_tab_device_detail_activity_videosets.setOnClickListener(this);
		relative_tab_device_detail_activity_voicesets.setOnClickListener(this);
		relative_tab_device_detail_activity_wifinets.setOnClickListener(this);
		relative_tab_device_detail_activity_savestatuss.setOnClickListener(this);
		relative_tab_device_detail_activity_deviceversions.setOnClickListener(this);
		relative_tab_device_detail_activity_systemsets.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.relative_tab_device_detail_activity_head://我的摄像头
			startActivity(new Intent(context, UpdateDeviceNameActivity.class));
			break;
			
		case R.id.relative_tab_device_detail_activity_imageset://图像设置
			startActivity(new Intent(context, ImageSetActivity.class));
			break;
			
		case R.id.relative_tab_device_detail_activity_videoset://录像设置
			startActivity(new Intent(context, VideoSetActivity.class));
			break;
			
		case R.id.relative_tab_device_detail_activity_voiceset://声音设置
			startActivity(new Intent(context, VoiceSetActivity.class));
			break;
			
		case R.id.relative_tab_device_detail_activity_wifinet://WIFI网络
			startActivity(new Intent(context, WifiNetActivity.class));
			break;
			
		case R.id.relative_tab_device_detail_activity_savestatus://存储状态
			startActivity(new Intent(context, StorageStatusActivity.class));
			break;
			
		case R.id.relative_tab_device_detail_activity_deviceversion://设备版本
			alert_dialog_version=CustomDialog.versionUpdateDialog(context, null,"1:调整UI界面;\n2:版本更新;\n3:调整UI界面;\n4:版本更新;\n5:调整UI界面;\n6:版本更新;"
					, updateOnClickListener);
			break;
			
		case R.id.relative_tab_device_detail_activity_systemset://系统设置
			startActivity(new Intent(context, SystemSetActivity.class));
			break;

		default:
			break;
		}
	}
	
	OnClickListener updateOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			alert_dialog_version.cancel();
		}
		
	};
	
}
