package com.wholeally.mindeye_android_new.fragments;

import java.util.ArrayList;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.VideoControlActivity;
import com.wholeally.mindeye_android_new.adapter.ListViewAdapterMessageEvent;
import com.wholeally.mindeye_android_new.adapter.ListViewAdapterStorageCardAct;
import com.wholeally.mindeye_android_new.utils.ViewUtils;
import com.wholeally.mindeye_android_new.view.MessageEvent;
import com.wholeally.mindeye_android_new.view.MonitorDirector;
import com.wholeally.mindeye_android_new.view.TreeElement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class FragmentMessageEvent  extends Fragment
{
	private View view;
	/**是否已全部读*/
	private RelativeLayout relative_FragmentMessageEvent_reads;
	/**消息事件listview*/
	private ListView listView_fragment_message_events;
	private FragmentActivity activity;
	private ViewUtils viewUtils;
	private ArrayList<MessageEvent> messageEventList;
    private ListViewAdapterMessageEvent messageEventAdapter;
	@Override
	public void onAttach(Activity activity)
	{
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		view = inflater.inflate(R.layout.fragment_message_event, null);
		initUI();
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		activity = getActivity();
		viewUtils = new ViewUtils(activity);
		initMessageEventData();
		if(messageEventList != null && messageEventList.size() > 0)
		{
			initListViewMessageEventData();
		}
	}
	
	public void initUI(){
		relative_FragmentMessageEvent_reads=(RelativeLayout) view.findViewById(R.id.relative_FragmentMessageEvent_read);
		listView_fragment_message_events=(ListView) view.findViewById(R.id.listView_fragment_message_event);
	}
	
	public void initMessageEventData(){
		MessageEvent messageEvent1=new MessageEvent();
		messageEvent1.setMessage_source("IPC(HW001)");
		messageEvent1.setMessage_tag_read("未读");
		messageEvent1.setMessage_time("2015-02-17 09:27:55");
		messageEvent1.setMessage_type("移动侦测报警1");
		
		MessageEvent messageEvent2=new MessageEvent();
		messageEvent2.setMessage_source("IPC(HW002)");
		messageEvent2.setMessage_tag_read("未读");
		messageEvent2.setMessage_time("2015-02-17 09:27:55");
		messageEvent2.setMessage_type("移动侦测报警2");
		
		MessageEvent messageEvent3=new MessageEvent();
		messageEvent3.setMessage_source("IPC(HW003)");
		messageEvent3.setMessage_tag_read("已读");
		messageEvent3.setMessage_time("2015-02-17 09:27:55");
		messageEvent3.setMessage_type("移动侦测报警3");
		
		messageEventList=new ArrayList<MessageEvent>();
		messageEventList.add(messageEvent1);
		messageEventList.add(messageEvent2);
		messageEventList.add(messageEvent3);
	}
	
	public void initListViewMessageEventData(){
		messageEventAdapter = new ListViewAdapterMessageEvent(activity, messageEventList);
		listView_fragment_message_events.setAdapter(messageEventAdapter);
		listView_fragment_message_events.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
			
			}
		});
	}
}
