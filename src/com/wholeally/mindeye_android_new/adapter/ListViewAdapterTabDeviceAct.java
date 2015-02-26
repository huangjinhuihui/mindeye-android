/**
 * 
 */
package com.wholeally.mindeye_android_new.adapter;

import java.util.List;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.view.DeviceInfo;
import com.wholeally.mindeye_android_new.view.TreeElement;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author huangjh
 *
 * 2015-2-6 下午2:36:37
 */
public class ListViewAdapterTabDeviceAct extends BaseAdapter{

	private List<DeviceInfo> deviceInfoList;
	private Context context;
	
	//private Activity activity;

	public ListViewAdapterTabDeviceAct(Context context, List<DeviceInfo> deviceInfoList)
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
			convertView = ((Activity) context).getLayoutInflater().inflate(R.layout.listitem_tab_device_activity, null);
			viewHolder.relative_listitem_tab_device_activity_images = (RelativeLayout)convertView.findViewById(R.id.relative_listitem_tab_device_activity_image);
			viewHolder.image_listitem_tab_device_activity_images = (ImageView)convertView.findViewById(R.id.image_listitem_tab_device_activity_image);
			viewHolder.imageView_listitem_tab_video_activity_arrows = (ImageView)convertView.findViewById(R.id.imageView_listitem_tab_video_activity_arrow);
			viewHolder.text_listitem_tab_device_activity_devicenames = (TextView)convertView.findViewById(R.id.text_listitem_tab_device_activity_devicename);
			viewHolder.image_listitem_tab_device_activity_devicetypes = (TextView)convertView.findViewById(R.id.text_listitem_tab_device_activity_devicetype);
			viewHolder.image_listitem_tab_device_activity_deviceids = (TextView)convertView.findViewById(R.id.text_listitem_tab_device_activity_deviceid);
			viewHolder.text_listitem_tab_device_activity_ifonlines=(TextView) convertView.findViewById(R.id.text_listitem_tab_device_activity_ifonline);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		DeviceInfo deviceInfo = deviceInfoList.get(position);
		if(deviceInfo.getDeviceType()=="IPC摄像头"){
			viewHolder.image_listitem_tab_device_activity_images.setImageResource(R.drawable.image_camer_device);
		}else if(deviceInfo.getDeviceType()=="IPC视频头"){
			viewHolder.image_listitem_tab_device_activity_images.setImageResource(R.drawable.image_device_video);
		}else{
			viewHolder.image_listitem_tab_device_activity_images.setImageResource(R.drawable.image_device_moren);
		}
		
		if(deviceInfo.getOnline()==0){
			viewHolder.text_listitem_tab_device_activity_ifonlines.setText(R.string.tab_device_activity_offline);
		}else{
			viewHolder.text_listitem_tab_device_activity_ifonlines.setText(R.string.tab_device_activity_online);
		}
		viewHolder.text_listitem_tab_device_activity_devicenames.setText(deviceInfo.getDeviceName());
		viewHolder.image_listitem_tab_device_activity_deviceids.setText(deviceInfo.getDeviceID());
		viewHolder.image_listitem_tab_device_activity_devicetypes.setText(deviceInfo.getDeviceType());
		
		
		
		return convertView;
	}

	class ViewHolder
	{
		RelativeLayout relative_listitem_tab_device_activity_images;
		ImageView image_listitem_tab_device_activity_images;
		ImageView imageView_listitem_tab_video_activity_arrows;
		TextView text_listitem_tab_device_activity_devicenames;
		TextView image_listitem_tab_device_activity_devicetypes;
		TextView image_listitem_tab_device_activity_deviceids;
		TextView text_listitem_tab_device_activity_ifonlines;
	}
}

