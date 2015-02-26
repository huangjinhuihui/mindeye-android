/**
 * 
 */
package com.wholeally.mindeye_android_new.adapter;

import java.util.List;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.view.MessageEvent;

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
 *         2015-2-16 下午5:00:58
 */
public class ListViewAdapterMessageEvent extends BaseAdapter {

	private List<MessageEvent> messageEventList;
	private Context context;

	public ListViewAdapterMessageEvent(Context context,
			List<MessageEvent> messageEventList) {
		this.context = context;
		this.messageEventList = messageEventList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return messageEventList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;

		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = ((Activity) context).getLayoutInflater().inflate(
					R.layout.listitem_fragment_message_event, null);
			viewHolder.text_FragmentMessageEvent_times = (TextView) convertView
					.findViewById(R.id.text_FragmentMessageEvent_time);
			viewHolder.text_FragmentMessageEvent_messagetypejsons = (TextView) convertView
					.findViewById(R.id.text_FragmentMessageEvent_messagetypejson);
			viewHolder.text_FragmentMessageEvent_messagesourcejsons = (TextView) convertView
					.findViewById(R.id.text_FragmentMessageEvent_messagesourcejson);
			viewHolder.text_FragmentMessageEvent_tagreads = (TextView) convertView
					.findViewById(R.id.text_FragmentMessageEvent_tagread);
			viewHolder.image_FragmentMessageEvent_images = (ImageView) convertView
					.findViewById(R.id.image_FragmentMessageEvent_image);
			viewHolder.relative_FragmentMessageEvent_scenevideos = (RelativeLayout) convertView
					.findViewById(R.id.relative_FragmentMessageEvent_scenevideo);
			viewHolder.relative_FragmentMessageEvent_messagevideos = (RelativeLayout) convertView
					.findViewById(R.id.relative_FragmentMessageEvent_messagevideo);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		MessageEvent messageEvent = messageEventList.get(position);
		viewHolder.text_FragmentMessageEvent_times
				.setText(messageEvent.getMessage_time());
		viewHolder.text_FragmentMessageEvent_messagetypejsons
				.setText(messageEvent.getMessage_type());
		viewHolder.text_FragmentMessageEvent_messagesourcejsons
				.setText(messageEvent.getMessage_source());
        viewHolder.text_FragmentMessageEvent_tagreads.setText(messageEvent.getMessage_tag_read());
		return convertView;
	}

	class ViewHolder {
		RelativeLayout relative_FragmentMessageEvent_messagevideos;//消息录像
		RelativeLayout relative_FragmentMessageEvent_scenevideos;//现场视频
		ImageView image_FragmentMessageEvent_images;//消息图片
		TextView text_FragmentMessageEvent_tagreads;//消息是否已读
		TextView text_FragmentMessageEvent_messagesourcejsons;//消息来源;
		TextView text_FragmentMessageEvent_messagetypejsons;//消息类型
		TextView text_FragmentMessageEvent_times;//消息时间
	}
}
