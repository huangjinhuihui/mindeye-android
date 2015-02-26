package com.wholeally.mindeye_android_new.fragments;

import java.util.ArrayList;
import java.util.List;
import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.TabVideoActivity;
import com.wholeally.mindeye_android_new.VideoControlActivity;
import com.wholeally.mindeye_android_new.adapter.ListViewAdapterTabVideoAct;
import com.wholeally.mindeye_android_new.utils.ViewUtils;
import com.wholeally.mindeye_android_new.view.NodeInfo;
import com.wholeally.mindeye_android_new.view.TreeElement;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentVideoRemote  extends Fragment
{
	private View view;
	private ListView listView;
	private ImageView imageView_novideo;
	private TextView textView_novideo;
	private ArrayList<TreeElement> treeElementList;
	private FragmentActivity activity;
	private ViewUtils viewUtils;
	private ListViewAdapterTabVideoAct adapter;

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
		view = inflater.inflate(R.layout.fragment_video_remote, null);
		initUI();
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		activity = getActivity();
		
		viewUtils = new ViewUtils(activity);
		applyFirstLevelData();
		if(treeElementList != null && treeElementList.size() > 0)
		{
			imageView_novideo.setVisibility(View.GONE);
			textView_novideo.setVisibility(View.GONE);
		}
		else
		{
			imageView_novideo.setVisibility(View.VISIBLE);
			textView_novideo.setVisibility(View.VISIBLE);
		}
		
		initListView();
	}

	private void initUI()
	{
		listView = (ListView)view.findViewById(R.id.listView_fragment_video_remote);
		imageView_novideo = (ImageView)view.findViewById(R.id.imageView_fragment_video_remote_novideo);
		textView_novideo = (TextView)view.findViewById(R.id.textView_fragment_video_remote_novideo);
	}

	private void initListView()
	{
		adapter = new ListViewAdapterTabVideoAct(activity, treeElementList);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id)
			{
				TreeElement tElement = treeElementList.get(position);
				NodeInfo nodeInfo = tElement.getNodeInfo();
				// 如果没有子节点，通道节点
				if (!tElement.isMhasChild()) {
						if(nodeInfo.getOnline() == 1 && nodeInfo.getType() == 3)
						{
							Intent i = new Intent();
							i.setClass(activity, VideoControlActivity.class);
							startActivity(i);
							
						}
						else if(nodeInfo.getOnline() == 0)
						{
							viewUtils.showToastCustom(R.string.toast_channel_offline);
						}
				}
				else {
					// 展开，收起处理
					showHiddentreeNode(position);
				}
				
			}
		});
	}
	
	private void applyFirstLevelData()
	{
		//实际操作是发协议申请获得第一级所有节点，现在协议还未定好，暂时自己虚构一些第一级节点
//		***********************虚构第一个第一级树节点**************************************
		NodeInfo nodeInfo1 = new NodeInfo();
		nodeInfo1.setType(2);
		nodeInfo1.setDeviceID("xxxxxxxxx");
		nodeInfo1.setName("xxx的监控");
		nodeInfo1.setOnline(1);
		nodeInfo1.setSubs(2);
		TreeElement treeElement1 = new TreeElement(null, true, 0, false);
		treeElement1.setNodeInfo(nodeInfo1);
		treeElement1.setMhasChild(true);
//		******************************************************************************
		
//		***********************虚构第二个树节点**************************************
		NodeInfo nodeInfo2 = new NodeInfo();
		nodeInfo2.setType(2);
		nodeInfo2.setDeviceID("yyyyyyyyy");
		nodeInfo2.setName("yyy的监控");
		nodeInfo2.setOnline(0);
		nodeInfo1.setSubs(1);
		TreeElement treeElement2 = new TreeElement(null, true, 0, false);
		treeElement2.setNodeInfo(nodeInfo2);
		treeElement2.setMhasChild(true);
//		************************************************************************
		
		treeElementList = new ArrayList<TreeElement>();
		treeElementList.add(treeElement1);
		treeElementList.add(treeElement2);
	}
	
	private void applySecondLevelData(String deviceID, TreeElement tElementFirstLevel)
	{
		//实际操作是发协议申请获得某个第一级节点的第二级节点，现在协议还未定好，暂时自己虚构一些第二级节点
		if(deviceID != null && deviceID.equals("xxxxxxxxx"))
		{
			NodeInfo nodeInfo1_1 = new NodeInfo();
			nodeInfo1_1.setType(3);
			nodeInfo1_1.setDeviceID("xxxxxxxxx");
			nodeInfo1_1.setName("通道1");
			nodeInfo1_1.setOnline(1);
			TreeElement treeElement1_1 = new TreeElement(tElementFirstLevel.getNodeInfo().getName(), false, 1, false);
			treeElement1_1.setNodeInfo(nodeInfo1_1);
			treeElement1_1.setPositionInChildList(0);
			
			NodeInfo nodeInfo1_2 = new NodeInfo();
			nodeInfo1_2.setType(3);
			nodeInfo1_2.setDeviceID("xxxxxxxxx");
			nodeInfo1_2.setName("通道2");
			nodeInfo1_2.setOnline(1);
			TreeElement treeElement1_2 = new TreeElement(tElementFirstLevel.getNodeInfo().getName(), false, 1, false);
			treeElement1_2.setNodeInfo(nodeInfo1_2);
			treeElement1_1.setPositionInChildList(1);
			
			tElementFirstLevel.addChild(treeElement1_1);
			tElementFirstLevel.addChild(treeElement1_2);
			tElementFirstLevel.setMhasChild(true);
		}
		else if(deviceID != null && deviceID.equals("yyyyyyyyy"))
		{
			NodeInfo nodeInfo2_1 = new NodeInfo();
			nodeInfo2_1.setType(3);
			nodeInfo2_1.setDeviceID("yyyyyyyyy");
			nodeInfo2_1.setName("通道1");
			nodeInfo2_1.setOnline(0);
			TreeElement treeElement2_1 = new TreeElement(tElementFirstLevel.getNodeInfo().getName(), false, 1, false);
			treeElement2_1.setNodeInfo(nodeInfo2_1);
			treeElement2_1.setPositionInChildList(0);
			
			tElementFirstLevel.addChild(treeElement2_1);
			tElementFirstLevel.setMhasChild(true);
		}
	}
	
	public void showHiddentreeNode(int position)
	{
		TreeElement clickedElement = treeElementList.get(position);
		
		if (!clickedElement.isMhasChild())
		{
			return;
		}
		
		// 如果节点展开
		if (clickedElement.isExpanded()) {
			clickedElement.setExpanded(false);

			ArrayList<TreeElement> temp = new ArrayList<TreeElement>();
			for (int i = position + 1; i < treeElementList.size(); i++) {
				if (clickedElement.getLevel() >= treeElementList.get(i).getLevel()) {
					break;
				}
				temp.add(treeElementList.get(i));
			}

			treeElementList.removeAll(temp);
			adapter.notifyDataSetChanged();
		}
		else 
		{
			clickedElement.setExpanded(true);
			int level = clickedElement.getLevel();//控制不同级别节点在界面显示时的缩进量
			int nextLevel = level + 1;

			boolean ish = false;
				// 如果是通道节点，不再获取
				if (clickedElement.getChildList().size() > 0) {
					ish = true;
				}
			if (!ish) {
				// 动态添加节点
				applySecondLevelData(clickedElement.getNodeInfo().getDeviceID(), clickedElement);
			}

			// 倒排节点
			for (int i = clickedElement.getChildList().size(); i > 0; i--) {
				TreeElement element = (TreeElement) clickedElement.getChildList().get(i - 1);
				element.setLevel(nextLevel);
				element.setExpanded(false);
				
				//将通道节点在父级的ChildList中的位置记录下来
//						System.out.println("TreeView==i - 1:" + (i - 1));
				int positionInChildList = i - 1;
				element.setPositionInChildList(positionInChildList);
				
				treeElementList.add(position + 1, element);
			}

			adapter.notifyDataSetChanged();
		}
	}
}
