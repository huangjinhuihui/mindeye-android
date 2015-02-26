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
import com.wholeally.mindeye_android_new.utils.AndroidUtil;

/**
 * @author huangjh
 *
 * 2015-2-4 上午9:48:49
 */
public class AccountManagerActivity extends BaseActivity implements OnClickListener{

	private Context context;
	AlertDialog alert_dialog_update_head;
	private TextView textView_common_titlebar_titles;//标题
	private ImageView imageView_common_titlebar_rights;//导航右边图片
	private RelativeLayout relative_AccountManagerActivity_nicks;//昵称
	private RelativeLayout relative_AccountManagerActivity_names;//真实姓名
	private RelativeLayout relative_AccountManagerActivity_emails;//邮箱
	private RelativeLayout relative_AccountManagerActivity_updatepasswords;//修改密码
	private ImageView image_AccountManagerActivity_headimages;//头像
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_manager);
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.more_activity_account_manager);
		imageView_common_titlebar_rights=(ImageView) findViewById(R.id.imageView_common_titlebar_right);
		imageView_common_titlebar_rights.setVisibility(View.GONE);
		image_AccountManagerActivity_headimages=(ImageView) findViewById(R.id.image_AccountManagerActivity_headimage);
		relative_AccountManagerActivity_nicks=(RelativeLayout) findViewById(R.id.relative_AccountManagerActivity_nick);
		relative_AccountManagerActivity_names=(RelativeLayout) findViewById(R.id.relative_AccountManagerActivity_name);
		relative_AccountManagerActivity_emails=(RelativeLayout) findViewById(R.id.relative_AccountManagerActivity_email);
		relative_AccountManagerActivity_updatepasswords=(RelativeLayout) findViewById(R.id.relative_AccountManagerActivity_updatepassword);
	}
	
	public void event(){
		super.event();
		image_AccountManagerActivity_headimages.setOnClickListener(this);
		relative_AccountManagerActivity_nicks.setOnClickListener(this);
		relative_AccountManagerActivity_names.setOnClickListener(this);
		relative_AccountManagerActivity_emails.setOnClickListener(this);
		relative_AccountManagerActivity_updatepasswords.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_AccountManagerActivity_headimage://头像
			alert_dialog_update_head=CustomDialog.updateHeadDialog(context, "修改头像","拍照","从相册中选择",takePhotoListener, photoChooseListener);
			break;
			
		case R.id.relative_AccountManagerActivity_nick://昵称
			Intent update_nick = new Intent(context,NickNameMailActivity.class);
			update_nick.putExtra("nametitle","修改昵称");
			startActivity(update_nick);
			break;
			
		case R.id.relative_AccountManagerActivity_name://真实姓名
			Intent update_name = new Intent(context,NickNameMailActivity.class);
			update_name.putExtra("nametitle","修改姓名");
			startActivity(update_name);
			break;
			
		case R.id.relative_AccountManagerActivity_email://邮箱
			Intent update_mail = new Intent(context,NickNameMailActivity.class);
			update_mail.putExtra("nametitle","修改邮箱");
			startActivity(update_mail);
			break;
			
		case R.id.relative_AccountManagerActivity_updatepassword://修改密码
			startActivity(new Intent(context,UpdatePasswordActivity.class));
			break;

		default:
			break;
		}
	}
	
	/**
	 *拍照功能监听 
	 */
	OnClickListener takePhotoListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			new AndroidUtil(AccountManagerActivity.this).showCamera();
			//alert_dialog_update_head.cancel();
		}
		
	};
	
	/**
	 *从相册中选择照片监听 
	 */
	OnClickListener photoChooseListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			new AndroidUtil(AccountManagerActivity.this).showSystemImage();
			//alert_dialog_update_head.cancel();
		}
		
	};
	
}
