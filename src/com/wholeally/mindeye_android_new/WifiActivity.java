/**
 * 
 */
package com.wholeally.mindeye_android_new;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.adapter.ListViewAdapterWifiAct;
import com.wholeally.mindeye_android_new.utils.ViewUtils;
import com.wholeally.mindeye_android_new.view.DeviceInfo;

/**
 * @author huangjh
 *
 * 2015-2-5 下午1:59:10
 */
public class WifiActivity extends BaseActivity{

	private Context context;
	private TextView textView_common_titlebar_titles;//标题
	private ImageView imageView_common_titlebar_rights;//导航右边图片
	
	private ListView listView_wifi_activity_lists;
	private ImageView image_wifi_activity_images;
	private TextView text_wifi_activity_nodevices;
	private ArrayList<DeviceInfo> wifiInfoList;
	private ViewUtils wifiUtils;
	private ListViewAdapterWifiAct wifiAdapter;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wifi);
		initView();
	}
	
	public void initView(){
		super.initView();
		context=this;
		textView_common_titlebar_titles=(TextView) findViewById(R.id.textView_common_titlebar_title);
		textView_common_titlebar_titles.setText(R.string.wifi_activity_title);
		imageView_common_titlebar_rights=(ImageView) findViewById(R.id.imageView_common_titlebar_right);
		imageView_common_titlebar_rights.setVisibility(View.GONE);
		listView_wifi_activity_lists=(ListView) findViewById(R.id.listView_wifi_activity_list);
		image_wifi_activity_images=(ImageView) findViewById(R.id.image_wifi_activity_image);
		text_wifi_activity_nodevices=(TextView) findViewById(R.id.text_wifi_activity_nodevice);
		wifiUtils = new ViewUtils(context);
		initDeviceWifiData();
		if(wifiInfoList != null && wifiInfoList.size() > 0)
		{
			image_wifi_activity_images.setVisibility(View.GONE);
			text_wifi_activity_nodevices.setVisibility(View.GONE);
			initListViewData();
		}
		else
		{
			image_wifi_activity_images.setVisibility(View.VISIBLE);
			text_wifi_activity_nodevices.setVisibility(View.VISIBLE);
		}
	}
	
	public void initDeviceWifiData(){
		DeviceInfo wifiInfo1=new DeviceInfo();
		wifiInfo1.setDeviceName("我的摄像机");
		wifiInfo1.setDeviceType("IPC摄像头");
		wifiInfo1.setDeviceID("(LoveSmile)");
		wifiInfo1.setActivation(0);
		
		DeviceInfo wifiInfo2=new DeviceInfo();
		wifiInfo2.setDeviceName("我的摄像机1");
		wifiInfo2.setDeviceType("IPC视频头");
		wifiInfo2.setDeviceID("(smileone)");
		wifiInfo2.setActivation(1);
		
		DeviceInfo wifiInfo3=new DeviceInfo();
		wifiInfo3.setDeviceName("我的摄像机2");
		wifiInfo3.setDeviceType("IPC2");
		wifiInfo3.setDeviceID("(smiletwo)");
		wifiInfo3.setActivation(1);
		
		wifiInfoList=new ArrayList<DeviceInfo>();
		wifiInfoList.add(wifiInfo1);
		wifiInfoList.add(wifiInfo2);
		wifiInfoList.add(wifiInfo3);
	}
	
	public void initListViewData(){
		wifiAdapter = new ListViewAdapterWifiAct(context, wifiInfoList);
		listView_wifi_activity_lists.setAdapter(wifiAdapter);
		listView_wifi_activity_lists.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {

				startActivity(new Intent(context,WifiItemSetActivity.class));
			}
		});
	}
}
