/**
 * 
 */
package com.wholeally.mindeye_android_new;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.adapter.ListViewAdapterStorageCardAct;
import com.wholeally.mindeye_android_new.adapter.ListViewAdapterTabDeviceAct;
import com.wholeally.mindeye_android_new.utils.ViewUtils;
import com.wholeally.mindeye_android_new.view.DeviceInfo;
import com.wholeally.mindeye_android_new.view.MonitorDirector;

/**
 * @author huangjh
 * 
 *         2015-2-15 上午9:35:00
 */
public class StorageCardActivity extends BaseActivity implements
		OnClickListener {

	private Context context;
	/** 标题 */
	private TextView textView_common_titlebar_titles;
	/** 存储卡监控目录文件名 */
	private TextView text_StorageCardActivity_monitoring_directorys;
	/** 返回上层的监控目录 */
	private ImageView image_StorageCardActivity_monitore_backs;
	/** 监控目录列表数据 */
	private ListView listView_StorageCardActivity_lists;
	/** 展开或隐藏底部菜单栏 */
	private LinearLayout relative_StorageCardActivity_bottomenus;
	/** 删除监控 */
	private ImageView image_StorageCardActivity_deletes;
	/** 复制监控 */
	private ImageView image_StorageCardActivity_copys;
	/** 剪切监控 */
	private ImageView image_StorageCardActivity_cutss;
	/** 全选监控 */
	private ImageView image_StorageCardActivity_alls;
	
	private ArrayList<MonitorDirector> monitorDirectorList;
	
	private ListViewAdapterStorageCardAct storageAdapter;
	
	private ViewUtils viewUtils;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_storage_card);
		initView();
		event();
	}

	public void initView() {
		super.initView();
		context = this;
		textView_common_titlebar_titles = (TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles
				.setText(R.string.storage_status_activity_storagecard);
		text_StorageCardActivity_monitoring_directorys = (TextView) findViewById(R.id.text_StorageCardActivity_monitoring_directory);
		image_StorageCardActivity_monitore_backs = (ImageView) findViewById(R.id.image_StorageCardActivity_monitore_back);
		listView_StorageCardActivity_lists = (ListView) findViewById(R.id.listView_StorageCardActivity_list);
		relative_StorageCardActivity_bottomenus = (LinearLayout) findViewById(R.id.relative_StorageCardActivity_bottomenu);
		image_StorageCardActivity_deletes = (ImageView) findViewById(R.id.image_StorageCardActivity_delete);
		image_StorageCardActivity_copys = (ImageView) findViewById(R.id.image_StorageCardActivity_copy);
		image_StorageCardActivity_cutss = (ImageView) findViewById(R.id.image_StorageCardActivity_cuts);
		image_StorageCardActivity_alls=(ImageView) findViewById(R.id.image_StorageCardActivity_all);
		
		viewUtils = new ViewUtils(context);
		initStorageData();
		if(monitorDirectorList != null && monitorDirectorList.size() > 0)
		{
			initListViewStorageData();
		}
	}

	public void event() {
		super.event();
		image_StorageCardActivity_monitore_backs.setOnClickListener(this);
		image_StorageCardActivity_deletes.setOnClickListener(this);
		image_StorageCardActivity_copys.setOnClickListener(this);
		image_StorageCardActivity_cutss.setOnClickListener(this);
		image_StorageCardActivity_alls.setOnClickListener(this);
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
		case R.id.image_StorageCardActivity_monitore_back:// 返回上层的监控目录
			
			break;
		case R.id.image_StorageCardActivity_delete:// 删除监控

			break;

		case R.id.image_StorageCardActivity_copy:// 复制监控

			break;
		case R.id.image_StorageCardActivity_cuts:// 剪切监控

			break;
			
		case R.id.image_StorageCardActivity_all://全选监控
			
			break;

		default:
			break;
		}
	}

	public void initStorageData(){
		MonitorDirector monitorDirector1=new MonitorDirector();
		monitorDirector1.setMonitor_file_name("软件园二期监控1");
		monitorDirector1.setMonitor_file_tiime("2015-02-15 17:11:53");
		
		MonitorDirector monitorDirector2=new MonitorDirector();
		monitorDirector2.setMonitor_file_name("软件园二期监控2");
		monitorDirector2.setMonitor_file_tiime("2015-02-16 17:11:53");
		
		MonitorDirector monitorDirector3=new MonitorDirector();
		monitorDirector3.setMonitor_file_name("软件园二期监控3");
		monitorDirector3.setMonitor_file_tiime("2015-02-17 17:11:53");
		
		monitorDirectorList=new ArrayList<MonitorDirector>();
		monitorDirectorList.add(monitorDirector1);
		monitorDirectorList.add(monitorDirector2);
		monitorDirectorList.add(monitorDirector3);
	}
	
	public void initListViewStorageData(){
		storageAdapter = new ListViewAdapterStorageCardAct(context, monitorDirectorList);
		listView_StorageCardActivity_lists.setAdapter(storageAdapter);
		listView_StorageCardActivity_lists.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				ImageView aa=(ImageView) view.findViewById(R.id.image_listitem_storage_card_activity_image);
				aa.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						startActivity(new Intent(context,VideoControlActivity.class));
					}
				});
				
				CheckBox check=(CheckBox) view.findViewById(R.id.check_StorageCardActivity_check);
				check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton arg0, boolean check_boolean) {
						// TODO Auto-generated method stub
						if(check_boolean){
							bottomMenuVisibility();
						}else{
							bottomMenuGone();
						}
					}
				});
			}
		});
	}
	
	/**底部菜单隐藏*/
	public void bottomMenuGone(){
		TranslateAnimation trans = new TranslateAnimation(0, 0, 0,
				relative_StorageCardActivity_bottomenus.getHeight());
		trans.setDuration(300);
		relative_StorageCardActivity_bottomenus.startAnimation(trans);
		relative_StorageCardActivity_bottomenus.setVisibility(View.GONE);
	}

	/**底部菜单显示*/
	public void bottomMenuVisibility(){
		TranslateAnimation trans = new TranslateAnimation(0, 0,
				relative_StorageCardActivity_bottomenus.getHeight(), 0);
		trans.setDuration(300);
		trans.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}
			@Override
			public void onAnimationEnd(Animation animation) {
				relative_StorageCardActivity_bottomenus
						.setVisibility(View.VISIBLE);
			}
		});
		relative_StorageCardActivity_bottomenus.startAnimation(trans);
	}
}
