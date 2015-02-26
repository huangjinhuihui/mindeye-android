/**
 * 
 */
package com.wholeally.mindeye_android_new;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
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
 * 2015-2-5 上午9:49:14
 */
public class NickNameMailActivity extends BaseActivity implements OnClickListener{

	private Context context;
	/**
	 * 1:修改昵称;2:修改姓名;3:修改邮箱
	 */
	private int tag = 1;
	private String nametitle="";
	private TextView textView_common_titlebar_titles;//标题
	private ImageView imageView_common_titlebar_rights;//导航右边图片
	private TextView text_NickNameMailActivity_publics;//名称
	private EditText edit_NickNameMailActivity_publics;//编辑名称
	private RelativeLayout relative_NickNameMailActivity_publics;//确认按钮
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nick_name_mail_public);
		nametitle=getIntent().getStringExtra("nametitle");
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		imageView_common_titlebar_rights=(ImageView) findViewById(R.id.imageView_common_titlebar_right);
		imageView_common_titlebar_rights.setVisibility(View.GONE);
		text_NickNameMailActivity_publics=(TextView) findViewById(R.id.text_NickNameMailActivity_public);
		edit_NickNameMailActivity_publics=(EditText) findViewById(R.id.edit_NickNameMailActivity_public);
		relative_NickNameMailActivity_publics=(RelativeLayout) findViewById(R.id.relative_NickNameMailActivity_public);
		if (nametitle!=null&&nametitle.equals("修改昵称")) {
			textView_common_titlebar_titles.setText(nametitle);
			text_NickNameMailActivity_publics.setText(R.string.register_activity_nick);
			edit_NickNameMailActivity_publics.setHint(R.string.nickname_mail_activity_nickcondition);
			tag=1;
		}
		if (nametitle!=null&&nametitle.equals("修改姓名")) {
			textView_common_titlebar_titles.setText(nametitle);
			text_NickNameMailActivity_publics.setText(R.string.account_manager_activity_name);
			edit_NickNameMailActivity_publics.setHint(R.string.nickname_mail_activity_namecondition);
			tag=2;
		}
		if (nametitle!=null&&nametitle.equals("修改邮箱")) {
			textView_common_titlebar_titles.setText(nametitle);
			text_NickNameMailActivity_publics.setText(R.string.account_manager_activity_mail);
			edit_NickNameMailActivity_publics.setHint(R.string.nickname_mail_activity_mailcondition);
			tag=3;
		}
	}
	
	public void event(){
		super.event();
		relative_NickNameMailActivity_publics.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.relative_NickNameMailActivity_public:
			
			break;

		default:
			break;
		}
	}
	
}
