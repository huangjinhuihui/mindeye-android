/**
 * 
 */
package com.wholeally.mindeye_android_new;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.shilian.mindeye.android.R;

/**
 * @author huangjh
 * 
 *         2015-2-12 上午10:57:31
 */
public class StorageInfoActivity extends BaseActivity implements
		OnClickListener {

	private Context context;
	private TextView textView_common_titlebar_titles;// 标题
	private TextView text_StorageInfoActivity_usesizes;// 存储卡使用的大小
	private TextView text_storage_info_activity_sizes;// 存储卡容量大小
	private SeekBar seekbar_storage_info_activity_nothumbs;// 不能拖动
	private SeekBar seekbar_StorageInfoActivity_warnthumbs;// 可以拖动预警百分比
	private TextView text_StorageInfoActivity_warnpercents;// 预警百分比
	private RelativeLayout relative_StorageInfoActivity_lookcontents;// 查看内容
	private int seek_progress = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_storage_info);
		initView();
		event();
	}

	public void initView() {
		super.initView();
		context = this;
		seekbar_storage_info_activity_nothumbs = (SeekBar) findViewById(R.id.seekbar_storage_info_activity_nothumb);
		seekbar_storage_info_activity_nothumbs.setProgress(60);
		seekbar_storage_info_activity_nothumbs
				.setOnTouchListener(new OnTouchListener() {
					public boolean onTouch(View arg0, MotionEvent event) {
						return true;// 返回true为拖动不了,false为可以拖动
					}
				});
		textView_common_titlebar_titles = (TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles
				.setText(R.string.storage_info_activity_titlename);
		text_StorageInfoActivity_usesizes = (TextView) findViewById(R.id.text_StorageInfoActivity_usesize);
		text_storage_info_activity_sizes = (TextView) findViewById(R.id.text_storage_info_activity_size);
		text_StorageInfoActivity_warnpercents = (TextView) findViewById(R.id.text_StorageInfoActivity_warnpercent);
		relative_StorageInfoActivity_lookcontents = (RelativeLayout) findViewById(R.id.relative_StorageInfoActivity_lookcontent);
		seekbar_StorageInfoActivity_warnthumbs = (SeekBar) findViewById(R.id.seekbar_StorageInfoActivity_warnthumb);
		seekbar_StorageInfoActivity_warnthumbs
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekBar seekbar) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onStartTrackingTouch(SeekBar seekbar) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onProgressChanged(SeekBar seekbar,
							int progress, boolean arg2) {
						if (progress == 0) {
							text_StorageInfoActivity_warnpercents.setText("("
									+ progress + "%" + ")");
						}
						if (0 < progress && progress < 10) {
							seek_progress = 10;
							seekbar.setProgress(seek_progress);
							text_StorageInfoActivity_warnpercents.setText("("
									+ seek_progress + "%" + ")");
						}
						if (10 < progress && progress < 20) {
							seek_progress = 20;
							seekbar.setProgress(seek_progress);
							text_StorageInfoActivity_warnpercents.setText("("
									+ seek_progress + "%" + ")");
						}
						if (20 < progress && progress < 30) {
							seek_progress = 30;
							seekbar.setProgress(seek_progress);
							text_StorageInfoActivity_warnpercents.setText("("
									+ seek_progress + "%" + ")");
						}
						if (30 < progress && progress < 40) {
							seek_progress = 40;
							seekbar.setProgress(seek_progress);
							text_StorageInfoActivity_warnpercents.setText("("
									+ seek_progress + "%" + ")");
						}
						if (40 < progress && progress < 50) {
							seek_progress = 50;
							seekbar.setProgress(seek_progress);
							text_StorageInfoActivity_warnpercents.setText("("
									+ seek_progress + "%" + ")");
						}
						if (50 < progress && progress < 60) {
							seek_progress = 60;
							seekbar.setProgress(seek_progress);
							text_StorageInfoActivity_warnpercents.setText("("
									+ seek_progress + "%" + ")");
						}
						if (60 < progress && progress < 70) {
							seek_progress = 70;
							seekbar.setProgress(seek_progress);
							text_StorageInfoActivity_warnpercents.setText("("
									+ seek_progress + "%" + ")");
						}
						if (70 < progress && progress < 80) {
							seek_progress = 80;
							seekbar.setProgress(seek_progress);
							text_StorageInfoActivity_warnpercents.setText("("
									+ seek_progress + "%" + ")");
						}
						if (80 < progress && progress < 90) {
							seek_progress = 90;
							seekbar.setProgress(seek_progress);
							text_StorageInfoActivity_warnpercents.setText("("
									+ seek_progress + "%" + ")");
						}
						if (90 < progress && progress <= 100) {
							seek_progress = 100;
							seekbar.setProgress(seek_progress);
							text_StorageInfoActivity_warnpercents.setText("("
									+ seek_progress + "%" + ")");
						}
					}
				});
	}

	public void event() {
		super.event();
		relative_StorageInfoActivity_lookcontents.setOnClickListener(this);
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
		case R.id.relative_StorageInfoActivity_lookcontent:// 查看内容
            startActivity(new Intent(context,StorageCardActivity.class));
			break;

		default:
			break;
		}
	}

}
