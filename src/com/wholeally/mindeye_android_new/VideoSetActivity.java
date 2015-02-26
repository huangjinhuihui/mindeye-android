/**
 * 
 */
package com.wholeally.mindeye_android_new;

import android.content.Context;
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
 * 2015-2-9 下午4:19:33
 */
public class VideoSetActivity extends BaseActivity implements OnClickListener{
   
	private Context context;
	private TextView textView_common_titlebar_titles;//标题
	private ImageView imageView_common_titlebar_rights;//导航右边图片
	private RelativeLayout relative_VideoSetActivity_manualvideos;//手动录像
	private ImageView image_VideoSetActivity_manualimages;//手动录像图片是否显示
	private RelativeLayout relative_VideoSetActivity_entirevideos;//全程录像
	private ImageView image_VideoSetActivity_entireimages;//全程录像图片是否显示
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_set);
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.video_set_activity_videoset);
		imageView_common_titlebar_rights=(ImageView) findViewById(R.id.imageView_common_titlebar_right);
		imageView_common_titlebar_rights.setVisibility(View.GONE);
		relative_VideoSetActivity_manualvideos=(RelativeLayout) findViewById(R.id.relative_VideoSetActivity_manualvideo);
		image_VideoSetActivity_manualimages=(ImageView) findViewById(R.id.image_VideoSetActivity_manualimage);
		relative_VideoSetActivity_entirevideos=(RelativeLayout) findViewById(R.id.relative_VideoSetActivity_entirevideo);
		image_VideoSetActivity_entireimages=(ImageView) findViewById(R.id.image_VideoSetActivity_entireimage);
	}
	
	public void event(){
		super.event();
		relative_VideoSetActivity_manualvideos.setOnClickListener(this);
		relative_VideoSetActivity_entirevideos.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.relative_VideoSetActivity_manualvideo://手动录像
			image_VideoSetActivity_manualimages.setVisibility(View.VISIBLE);
			image_VideoSetActivity_entireimages.setVisibility(View.GONE);
			break;
			
		case R.id.relative_VideoSetActivity_entirevideo://全程录像
			image_VideoSetActivity_manualimages.setVisibility(View.GONE);
			image_VideoSetActivity_entireimages.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}
	
}
