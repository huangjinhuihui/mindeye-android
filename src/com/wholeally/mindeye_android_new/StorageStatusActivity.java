/**
 * 
 */
package com.wholeally.mindeye_android_new;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shilian.mindeye.android.R;

/**
 * @author huangjh
 *
 * 2015-2-10 上午11:40:41
 */
public class StorageStatusActivity extends BaseActivity implements OnClickListener{

	private Context context;
	private TextView textView_common_titlebar_titles;//标题
	private ImageView imageView_common_titlebar_rights;//导航右边图片
	private RelativeLayout relative_storage_status_activity_cards;//存储卡
	private TextView text_storage_status_activity_ifstorages;//是否存储
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_storage_status);
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.storage_status_activity_storage);
		imageView_common_titlebar_rights=(ImageView) findViewById(R.id.imageView_common_titlebar_right);
		imageView_common_titlebar_rights.setVisibility(View.GONE);
		relative_storage_status_activity_cards=(RelativeLayout) findViewById(R.id.relative_storage_status_activity_card);
		text_storage_status_activity_ifstorages=(TextView) findViewById(R.id.text_storage_status_activity_ifstorage);
	}
	
	public void event(){
		super.event();
		relative_storage_status_activity_cards.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.relative_storage_status_activity_card://存储卡
			startActivity(new Intent(context, StorageInfoActivity.class));
			break;

		default:
			break;
		}
	}
	
}
