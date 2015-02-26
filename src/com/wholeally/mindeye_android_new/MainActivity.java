package com.wholeally.mindeye_android_new;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.MindeyeInterface.Init;
import com.wholeally.mindeye_android_new.custom.view.CustomDialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity implements Init, OnClickListener
{
	/**1:扫描添加设备;2:手动添加设备*/
	public static int tag=1;
    private Context context;
	private TabHost tabHost;
	private TabWidget mTabWidget;
	private View view;
	/**标题*/
	private TextView titlebarTitle;
	/**返回图标*/
	private ImageView back;
	/**titlebar右边的那个图标*/
	private ImageView titlebarRight;
	
	AlertDialog alert_dialog_addevice ;
	/**从注册完成页面传递过来的值*/
	private String tab_position;
	/**显示底部的tabhost当前显示的是哪项(注册完成时直接跳转到设备列表 tag_tab=2,否则 tag_tab=0为视频)*/
	private int tag_tab;
	
    /**添加设备*/
	private ImageView addeviceRight;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		event();
	}

	@Override
	public void initView()
	{
		context=this;
		titlebarTitle = (TextView)findViewById(R.id.textView_common_titlebar_title);//标题
		titlebarTitle.setText(R.string.tab_video_activity_titlebar_title);//设置默认titlebar的标题为“视频”
		back = (ImageView)findViewById(R.id.imageView_common_titlebar_left);//返回图标
		addeviceRight=(ImageView) findViewById(R.id.imageView_common_titlebar_rightadd);
		titlebarRight = (ImageView)findViewById(R.id.imageView_common_titlebar_right);//titlebar右边的那个图标
		titlebarRight.setVisibility(View.VISIBLE);
		tab_position=getIntent().getStringExtra("tab_host");
		if(tab_position==null||tab_position==""){
			tag_tab=0;
		}else{
			tag_tab=Integer.valueOf(tab_position);
			titlebarRight.setVisibility(View.GONE);
			addeviceRight.setVisibility(View.VISIBLE);
		}
		initTab();
	}

	@Override
	public void event()
	{
		// TODO Auto-generated method stub
		addeviceRight.setOnClickListener(this);
	}

	public void goBack()
	{
		// TODO Auto-generated method stub
		
	}
	
	private void initTab()
	{
		tabHost = getTabHost();
		mTabWidget = tabHost.getTabWidget();
		
		//视频
		view = getLayoutInflater().inflate(R.layout.common_tab_main_indicator, null);
		view.findViewById(R.id.ImageView_common_tab_icon).setBackgroundResource(R.drawable.selector_main_tab_video);
		TabSpec tabVideo = tabHost.newTabSpec("tabVideo");
		tabVideo.setIndicator(view);
		Intent tabVideoIntent = new Intent(this, TabVideoActivity.class);
		tabVideo.setContent(tabVideoIntent);

		//消息
		view = getLayoutInflater().inflate(R.layout.common_tab_main_indicator, null);
		view.findViewById(R.id.ImageView_common_tab_icon).setBackgroundResource(R.drawable.selector_main_tab_message);
		TabSpec tabMessage = tabHost.newTabSpec("tabMessage");
		tabMessage.setIndicator(view);
		Intent tabYuntaiIntent = new Intent(this, TabMessageActivity.class);
		tabMessage.setContent(tabYuntaiIntent);
		
		//设备
		view = getLayoutInflater().inflate(R.layout.common_tab_main_indicator, null);
		view.findViewById(R.id.ImageView_common_tab_icon).setBackgroundResource(R.drawable.selector_main_tab_device);
		TabSpec tabDevice = tabHost.newTabSpec("tabDevice");
		tabDevice.setIndicator(view);
		Intent tabMapIntent = new Intent(this, TabDeviceActivity.class);
		tabDevice.setContent(tabMapIntent);
		
		//更多
		view = getLayoutInflater().inflate(R.layout.common_tab_main_indicator, null);
		view.findViewById(R.id.ImageView_common_tab_icon).setBackgroundResource(R.drawable.selector_main_tab_more);
		TabSpec tabMore = tabHost.newTabSpec("tabMore");
		tabMore.setIndicator(view);
		Intent tabMoreIntent = new Intent(this, TabMoreActivity.class);
		tabMore.setContent(tabMoreIntent);

		// 添加tab
		tabHost.addTab(tabVideo);
		tabHost.addTab(tabMessage);
		tabHost.addTab(tabDevice);
		tabHost.addTab(tabMore);

		// 设置默认选中的tab页
		tabHost.setCurrentTab(tag_tab);
		
		//设置tab标签点击监听事件
		//设置视频tab标签点击监听事件
		mTabWidget.getChildAt(0).setOnClickListener(new OnClickListener() {     
		    @Override  
		    public void onClick(View v) 
		    {  
		    	titlebarTitle.setText(R.string.tab_video_activity_titlebar_title);//设置titlebar的标题为“视频”
		    	titlebarRight.setVisibility(View.VISIBLE);
		    	addeviceRight.setVisibility(View.GONE);
		    	titlebarRight.setImageResource(R.drawable.selector_title_bar_refresh);//设置titlebar右侧为刷新的图标
				tabHost.setCurrentTab(0);
		    }  
		});  
		
		//设置消息tab标签点击监听事件
		mTabWidget.getChildAt(1).setOnClickListener(new OnClickListener() {     
		    @Override  
		    public void onClick(View v) 
		    {  
		    	titlebarTitle.setText(R.string.tab_message_activity_titlebar_title);//设置titlebar的标题为“消息”
		    	titlebarRight.setVisibility(View.GONE);
		    	addeviceRight.setVisibility(View.GONE);
				tabHost.setCurrentTab(1);
		    }  
		});  
		
		//设置设备tab标签点击监听事件
		mTabWidget.getChildAt(2).setOnClickListener(new OnClickListener() {     
		    @Override  
		    public void onClick(View v) 
		    {  
		    	titlebarTitle.setText(R.string.tab_device_activity_titlebar_title);//设置titlebar的标题为“设备”
		    	titlebarRight.setVisibility(View.GONE);
		    	addeviceRight.setVisibility(View.VISIBLE);//设置titlebar右侧为“+”的图标
				tabHost.setCurrentTab(2);
		    }  
		});  
		
		//设置更多tab标签点击监听事件
		mTabWidget.getChildAt(3).setOnClickListener(new OnClickListener() {     
		    @Override  
		    public void onClick(View v) 
		    {  
		    	titlebarTitle.setText(R.string.tab_more_activity_titlebar_title);//设置titlebar的标题为“更多”
		    	titlebarRight.setVisibility(View.GONE);
		    	addeviceRight.setVisibility(View.GONE);
				tabHost.setCurrentTab(3);
		    }  
		});  
		
		// 标签页切换监听事件
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				System.out.println("Tab==tabId " + tabId);
			}
		});
		
	}

	/* (non-Javadoc)
	 * @see com.wholeally.mindeye_android_new.MindeyeInterface.Init#myOnClick(android.view.View)
	 */
	@Override
	public void myOnClick(View v) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.wholeally.mindeye_android_new.MindeyeInterface.Init#goBack(android.view.View)
	 */
	@Override
	public void goBack(View v) {
		// TODO Auto-generated method stub
		finish();
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imageView_common_titlebar_rightadd://添加设备
			alert_dialog_addevice=CustomDialog.addDeviceDialog(context,updateOnClickListener);
			break;
			
		case R.id.imageView_common_titlebar_right://刷新
		
	     	break;

		default:
			break;
		}
	}
	
	OnClickListener updateOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (CustomDialog.tag) {
			case 1://扫描添加设备
				startActivity(new Intent(context, CaptureActivity.class));
				break;
				
			case 2://手动添加设备
				startActivity(new Intent(context, ManualAddDeviceActivity.class));
				break;

			default:
				break;
			}
			CustomDialog.tag=1;//让其初始为默认选中 扫描添加设备
			alert_dialog_addevice.cancel();
		}
		
	};

}
