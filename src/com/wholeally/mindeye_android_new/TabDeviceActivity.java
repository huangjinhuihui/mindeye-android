package com.wholeally.mindeye_android_new;

import java.util.ArrayList;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.adapter.ListViewAdapterTabDeviceAct;
import com.wholeally.mindeye_android_new.utils.ViewUtils;
import com.wholeally.mindeye_android_new.view.DeviceInfo;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class TabDeviceActivity extends BaseActivity
{
	private Context context;
	private ListView listView_tab_device_lists;
	private ImageView image_tab_device_activity_images;
	private TextView text_tab_device_activity_nodevices;
	private ArrayList<DeviceInfo> deviceInfoList;
	private ViewUtils viewUtils;
	private ListViewAdapterTabDeviceAct deviceAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_device);
		initView();
	}
	
	public void initView(){
		super.initView();
		context=this;
		listView_tab_device_lists=(ListView) findViewById(R.id.listView_tab_device_list);
		image_tab_device_activity_images=(ImageView) findViewById(R.id.image_tab_device_activity_image);
		text_tab_device_activity_nodevices=(TextView) findViewById(R.id.text_tab_device_activity_nodevice);
		viewUtils = new ViewUtils(context);
		initDeviceData();
		if(deviceInfoList != null && deviceInfoList.size() > 0)
		{
			image_tab_device_activity_images.setVisibility(View.GONE);
			text_tab_device_activity_nodevices.setVisibility(View.GONE);
			initListViewData();
		}
		else
		{
			image_tab_device_activity_images.setVisibility(View.VISIBLE);
			text_tab_device_activity_nodevices.setVisibility(View.VISIBLE);
		}
	}
	
	public void initDeviceData(){
		DeviceInfo deviceInfo1=new DeviceInfo();
		deviceInfo1.setDeviceName("我的摄像机");
		deviceInfo1.setDeviceType("IPC摄像头");
		deviceInfo1.setDeviceID("(LoveSmile)");
		deviceInfo1.setOnline(0);
		
		DeviceInfo deviceInfo2=new DeviceInfo();
		deviceInfo2.setDeviceName("我的摄像机1");
		deviceInfo2.setDeviceType("IPC视频头");
		deviceInfo2.setDeviceID("(smileone)");
		deviceInfo2.setOnline(1);
		
		DeviceInfo deviceInfo3=new DeviceInfo();
		deviceInfo3.setDeviceName("我的摄像机2");
		deviceInfo3.setDeviceType("IPC2");
		deviceInfo3.setDeviceID("(smiletwo)");
		deviceInfo3.setOnline(0);
		
		deviceInfoList=new ArrayList<DeviceInfo>();
		deviceInfoList.add(deviceInfo1);
		deviceInfoList.add(deviceInfo2);
		deviceInfoList.add(deviceInfo3);
	}
	
	public void initListViewData(){
		deviceAdapter = new ListViewAdapterTabDeviceAct(context, deviceInfoList);
		listView_tab_device_lists.setAdapter(deviceAdapter);
		listView_tab_device_lists.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
//				DeviceInfo device=deviceInfoList.get(position);
//				if(device == null || "".equals(device)){
//					
//				}
				startActivity(new Intent(context,TabDeviceDetailActivity.class));
			}
		});
	}

}
