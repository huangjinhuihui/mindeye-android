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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author huangjh
 * 
 *         2015-2-2 下午3:59:06
 */
public class RegisterActivity extends BaseActivity implements OnClickListener {
	
	/** 验证码错误dialog提示 */
	AlertDialog alert_dialog_code ;
	private Context context;
	/** 选择国家和地区 */
	private RelativeLayout relative_RegisterActivity_choosecountrys;
	/** 请输入手机号码 */
	private EditText edit_RegisterActivity_inputphoness;
	/** 请填写验证码 */
	private EditText edit_RegisterActivity_inputcodes;
	/** 点击获取验证码 */
	private LinearLayout linear_RegisterActivity_getcodes;
	/** 是否已阅读注册协议 */
	private CheckBox check_RegisterActivity_checks;
	/** 显示已阅读协议的文本 */
	private TextView text_RegisterActivity_already_reads;
	/** 显示未阅读协议的文本布局 */
	private LinearLayout linear_RegisterActivity_noreads;
	/** 点击进入阅读协议文本 */
	private TextView text_RegisterActivity_readxieyis;
	/** 标题 */
	private TextView textView_common_titlebar_titles;
	/** 标题栏右边 */
	private TextView text_common_titlebar_verifications;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
		event();
	}

	public void initView() {
		super.initView();
		context = this;
		textView_common_titlebar_titles = (TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles
				.setText(R.string.register_activity_register);
		text_common_titlebar_verifications = (TextView) findViewById(R.id.text_common_titlebar_verification);
		text_common_titlebar_verifications.setVisibility(View.VISIBLE);
		relative_RegisterActivity_choosecountrys = (RelativeLayout) findViewById(R.id.relative_RegisterActivity_choosecountry);
		edit_RegisterActivity_inputphoness = (EditText) findViewById(R.id.edit_RegisterActivity_inputphones);
		edit_RegisterActivity_inputcodes = (EditText) findViewById(R.id.edit_RegisterActivity_inputcode);
		linear_RegisterActivity_getcodes = (LinearLayout) findViewById(R.id.linear_RegisterActivity_getcode);
		text_RegisterActivity_already_reads = (TextView) findViewById(R.id.text_RegisterActivity_already_read);
		linear_RegisterActivity_noreads = (LinearLayout) findViewById(R.id.linear_RegisterActivity_noread);
		text_RegisterActivity_readxieyis = (TextView) findViewById(R.id.text_RegisterActivity_readxieyi);
		check_RegisterActivity_checks = (CheckBox) findViewById(R.id.check_RegisterActivity_check);
		if (check_RegisterActivity_checks.isChecked()) {
			text_RegisterActivity_already_reads.setVisibility(View.VISIBLE);
			linear_RegisterActivity_noreads.setVisibility(View.GONE);
		} else {
			text_RegisterActivity_already_reads.setVisibility(View.GONE);
			linear_RegisterActivity_noreads.setVisibility(View.VISIBLE);
		}

		check_RegisterActivity_checks
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean check) {
						// TODO Auto-generated method stub
						if (check) {
							text_RegisterActivity_already_reads
									.setVisibility(View.VISIBLE);
							linear_RegisterActivity_noreads
									.setVisibility(View.GONE);
						//	text_common_titlebar_verifications.setTextColor(context.getResources().getColor(R.color.white));
						} else {
							text_RegisterActivity_already_reads
									.setVisibility(View.GONE);
							linear_RegisterActivity_noreads
									.setVisibility(View.VISIBLE);
						//	text_common_titlebar_verifications.setTextColor(context.getResources().getColor(R.color.blue_two));
						}
					}
				});
	}

	public void event() {
		super.event();
		relative_RegisterActivity_choosecountrys.setOnClickListener(this);
		linear_RegisterActivity_getcodes.setOnClickListener(this);
		text_RegisterActivity_readxieyis.setOnClickListener(this);
		text_common_titlebar_verifications.setOnClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.relative_RegisterActivity_choosecountry:// 选择国家和地区

			break;

		case R.id.linear_RegisterActivity_getcode:// 点击获取验证码
			alert_dialog_code=CustomDialog.textInfoErrorDialog(context, "验证码错误", "请重新点击获取新的验证码");
			break;

		case R.id.text_RegisterActivity_readxieyi:// 点击进入阅读协议
			startActivity(new Intent(context, RegisterAgreeMentActivity.class));
			break;
			
		case R.id.text_common_titlebar_verification://验证
			startActivity(new Intent(context, RegisterFinishActivity.class));
			break;

		default:
			break;
		}
	}

}
