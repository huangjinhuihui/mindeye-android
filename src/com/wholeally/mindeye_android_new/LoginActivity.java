/**
 * 
 */
package com.wholeally.mindeye_android_new;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.custom.view.CustomDialog;
import com.wholeally.mindeye_android_new.sharepreference.MyShared;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author huangjh
 *
 * 2015-1-30 下午2:02:21
 */
public class LoginActivity extends BaseActivity implements OnClickListener{
	int tag=5;
	private Context context;
	AlertDialog alert_dialog_version1 ;
	private TextView text_LoginActivity_registers;
	private RelativeLayout relative_LoginActivity_logins;

	public static int screenWidth = 0;
	public static int screenHeight = 0;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);        
		screenWidth = dm.widthPixels; //当前分辨率 宽度
        screenHeight = dm.heightPixels; //当前分辨率高度
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		MyShared.saveData(MyShared.FIRST_LOGIN, 1);
		alert_dialog_version1=CustomDialog.versionUpdateDialog(context,"思维眼 企业版2.0.0","1:调整UI界面;\n2:版本更新;\n3:调整UI界面;\n4:版本更新;\n5:调整UI界面;\n6:版本更新;"
				, updateOnClickListener1);
		relative_LoginActivity_logins=(RelativeLayout) findViewById(R.id.relative_LoginActivity_login);
		text_LoginActivity_registers=(TextView) findViewById(R.id.text_LoginActivity_register);
		text_LoginActivity_registers.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
	}

	public void event(){
		super.event();
		text_LoginActivity_registers.setOnClickListener(this);
		relative_LoginActivity_logins.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.text_LoginActivity_register:
			startActivity(new Intent(context,RegisterActivity.class));
			//alert_dialog_version1=CustomDialog.textInfoErrorDialog(context, "输入信息有误", "请重新填写您的账号信息");
			break;

		case R.id.relative_LoginActivity_login:
			startActivity(new Intent(context,MainActivity.class));
			break;
			
		default:
			break;
		}
	}
	
	OnClickListener updateOnClickListener1 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (tag) {
			case 1:
				
				break;

			default:
				break;
			}
			alert_dialog_version1.cancel();
		}
		
	};
}
