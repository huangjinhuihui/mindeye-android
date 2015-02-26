/**
 * 
 */
package com.wholeally.mindeye_android_new;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.custom.view.CustomDialog;
import com.wholeally.mindeye_android_new.utils.AndroidUtil;

/**
 * @author huangjh
 *
 * 2015-2-5 下午5:40:42
 */
public class FeedBackActivity extends BaseActivity implements OnClickListener{

	private Context context;
	AlertDialog alert_dialog_feedback;
	private TextView textView_common_titlebar_titles;//标题
	private ImageView imageView_common_titlebar_rights;//导航右边图片
	private EditText edit_FeedBackActivity_edits;//反馈内容
	private ImageView image_FeedBackActivity_ones,image_FeedBackActivity_twos,
	                  image_FeedBackActivity_threes,image_FeedBackActivity_fours;//添加图片1、2、3、4
	private RelativeLayout relative_FeedBackActivity_submits;//提交
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		initView();
		event();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.feedback_activity_feedback);
		imageView_common_titlebar_rights=(ImageView) findViewById(R.id.imageView_common_titlebar_right);
		imageView_common_titlebar_rights.setVisibility(View.GONE);
		edit_FeedBackActivity_edits=(EditText) findViewById(R.id.edit_FeedBackActivity_edit);
		image_FeedBackActivity_ones=(ImageView) findViewById(R.id.image_FeedBackActivity_one);
		image_FeedBackActivity_twos=(ImageView) findViewById(R.id.image_FeedBackActivity_two);
		image_FeedBackActivity_threes=(ImageView) findViewById(R.id.image_FeedBackActivity_three);
		image_FeedBackActivity_fours=(ImageView) findViewById(R.id.image_FeedBackActivity_four);
		relative_FeedBackActivity_submits=(RelativeLayout) findViewById(R.id.relative_FeedBackActivity_submit);
	}
	
	public void event(){
		super.event();
		image_FeedBackActivity_ones.setOnClickListener(this);
		image_FeedBackActivity_twos.setOnClickListener(this);
		image_FeedBackActivity_threes.setOnClickListener(this);
		image_FeedBackActivity_fours.setOnClickListener(this);
		relative_FeedBackActivity_submits.setOnClickListener(this);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.image_FeedBackActivity_one://添加图片1
			alert_dialog_feedback=CustomDialog.updateHeadDialog(context, "相册选择", "本地相册", "系统相册", takePhotoListener, photoChooseListener);
			break; 
			
		case R.id.image_FeedBackActivity_two://添加图片2
			alert_dialog_feedback=CustomDialog.updateHeadDialog(context, "相册选择", "本地相册", "系统相册", takePhotoListener, photoChooseListener);
			break;
			
		case R.id.image_FeedBackActivity_three://添加图片3
			alert_dialog_feedback=CustomDialog.updateHeadDialog(context, "相册选择", "本地相册", "系统相册", takePhotoListener, photoChooseListener);
			break;
			
		case R.id.image_FeedBackActivity_four://添加图片4
			alert_dialog_feedback=CustomDialog.updateHeadDialog(context, "相册选择", "本地相册", "系统相册", takePhotoListener, photoChooseListener);
			break;
			
		case R.id.relative_FeedBackActivity_submit://提交
			
			break;

		default:
			break;
		}
	}
	
	/**
	 *本地相册功能监听 
	 */
	OnClickListener takePhotoListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			new AndroidUtil(FeedBackActivity.this).showCamera();
			//alert_dialog_feedback.cancel();
		}
		
	};
	
	/**
	 *系统相册功能监听 
	 */
	OnClickListener photoChooseListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			new AndroidUtil(FeedBackActivity.this).showSystemImage();
			//alert_dialog_feedback.cancel();
		}
		
	};
	
}
