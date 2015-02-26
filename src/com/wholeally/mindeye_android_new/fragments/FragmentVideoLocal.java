package com.wholeally.mindeye_android_new.fragments;

import java.util.ArrayList;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.view.TreeElement;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentVideoLocal  extends Fragment
{
	private View view;
	private ListView listView;
	private ImageView imageView_novideo;
	private TextView textView_novideo;
	private FragmentActivity activity;
	private ArrayList<TreeElement> dataList;

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
		view = inflater.inflate(R.layout.fragment_video_local, null);
		initUI();
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		activity = getActivity();
		initData();
		if(dataList != null && dataList.size() > 0)
		{
			imageView_novideo.setVisibility(View.GONE);
			textView_novideo.setVisibility(View.GONE);
		}
		else
		{
			imageView_novideo.setVisibility(View.VISIBLE);
			textView_novideo.setVisibility(View.VISIBLE);
		}
	}
	
	private void initUI()
	{
		listView = (ListView)view.findViewById(R.id.listView_fragment_video_local);
		imageView_novideo = (ImageView)view.findViewById(R.id.imageView_fragment_video_local_novideo);
		textView_novideo = (TextView)view.findViewById(R.id.textView_fragment_video_local_novideo);
	}
	
	private void initData()
	{
		// TODO Auto-generated method stub
		
	}
}
