/**
 * 
 */
package com.wholeally.mindeye_android_new;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shilian.mindeye.android.R;

/**
 * @author huangjh
 *
 * 2015-2-13 下午1:35:31
 */
public class RegisterFinishActivity extends BaseActivity implements OnClickListener{

	private Context context;
	/**账号*/
	private EditText edit_RegisterFinishActivity_accounts;
	/**昵称*/
	private EditText edit_RegisterFinishActivity_nicks;
	/**密码*/
	private EditText edit_RegisterFinishActivity_passwords;
	/**确认密码*/
	private EditText edit_RegisterFinishActivity_confirmpasswords;
	/**注册*/
	private RelativeLayout relative_RegisterFinishActivity_registers;
	/** 标题 */
	private TextView textView_common_titlebar_titles;
	/** 完成 */
	private TextView text_common_titlebar_finishs;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_finish);
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles = (TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.register_activity_register);
		text_common_titlebar_finishs=(TextView) findViewById(R.id.text_common_titlebar_finish);
		text_common_titlebar_finishs.setVisibility(View.VISIBLE);
		edit_RegisterFinishActivity_accounts=(EditText) findViewById(R.id.edit_RegisterFinishActivity_account);
		edit_RegisterFinishActivity_nicks=(EditText) findViewById(R.id.edit_RegisterFinishActivity_nick);
		edit_RegisterFinishActivity_passwords=(EditText) findViewById(R.id.edit_RegisterFinishActivity_password);
		edit_RegisterFinishActivity_confirmpasswords=(EditText) findViewById(R.id.edit_RegisterFinishActivity_confirmpassword);
		relative_RegisterFinishActivity_registers=(RelativeLayout) findViewById(R.id.relative_RegisterFinishActivity_register);
	}
	
	public void event(){
		super.event();
		relative_RegisterFinishActivity_registers.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.relative_RegisterFinishActivity_register://注册
			Intent finishs=new Intent(context,MainActivity.class);
			finishs.putExtra("tab_host", "2");   //注册完成时 直接跳转到设备列表 所以序号为2
			startActivity(finishs);
			break;

		default:
			break;
		}
	}
	
}
