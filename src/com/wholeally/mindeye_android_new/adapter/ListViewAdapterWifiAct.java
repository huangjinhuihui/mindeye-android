/**
 * 
 */
package com.wholeally.mindeye_android_new.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.view.DeviceInfo;

/**
 * @author huangjh
 *
 * 2015-2-9 上午9:22:59
 */
public class ListViewAdapterWifiAct extends BaseAdapter{

	private List<DeviceInfo> deviceInfoList;
	private Context context;
	
	//private Activity activity;

	public ListViewAdapterWifiAct(Context context, List<DeviceInfo> deviceInfoList)
	{
		this.context = context;
		this.deviceInfoList = deviceInfoList;
	}
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return deviceInfoList.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		
		if(convertView == null)
		{
			viewHolder = new ViewHolder();
			convertView = ((Activity) context).getLayoutInflater().inflate(R.layout.listitem_wifi_activity, null);
			viewHolder.relative_wifi_activity_images = (RelativeLayout)convertView.findViewById(R.id.relative_wifi_activity_image);
			viewHolder.image_wifi_activity_images = (ImageView)convertView.findViewById(R.id.image_wifi_activity_image);
			viewHolder.image_wifi_activity_arrows = (ImageView)convertView.findViewById(R.id.image_wifi_activity_arrow);
			viewHolder.text_wifi_activity_devicenames = (TextView)convertView.findViewById(R.id.text_wifi_activity_devicename);
			viewHolder.text_wifi_activity_devicetypes = (TextView)convertView.findViewById(R.id.text_wifi_activity_devicetype);
			viewHolder.text_wifi_activity_deviceids = (TextView)convertView.findViewById(R.id.text_wifi_activity_deviceid);
			viewHolder.text_wifi_activity_deviceactivations=(TextView) convertView.findViewById(R.id.text_wifi_activity_deviceactivation);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		DeviceInfo deviceInfo = deviceInfoList.get(position);
		if(deviceInfo.getDeviceType()=="IPC摄像头"){
			viewHolder.image_wifi_activity_images.setImageResource(R.drawable.image_camer_device);
		}else if(deviceInfo.getDeviceType()=="IPC视频头"){
			viewHolder.image_wifi_activity_images.setImageResource(R.drawable.image_device_video);
		}else{
			viewHolder.image_wifi_activity_images.setImageResource(R.drawable.image_device_moren);
		}
		
		if(deviceInfo.getActivation()==0){
			viewHolder.text_wifi_activity_deviceactivations.setText(R.string.wifi_activity_noactivation);
		}else{
			viewHolder.text_wifi_activity_deviceactivations.setText(R.string.wifi_activity_activation);
		}
		viewHolder.text_wifi_activity_devicenames.setText(deviceInfo.getDeviceName());
		viewHolder.text_wifi_activity_deviceids.setText(deviceInfo.getDeviceID());
		viewHolder.text_wifi_activity_devicetypes.setText(deviceInfo.getDeviceType());
		
		
		
		return convertView;
	}

	class ViewHolder
	{
		RelativeLayout relative_wifi_activity_images;
		ImageView image_wifi_activity_images;
		ImageView image_wifi_activity_arrows;
		TextView text_wifi_activity_devicenames;
		TextView text_wifi_activity_devicetypes;
		TextView text_wifi_activity_deviceids;
		TextView text_wifi_activity_deviceactivations;
	}
}
