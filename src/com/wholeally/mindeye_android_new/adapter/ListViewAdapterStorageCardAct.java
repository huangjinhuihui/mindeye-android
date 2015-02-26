/**
 * 
 */
package com.wholeally.mindeye_android_new.adapter;

import java.util.List;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.adapter.ListViewAdapterTabDeviceAct.ViewHolder;
import com.wholeally.mindeye_android_new.view.DeviceInfo;
import com.wholeally.mindeye_android_new.view.MonitorDirector;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author huangjh
 *
 * 2015-2-15 下午3:56:34
 */
public class ListViewAdapterStorageCardAct extends BaseAdapter{

	private List<MonitorDirector> monitorDirectorList;
	private Context context;
	
	public ListViewAdapterStorageCardAct(Context context, List<MonitorDirector> monitorDirectorList)
	{
		this.context = context;
		this.monitorDirectorList = monitorDirectorList;
	}
	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return monitorDirectorList.size();
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
		if(convertView==null){
			viewHolder = new ViewHolder();
			convertView = ((Activity) context).getLayoutInflater().inflate(R.layout.listitem_storage_card_activity, null);
			viewHolder.relative_listitem_storage_card_activity_images=(RelativeLayout) convertView.findViewById(R.id.relative_listitem_storage_card_activity_image);
			viewHolder.text_listitem_storage_card_activity_storagenames = (TextView)convertView.findViewById(R.id.text_listitem_storage_card_activity_storagename);
			viewHolder.text_listitem_storage_card_activity_storagetimes = (TextView)convertView.findViewById(R.id.text_listitem_storage_card_activity_storagetime);
			viewHolder.image_listitem_storage_card_activity_images = (ImageView)convertView.findViewById(R.id.image_listitem_storage_card_activity_image);
			viewHolder.check_StorageCardActivity_checks = (CheckBox)convertView.findViewById(R.id.check_StorageCardActivity_check);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		MonitorDirector monitorDirector=monitorDirectorList.get(position);
		viewHolder.text_listitem_storage_card_activity_storagenames.setText(monitorDirector.getMonitor_file_name());
		viewHolder.text_listitem_storage_card_activity_storagetimes.setText(monitorDirector.getMonitor_file_tiime());
		
		return convertView;
	}
	
	class ViewHolder
	{
		RelativeLayout relative_listitem_storage_card_activity_images;
		TextView text_listitem_storage_card_activity_storagenames;
		TextView text_listitem_storage_card_activity_storagetimes;
		CheckBox check_StorageCardActivity_checks;
		ImageView image_listitem_storage_card_activity_images;
	}

}
