package com.wholeally.mindeye_android_new.adapter;

import java.util.List;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.view.NodeInfo;
import com.wholeally.mindeye_android_new.view.TreeElement;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

class ViewHolder
{
	RelativeLayout layout_thumbnail;
	ImageView iv_thumbnail;
	ImageView iv_arrow;
	TextView tv_title;
	TextView tv_subTitle;
}

public class ListViewAdapterTabVideoAct extends BaseAdapter
{

	private List<TreeElement> treeElementList;
	private Activity activity;

	public ListViewAdapterTabVideoAct(Activity activity, List<TreeElement> treeElementList)
	{
		this.activity = activity;
		this.treeElementList = treeElementList;
	}
	
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return treeElementList.size();
	}

	@Override
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder;
		
		if(convertView == null)
		{
			viewHolder = new ViewHolder();
			convertView = activity.getLayoutInflater().inflate(R.layout.listitem_tab_video_activity, null);
			viewHolder.layout_thumbnail = (RelativeLayout)convertView.findViewById(R.id.layout_listitem_tab_video_activity_thumbnail);
			viewHolder.iv_thumbnail = (ImageView)convertView.findViewById(R.id.imageView_listitem_tab_video_activity_thumbnail);
			viewHolder.iv_arrow = (ImageView)convertView.findViewById(R.id.imageView_listitem_tab_video_activity_arrow);
			viewHolder.tv_title = (TextView)convertView.findViewById(R.id.textView_listitem_tab_video_activity_title);
			viewHolder.tv_subTitle = (TextView)convertView.findViewById(R.id.textView_listitem_tab_video_activity_subtitle);
			
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		TreeElement treeElement = treeElementList.get(position);
		
		boolean isExpanded = treeElement.isExpanded();
		if(isExpanded)
		{
			viewHolder.iv_arrow.setImageResource(R.drawable.icon_listview_arrow_down);
		}
		else
		{
			viewHolder.iv_arrow.setImageResource(R.drawable.icon_listview_arrow_right);
		}
		
		int level = treeElement.getLevel();
		viewHolder.iv_thumbnail.setPadding(25 * level, viewHolder.iv_thumbnail.getPaddingTop(),
				0, viewHolder.iv_thumbnail.getPaddingBottom());
		if(level == 0)
		{
			viewHolder.layout_thumbnail.setBackgroundColor(activity.getResources().getColor(R.color.gray_main_activity_bg));
			viewHolder.iv_thumbnail.setImageResource(R.drawable.icon_listview_tab_video_activity);
		}
		else if(level == 1)
		{
			viewHolder.layout_thumbnail.setBackgroundColor(activity.getResources().getColor(R.color.white));
			viewHolder.iv_thumbnail.setImageResource(R.drawable.icon_listview_channel_tab_video_activity);
		}
		
		
		NodeInfo nodeInfo = treeElement.getNodeInfo();
		viewHolder.tv_title.setText(nodeInfo.getName());
		
		int online = nodeInfo.getOnline();
		if(online == 0 && level == 1)
		{
			viewHolder.tv_subTitle.setText("离线");
			viewHolder.tv_title.setTextColor(activity.getResources().getColor(R.color.gray_common_tab_text));
			viewHolder.tv_subTitle.setTextColor(activity.getResources().getColor(R.color.gray_common_tab_text));
		}
		else if(online == 1 && level == 1)
		{
			viewHolder.tv_subTitle.setText("在线");
			viewHolder.tv_title.setTextColor(activity.getResources().getColor(R.color.black));
			viewHolder.tv_subTitle.setTextColor(activity.getResources().getColor(R.color.black));
		}
		
		return convertView;
	}

}
