/**
 * 
 */
package com.wholeally.mindeye_android_new;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.MindeyeInterface.Init;
import com.wholeally.mindeye_android_new.custom.view.CustomDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * @author huangjh
 *
 * 2015-2-2 下午5:58:07
 */
public class TabMoreActivity extends BaseActivity implements OnClickListener{
	
	AlertDialog alert_dialog_version ;
	private Context context;
	private RelativeLayout relative_TabMoreActivity_accounts;//账号管理
	private RelativeLayout relative_TabMoreActivity_wifis;//wifi设置
	private RelativeLayout relative_TabMoreActivity_liuliangs;//数据流量统计
	private RelativeLayout relative_TabMoreActivity_helps;//帮助
	private RelativeLayout relative_TabMoreActivity_fankuis;//意见反馈
	private RelativeLayout relative_TabMoreActivity_aboutwes;//关于我们
	private RelativeLayout relative_TabMoreActivity_updates;//检查更新
	private RelativeLayout relative_TabMoreActivity_exits;//退出
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		relative_TabMoreActivity_accounts=(RelativeLayout) findViewById(R.id.relative_TabMoreActivity_account);
		relative_TabMoreActivity_wifis=(RelativeLayout) findViewById(R.id.relative_TabMoreActivity_wifi);
		relative_TabMoreActivity_liuliangs=(RelativeLayout) findViewById(R.id.relative_TabMoreActivity_liuliang);
		relative_TabMoreActivity_helps=(RelativeLayout) findViewById(R.id.relative_TabMoreActivity_help);
		relative_TabMoreActivity_fankuis=(RelativeLayout) findViewById(R.id.relative_TabMoreActivity_fankui);
		relative_TabMoreActivity_aboutwes=(RelativeLayout) findViewById(R.id.relative_TabMoreActivity_aboutwe);
		relative_TabMoreActivity_updates=(RelativeLayout) findViewById(R.id.relative_TabMoreActivity_update);
		relative_TabMoreActivity_exits=(RelativeLayout) findViewById(R.id.relative_TabMoreActivity_exit);
	}
	
	public void event(){
		super.event();
		relative_TabMoreActivity_accounts.setOnClickListener(this);
		relative_TabMoreActivity_wifis.setOnClickListener(this);
		relative_TabMoreActivity_liuliangs.setOnClickListener(this);
		relative_TabMoreActivity_helps.setOnClickListener(this);
		relative_TabMoreActivity_fankuis.setOnClickListener(this);
		relative_TabMoreActivity_aboutwes.setOnClickListener(this);
		relative_TabMoreActivity_updates.setOnClickListener(this);
		relative_TabMoreActivity_exits.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.relative_TabMoreActivity_account://账号管理
			startActivity(new Intent(context, AccountManagerActivity.class));
			break;
			
		case R.id.relative_TabMoreActivity_wifi://wifi设置
			startActivity(new Intent(context, WifiActivity.class));
			break;
			
		case R.id.relative_TabMoreActivity_liuliang://数据流量统计
			startActivity(new Intent(context, DataTrafficActivity.class));
			break;
			
		case R.id.relative_TabMoreActivity_help://帮助
			startActivity(new Intent(context, HelpActivity.class));
			break;
			
		case R.id.relative_TabMoreActivity_fankui://意见反馈
			startActivity(new Intent(context, FeedBackActivity.class));
			break;
			
		case R.id.relative_TabMoreActivity_aboutwe://关于我们
			startActivity(new Intent(context, AboutWeActivity.class));
			break;
			
		case R.id.relative_TabMoreActivity_update://检查更新
			alert_dialog_version=CustomDialog.versionUpdateDialog(context, null,"1:调整UI界面;\n2:版本更新;\n3:调整UI界面;\n4:版本更新;\n5:调整UI界面;\n6:版本更新;"
					, updateOnClickListener);
			break;
			
		case R.id.relative_TabMoreActivity_exit://退出
			
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

