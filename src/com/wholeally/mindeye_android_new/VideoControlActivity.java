package com.wholeally.mindeye_android_new;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.constant.Constants;
import com.wholeally.mindeye_android_new.custom.view.PieButtonLayout;
import com.wholeally.mindeye_android_new.store.TempValue;
import com.wholeally.mindeye_android_new.utils.AndroidEnv;
import com.wholeally.mindeye_android_new.utils.ViewUtils;
import com.wholeally.mindeye_android_new.view.NodeInfo;
import com.wholeally.mindeye_android_new.view.SoundMeter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class VideoControlActivity extends BaseActivity
{
	private Animation slideLeftIn;
	private Animation slideLeftOut;
	private Animation slideRightIn;
	private Animation slideRightOut;
	private List<NodeInfo> nodeList;
	private View viewFlipperPageView;
	private ViewFlipper viewFlipper;
	private List<View> viewList = new ArrayList<View>();
	private SurfaceView surfaceView_videoctrl;
	private Configuration configuration;
	private GestureDetector detector;
	private long lastChangeChannelTime = System.currentTimeMillis();//默认最后操作切屏时间
	private long cuttime = System.currentTimeMillis();
	private int position;
	/**标题栏左侧返回图标*/
	private ImageView imageView_back;
	/**标题栏标题*/
	private TextView titlebar_title;
	/**标题栏右侧图标*/
	private ImageView titlebar_right;
	/**底部菜单layout*/
	private LinearLayout layout_video_control_menu;
	/**底部菜单选项--画质*/
	private ImageView imageView_resolution;
	/**底部菜单选项--翻转*/
	private ImageView imageView_flip;
	/**底部菜单选项--云台*/
	private ImageView imageView_yuntai;
	/**底部菜单选项--录像*/
	private ImageView imageView_recordVideo;
	/**底部菜单选项--回放*/
	private ImageView imageView_playback;
	/**底部菜单选项--截图*/
	private ImageView imageView_screenshot;
	/**底部菜单选项--照片*/
	private ImageView imageView_photo;
	/**底部菜单选项--布防*/
	private ImageView imageView_deployProtection;
	/**底部菜单选项--对讲*/
	private ImageView imageView_talk;
	/**查询设定通道参数权限--0代表无，1代表有*/
	private String right_QuerySetChannelParameters;
	/**控制云台权限--0代表无  1代表有*/
	private String right_YunTaiControl;
	/**语音对讲权限--0代表无  1代表有*/
	private String right_Talk;
	/**录像回放权限--0代表无  1代表有*/
	private String right_Playback;
	/**视频中转权限--0不能中转，只有直连   1可以中转*/
	private String right_VideoTransit;
	/**视频观看重连权限--0不重连  1重连*/
	private String right_VideoReconnection;
	/**主码流限制--0限制   1允许*/
	private String right_Stream;
	private ViewUtils viewUtils;
	/**底部布局总layout*/
	private RelativeLayout layout_video_control_bottom;
	/**底部对讲layout*/
	private LinearLayout layout_video_control_talk;
	/**底部对讲layout--关闭*/
	private ImageView imageView_talk_close;
	/**底部对讲layout--按住对讲*/
	private ImageView imageView_talk_switch;
	/**自定义AlertDialog--画质选择*/
	private AlertDialog customAlertDialogResolution;
	/**画质--高清*/
	private TextView textView_resolution_HD;
	/**画质--标清*/
	private TextView textView_resolution_SD;
	/**画质--流畅*/
	private TextView textView_resolution_LD;
	/**画质--取消*/
	private TextView textView_resolution_cancel;
	/**自定义AlertDialog--回放选择*/
	private AlertDialog customAlertDialogPlayback;
	/**回放--本地*/
	private TextView textView_playback_local;
	/**回放--远程*/
	private TextView textView_playback_remote;
	/**回放--取消*/
	private TextView textView_playback_cancel;
	/**自定义AlertDialog--截图*/
	private AlertDialog customAlertDialogScreenshot;
	/**截图--命名输入框*/
	private EditText editText_screenshot_name;
	/**截图--跳过*/
	private Button button_screenshot_ignore;
	/**截图--确定*/
	private Button button_screenshot_confirm;
	/**自定义AlertDialog--录像*/
	private AlertDialog customAlertDialogRecordvideo;
	/**录像--命名输入框*/
	private EditText editText_recordvideo_name;
	/**录像--跳过*/
	private Button button_recordvideo_ignore;
	/**录像--确定*/
	private Button button_recordvideo_confirm;
	/**底部录像layout*/
	private LinearLayout layout_video_control_recordvideo;
	/**底部录像layout--关闭*/
	private ImageView imageView_recordvideo_close;
	/**底部录像layout--录像时闪烁的红点*/
	private ImageView imageView_recordvideo_redcircle;
	/**底部录像layout--切换*/
	private ImageView imageView_recordvideo_switch;
	/**底部录像layout--录像时长*/
	private TextView textView_recordvideo_time;
	/**底部云台layout*/
	private LinearLayout layout_video_control_yuntai;
	/**底部云台layout--关闭*/
	private ImageView imageView_yuntai_close;
	/**底部云台layout--变倍，减*/
	private ImageView imageView_yuntai_zoom_reduce;
	/**底部云台layout--变倍，加*/
	private ImageView imageView_yuntai_zoom_add;
	/**底部云台layout--变倍ProgressBar*/
	private ProgressBar progressBar_yuntai_zoom;
	/**底部云台layout--光圈，减*/
	private ImageView imageView_yuntai_aperture_reduce;
	/**底部云台layout--光圈，加*/
	private ImageView imageView_yuntai_aperture_add;
	/**底部云台layout--光圈ProgressBar*/
	private ProgressBar progressBar_yuntai_aperture;
	/**底部云台layout--焦距，减*/
	private ImageView imageView_yuntai_focus_reduce;
	/**底部云台layout--焦距，加*/
	private ImageView imageView_yuntai_focus_add;
	/**底部云台layout--焦距ProgressBar*/
	private ProgressBar progressBar_yuntai_focus;
	/**自定义AlertDialog--翻转*/
	private AlertDialog customAlertDialogFlip;
	/**水平翻转CheckBox*/
	private CheckBox checkBox_flip_horizontal;
	/**垂直翻转CheckBox*/
	private CheckBox checkBox_flip_vertical;
	/**翻转--取消*/
	private Button button_flip_cancel;
	/**翻转--确定*/
	private Button button_flip_confirm;
	private MainClickListener mainClickListener;
	private SoundMeter soundMeter;
	/**录像的状态*/
	private int recordVideoState;
	/**录像--正在录像*/
	public static final int RECORD_VIDEO_RECORDING = 1;
	/**录像--停止录像*/
	public static final int RECORD_VIDEO_STOP = 0;
	/**计算录像时长的计时器*/
	private Timer myTimer;
	/**uiHandler的相关msg.what -- 0，刷新录像时长*/
	public static final int UIHANDLER_WHAT_REFRESH_RECORD_DURATION = 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_control);
		System.out.println("VideoControlActivity==onCreate");
		
		//禁止屏幕锁屏
  		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		// 获取配置信息
		configuration = this.getResources().getConfiguration();
		
		viewUtils = new ViewUtils(this);
		soundMeter = new SoundMeter();
		
		getAccountRight("1011111");
		initGesture();
		initNodeList();
		initView();
		event();
		initViewFlipper();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		System.out.println("VideoControlActivity==onResume");
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		System.out.println("VideoControlActivity==onPause");
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		System.out.println("VideoControlActivity==onDestroy");
		
		stopComputRecordTime();
	}
	
	private void getAccountRight(String accountRight)
    {
    	if(accountRight != null)
    	{
    		System.out.println("accountRight " + accountRight);
    		right_QuerySetChannelParameters = String.valueOf(accountRight.charAt(0));
    		right_YunTaiControl = String.valueOf(accountRight.charAt(1));
    		right_Talk = String.valueOf(accountRight.charAt(2));
    		right_Playback = String.valueOf(accountRight.charAt(3));
    		right_VideoTransit = String.valueOf(accountRight.charAt(4));
    		right_VideoReconnection = String.valueOf(accountRight.charAt(5));
    		right_Stream = String.valueOf(accountRight.charAt(6));
    	}
    }
	
	/**
	 * 构造虚拟数据
	 */
	private void initNodeList()
	{
		nodeList = new ArrayList<NodeInfo>();
		NodeInfo nodeInfo1 = new NodeInfo();
		NodeInfo nodeInfo2 = new NodeInfo();
		nodeList.add(nodeInfo1);
		nodeList.add(nodeInfo2);
		
		position = 0;
	}

	@Override
	public void initView()
	{
		super.initView();
		
		imageView_back = (ImageView) this.findViewById(R.id.imageView_common_titlebar_left);//标题栏左侧返回图标
		titlebar_title = (TextView) this.findViewById(R.id.textView_common_titlebar_title);//标题栏标题
		titlebar_title.setText("通道名称");
		titlebar_right = (ImageView) this.findViewById(R.id.imageView_common_titlebar_right);//标题栏右侧图标
		viewFlipper = (ViewFlipper) this.findViewById(R.id.viewFlipper_VideoControlActivity);//
		
		layout_video_control_bottom = (RelativeLayout) this.findViewById(R.id.layout_video_control_bottom);//底部布局总layout
		
		layout_video_control_menu = (LinearLayout) this.findViewById(R.id.layout_video_control_menu);//底部菜单layout
		imageView_resolution = (ImageView) this.findViewById(R.id.imageView_VideoControlActivity_resolution);//底部菜单选项--画质
		imageView_flip = (ImageView) this.findViewById(R.id.imageView_VideoControlActivity_flip);//底部菜单选项--翻转
		imageView_yuntai = (ImageView) this.findViewById(R.id.imageView_VideoControlActivity_yuntai);//底部菜单选项--云台
		imageView_recordVideo = (ImageView) this.findViewById(R.id.imageView_VideoControlActivity_recordVideo);//底部菜单选项--录像
		imageView_playback = (ImageView) this.findViewById(R.id.imageView_VideoControlActivity_playback);//底部菜单选项--回放
		imageView_screenshot = (ImageView) this.findViewById(R.id.imageView_VideoControlActivity_screenshot);//底部菜单选项--截图
		imageView_photo = (ImageView) this.findViewById(R.id.imageView_VideoControlActivity_photo);//底部菜单选项--照片
		imageView_deployProtection = (ImageView) this.findViewById(R.id.imageView_VideoControlActivity_deployProtection);//底部菜单选项--布防
		imageView_talk = (ImageView) this.findViewById(R.id.imageView_VideoControlActivity_talk);//底部菜单选项--对讲
		
		layout_video_control_talk = (LinearLayout) this.findViewById(R.id.layout_video_control_talk);//底部对讲layout
		imageView_talk_close = (ImageView) this.findViewById(R.id.imageView_VideoControlActivity_talk_close);//底部对讲layout--关闭
		imageView_talk_switch = (ImageView) this.findViewById(R.id.imageView_VideoControlActivity_talk_switch);//底部对讲layout--按住对讲
		
		layout_video_control_recordvideo = (LinearLayout) this.findViewById(R.id.layout_video_control_recordvideo);//底部录像layout
		textView_recordvideo_time = (TextView) this.findViewById(R.id.textView_video_control_recordvideo_time);//底部录像layout--录像时长
		imageView_recordvideo_redcircle = (ImageView) this.findViewById(R.id.imageView_video_control_recordvideo_redcircle);//底部录像layout--录像时闪烁的红点
		imageView_recordvideo_close = (ImageView) this.findViewById(R.id.imageView_video_control_recordvideo_close);//底部录像layout--关闭
		imageView_recordvideo_switch = (ImageView) this.findViewById(R.id.imageView_video_control_recordvideo_switch);//底部录像layout--切换
		
		layout_video_control_yuntai = (LinearLayout) this.findViewById(R.id.layout_video_control_yuntai);//底部云台layout
		imageView_yuntai_close = (ImageView) this.findViewById(R.id.imageView_video_control_yuntai_close);//底部云台layout--关闭
		imageView_yuntai_zoom_reduce = (ImageView) this.findViewById(R.id.imageView_video_control_yuntai_zoom_reduce);//底部云台layout--变倍，减
		imageView_yuntai_zoom_add = (ImageView) this.findViewById(R.id.imageView_video_control_yuntai_zoom_add);//底部云台layout--变倍，加
		progressBar_yuntai_zoom = (ProgressBar) this.findViewById(R.id.progressBar_video_control_yuntai_zoom);//底部云台layout--变倍ProgressBar
		imageView_yuntai_aperture_reduce = (ImageView) this.findViewById(R.id.imageView_video_control_yuntai_aperture_reduce);//底部云台layout--光圈，减
		imageView_yuntai_aperture_add = (ImageView) this.findViewById(R.id.imageView_video_control_yuntai_aperture_add);//底部云台layout--光圈，加
		progressBar_yuntai_aperture = (ProgressBar) this.findViewById(R.id.progressBar_video_control_yuntai_aperture);//底部云台layout--光圈ProgressBar
		imageView_yuntai_focus_reduce = (ImageView) this.findViewById(R.id.imageView_video_control_yuntai_focus_reduce);//底部云台layout--焦距，减
		imageView_yuntai_focus_add = (ImageView) this.findViewById(R.id.imageView_video_control_yuntai_focus_add);//底部云台layout--焦距，加
		progressBar_yuntai_focus = (ProgressBar) this.findViewById(R.id.progressBar_video_control_yuntai_focus);//底部云台layout--焦距ProgressBar
		
		setProgressBar();
		
		//将没有操作权限的图标替换为灰色图标
		if(right_YunTaiControl.equals("0"));
		{
			imageView_yuntai.setImageResource(R.drawable.icon_videoctrl_menu_yuntai_nonsupport);
		}
		if(right_Talk.equals("0"))
		{
			imageView_talk.setImageResource(R.drawable.icon_videoctrl_menu_talk_nonsupport);
		}
		
		titlebar_right.setVisibility(View.GONE);
		
	}

	@Override
	public void event()
	{
		super.event();
		
		mainClickListener = new MainClickListener();
		
		imageView_back.setOnClickListener(mainClickListener);
		imageView_resolution.setOnClickListener(mainClickListener);
		imageView_flip.setOnClickListener(mainClickListener);
		imageView_yuntai.setOnClickListener(mainClickListener);
		imageView_recordVideo.setOnClickListener(mainClickListener);
		imageView_playback.setOnClickListener(mainClickListener);
		imageView_screenshot.setOnClickListener(mainClickListener);
		imageView_photo.setOnClickListener(mainClickListener);
		imageView_deployProtection.setOnClickListener(mainClickListener);
		imageView_talk.setOnClickListener(mainClickListener);
		imageView_talk_close.setOnClickListener(mainClickListener);
		imageView_talk_switch.setOnTouchListener(new ImageViewTalkSwitchOnTouchListener());
		imageView_recordvideo_close.setOnClickListener(mainClickListener);
		imageView_recordvideo_switch.setOnClickListener(mainClickListener);
		imageView_yuntai_close.setOnClickListener(mainClickListener);
		imageView_yuntai_zoom_reduce.setOnClickListener(mainClickListener);
		imageView_yuntai_zoom_add.setOnClickListener(mainClickListener);
		imageView_yuntai_aperture_reduce.setOnClickListener(mainClickListener);
		imageView_yuntai_aperture_add.setOnClickListener(mainClickListener);
		imageView_yuntai_focus_reduce.setOnClickListener(mainClickListener);
		imageView_yuntai_focus_add.setOnClickListener(mainClickListener);
	}
	
	private void setProgressBar()
	{
		progressBar_yuntai_zoom.setMax(100);
		progressBar_yuntai_aperture.setMax(100);
		progressBar_yuntai_focus.setMax(100);
	}
	
//	*************************************START：手势设置部分*************************************
	/**
	 * 设置手势
	 */
	public void initGesture()
	{
		detector = new GestureDetector(new VideoPageOnGestureListener());
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		
		return this.detector.onTouchEvent(event);
	}
	
	/** 划屏动画设置，监听手势操作 */
	class VideoPageOnGestureListener implements OnGestureListener {

		private final int SWIPE_MIN_DISTANCE = 120;
		private final int SWIPE_MAX_OFF_PATH = 250;
		private final int SWIPE_THRESHOLD_VELOCITY = 200;
		
		@Override
		public boolean onDown(MotionEvent e) {
			System.out.println("touch=== Gesture onDown");
//			if(videoctrl_bottom.getVisibility() == View.VISIBLE){
//				videoctrl_bottom.setVisibility(View.GONE);
//			}else{
//				videoctrl_bottom.setVisibility(View.VISIBLE);
//			}
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {

		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// 横屏
			return false;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			// 获取屏幕高度
			WindowManager windowManager = getWindowManager();
			Display display = windowManager.getDefaultDisplay();
			int screenHeight = display.getHeight();
			int screenWidth = display.getWidth();
			
			// 横屏时
			//长按，镜头一直转动直到放开才停止转动
			if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
				System.out.println("Gesture === onFling");
//				if(isHaveRight(1))
//				{
//					int mw = screenWidth/3;
//					int mh = screenHeight/3;
//					int ex = (int) e.getX();
//					int ey = (int) e.getY();
//					if (ex > 0 && ex < mw) {
//						if (ey > 0 && ey < mh) {
//							setLeftTop();
//						}
//						else if (ey > mh && ey < 2 * mh) {
//							setLeft();
//						}
//						else if (ey > 2 * mh && ey < screenHeight) {
//							setLeftDown();
//						}
//					}
//					else if (ex > mw && ex < 2 * mw) {
//						if (ey > 0 && ey < mh) {
//							setTop();
//						}
//						else if (ey > mh && ey < 2 * mh) {
//							Log.d(TAG, "中");
//						}
//						else if (ey > 2 * mh && ey < screenHeight) {
//							setDown();
//						}
//					}
//					else if (ex > 2 * mw && ex < screenWidth) {
//						if (ey > 0 && ey < mh) {
//							setRightTop();
//						}
//						else if (ey > mh && ey < 2 * mh) {
//							setRight();
//						}
//						else if (ey > 2 * mh && ey < screenHeight) {
//							setRightDown();
//						}
//					}
//					isYunCtl = true;
//				}
//				else
//				{
//					showTip("当前账号无云台控制权限");
//				}
			}
		}
		public boolean check(){
			System.err.println("check --->last changeTIme:"+lastChangeChannelTime +"  curTime:"+System.currentTimeMillis());
			if ((System.currentTimeMillis() - lastChangeChannelTime) < 700){
				return false;//如果在700毫秒内切屏就不允许
			}
			lastChangeChannelTime = System.currentTimeMillis();
			return true;
		}
		
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
//			if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
//				return false;
			if (!check())
				return false;
			
			cuttime = System.currentTimeMillis();
			
			if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)//左划
			{
				// 显示下一屏和切换动画
				viewFlipper.setInAnimation(slideLeftIn);
				viewFlipper.setOutAnimation(slideLeftOut);
				viewFlipper.showNext();
				 System.out.println("Gesture === onFling");
				
				// 获取当前选中的通道
				position ++;
				if(position > nodeList.size()-1)
				{
					position = 0;
				}
				
				changeVideoViewHidden(position);
			} 
			else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
					&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)//右划
			{
				viewFlipper.setInAnimation(slideRightIn);
				viewFlipper.setOutAnimation(slideRightOut);
				viewFlipper.showPrevious();

				// 获取当前选中的通道
				position --;
				if(position < 0)
				{
					position = nodeList.size()-1;
				}

				changeVideoViewHidden(position);
			}
			else if(e1.getY() - e2.getY() > 50
					&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY)//上划
			{
//				showTip("上划");
			}
			else if(e2.getY() - e1.getY() > 50
					&& Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY)//下划
			{
//				showTip("下划");
			}
			return false;
		}
	}
//	*************************************END：手势设置部分*************************************
	
	/**
	 * 设置ViewFlipper
	 */
	public void initViewFlipper()
	{
		// 通道切换动画
		slideLeftIn = AnimationUtils.loadAnimation(this, R.anim.push_left_in);
		slideLeftOut = AnimationUtils.loadAnimation(this, R.anim.push_left_out);
		slideRightIn = AnimationUtils.loadAnimation(this, R.anim.push_right_in);
		slideRightOut = AnimationUtils.loadAnimation(this, R.anim.push_right_out);
		
		// 获取通道节点数据
		if (nodeList != null) {
			// 根据节点数，创建viewFlipper页面
			for (int i = 0; i < nodeList.size(); i++) {
				viewFlipperPageView = LayoutInflater.from(this).inflate(
						R.layout.vs_view_flipper_page, null);
				viewFlipper.addView(viewFlipperPageView);
				viewList.add(viewFlipperPageView);
//				 System.out.println("ipcNodeList " + i);
				surfaceView_videoctrl = (SurfaceView) viewFlipperPageView.findViewById(R.id.surfaceView_videoctrl);
			}
		}
		
	}
	
	public void changeVideoViewHidden(int current) {
		 System.out.println("===current===changeVideoViewHidden " + current);
		RelativeLayout relativeLayout = (RelativeLayout) viewList.get(current);
		SurfaceView surfaceView = (SurfaceView) relativeLayout.getChildAt(1);
		surfaceView.setVisibility(View.GONE);
		ProgressBar progressBar = (ProgressBar) relativeLayout.getChildAt(0);
		progressBar.setVisibility(View.VISIBLE);
	}
	
	public SurfaceView changeVideoViewShow(int current) {
		 System.out.println("===current===changeVideoViewShow " + current);
		RelativeLayout relativeLayout = (RelativeLayout) viewList.get(current);
		ProgressBar progressBar = (ProgressBar) relativeLayout.getChildAt(0);
		progressBar.setVisibility(View.GONE);
		SurfaceView surfaceView = (SurfaceView) relativeLayout.getChildAt(1);
		surfaceView.setVisibility(View.VISIBLE);
		return surfaceView;
	}
	
	class MainClickListener implements OnClickListener
	{

		@Override
		public void onClick(View v)
		{
			changeUI(v.getId());
			
			switch (v.getId())
			{
				case R.id.imageView_common_titlebar_left:
					finish();
					break;
				case R.id.imageView_VideoControlActivity_resolution://底部菜单选项--画质
					showCustomAlertDialogResolution();
					break;
				case R.id.imageView_VideoControlActivity_flip://底部菜单选项--翻转  
					showCustomAlertDialogFlip();
					break;
				case R.id.imageView_VideoControlActivity_yuntai://底部菜单选项--云台
					layout_video_control_yuntai.setVisibility(View.VISIBLE);
					layout_video_control_menu.setVisibility(View.GONE);
					break;
				case R.id.imageView_VideoControlActivity_recordVideo://底部菜单选项--录像 
					layout_video_control_recordvideo.setVisibility(View.VISIBLE);
					layout_video_control_menu.setVisibility(View.GONE);
					imageView_recordvideo_close.setVisibility(View.VISIBLE);
					recordVideoState = RECORD_VIDEO_STOP;
					textView_recordvideo_time.setText("00:00:00");
					imageView_recordvideo_switch.setImageResource(R.drawable.selector_recordvideo_start);
					break;
				case R.id.imageView_VideoControlActivity_playback://底部菜单选项--回放
					showCustomAlertDialogPlayback();
					break;
				case R.id.imageView_VideoControlActivity_screenshot://底部菜单选项--截图
					showCustomAlertDialogScreenshot();				
					break;
				case R.id.imageView_VideoControlActivity_photo://底部菜单选项--照片
					
					break;
				case R.id.imageView_VideoControlActivity_deployProtection://底部菜单选项--布防
					
					break;
				case R.id.imageView_VideoControlActivity_talk://底部菜单选项--对讲
					layout_video_control_menu.setVisibility(View.GONE);
					layout_video_control_talk.setVisibility(View.VISIBLE);
					break;
				case R.id.imageView_VideoControlActivity_talk_close://底部对讲layout--关闭
					layout_video_control_menu.setVisibility(View.VISIBLE);
					layout_video_control_talk.setVisibility(View.GONE);
					break;
				case R.id.textView_VideoControlActivity_resolution_HD://画质--高清
					customAlertDialogResolution.dismiss();
					break;
				case R.id.textView_VideoControlActivity_resolution_SD://画质--标清
					customAlertDialogResolution.dismiss();
					break;
				case R.id.textView_VideoControlActivity_resolution_LD://画质--流畅
					customAlertDialogResolution.dismiss();
					break;
				case R.id.textView_VideoControlActivity_resolution_cancel://画质--取消
					customAlertDialogResolution.dismiss();
					break;
				case R.id.textView_VideoControlActivity_playback_local://回放--本地
					customAlertDialogPlayback.dismiss();
					break;
				case R.id.textView_VideoControlActivity_playback_remote://回放--远程
					customAlertDialogPlayback.dismiss();
					break;
				case R.id.textView_VideoControlActivity_playback_cancel://回放--取消
					customAlertDialogPlayback.dismiss();
					break;
				case R.id.button_custom_alert_dialog_screenshot_ignore://截图--跳过
					customAlertDialogScreenshot.dismiss();
					break;
				case R.id.button_custom_alert_dialog_screenshot_confirm://截图--确定
					customAlertDialogScreenshot.dismiss();
					break;
				case R.id.button_custom_alert_dialog_recordvideo_ignore://录像--跳过
					customAlertDialogRecordvideo.dismiss();
					layout_video_control_recordvideo.setVisibility(View.GONE);
					layout_video_control_menu.setVisibility(View.VISIBLE);
					imageView_recordvideo_switch.setImageResource(R.drawable.selector_recordvideo_start);
					break;
				case R.id.button_custom_alert_dialog_recordvideo_confirm://录像--确定
					customAlertDialogRecordvideo.dismiss();
					layout_video_control_recordvideo.setVisibility(View.GONE);
					layout_video_control_menu.setVisibility(View.VISIBLE);
					imageView_recordvideo_switch.setImageResource(R.drawable.selector_recordvideo_start);
					break;
				case R.id.imageView_video_control_recordvideo_close://录像--关闭
					layout_video_control_recordvideo.setVisibility(View.GONE);
					layout_video_control_menu.setVisibility(View.VISIBLE);
					stopComputRecordTime();
					break;
				case R.id.imageView_video_control_recordvideo_switch://录像--切换
					if(recordVideoState == RECORD_VIDEO_STOP){
						recordVideoState = RECORD_VIDEO_RECORDING;
						imageView_recordvideo_switch.setImageResource(R.drawable.selector_recordvideo_stop);
						imageView_recordvideo_close.setVisibility(View.GONE);
						startComputRecordTime();
					}else if(recordVideoState == RECORD_VIDEO_RECORDING){
						recordVideoState = RECORD_VIDEO_STOP;
						showCustomAlertDialogRecordvideo();
						stopComputRecordTime();
					}
					break;
				case R.id.imageView_video_control_yuntai_close://录像--关闭
					layout_video_control_yuntai.setVisibility(View.GONE);
					layout_video_control_menu.setVisibility(View.VISIBLE);
					break;
				case R.id.imageView_video_control_yuntai_zoom_reduce://变倍--减
					changeProgressValue(progressBar_yuntai_zoom, "reduce");
					break;
				case R.id.imageView_video_control_yuntai_zoom_add://变倍--加
					changeProgressValue(progressBar_yuntai_zoom, "add");				
					break;
				case R.id.imageView_video_control_yuntai_aperture_reduce://光圈--减
					changeProgressValue(progressBar_yuntai_aperture, "reduce");
					break;
				case R.id.imageView_video_control_yuntai_aperture_add://光圈--加
					changeProgressValue(progressBar_yuntai_aperture, "add");
					break;
				case R.id.imageView_video_control_yuntai_focus_reduce://焦距--减
					changeProgressValue(progressBar_yuntai_focus, "reduce");
					break;
				case R.id.imageView_video_control_yuntai_focus_add://焦距--加
					changeProgressValue(progressBar_yuntai_focus, "add");
					break;
				case R.id.button_custom_alert_dialog_flip_cancel://翻转--取消
					customAlertDialogFlip.dismiss();
					break;
				case R.id.button_custom_alert_dialog_flip_confirm://翻转--确定
					customAlertDialogFlip.dismiss();
					break;
				default:
					break;
			}
		}
		
	}
	
	private void changeUI(int viewId)
	{
		//先将所有图标替换为白色图标
		imageView_resolution.setImageResource(R.drawable.icon_videoctrl_menu_resolution_normal);
		imageView_flip.setImageResource(R.drawable.icon_videoctrl_menu_flip_normal);
		imageView_yuntai.setImageResource(R.drawable.icon_videoctrl_menu_yuntai_normal);
		imageView_recordVideo.setImageResource(R.drawable.icon_videoctrl_menu_recordvideo_normal);
		imageView_playback.setImageResource(R.drawable.icon_videoctrl_menu_playback_normal);
		imageView_screenshot.setImageResource(R.drawable.icon_videoctrl_menu_screenshot_normal);
		imageView_photo.setImageResource(R.drawable.icon_videoctrl_menu_photo_normal);
		imageView_deployProtection.setImageResource(R.drawable.icon_videoctrl_menu_bufang_normal);
		imageView_talk.setImageResource(R.drawable.icon_videoctrl_menu_talk_normal);
		
		//再将没有操作权限的图标替换为灰色图标
		if(right_YunTaiControl.equals("0"));
		{
			imageView_yuntai.setImageResource(R.drawable.icon_videoctrl_menu_yuntai_nonsupport);
		}
		if(right_Talk.equals("0"))
		{
			imageView_talk.setImageResource(R.drawable.icon_videoctrl_menu_talk_nonsupport);
		}
		
		switch (viewId)
		{
			case R.id.imageView_VideoControlActivity_resolution://底部菜单选项--画质
				imageView_resolution.setImageResource(R.drawable.icon_videoctrl_menu_resolution_pressed);
				break;
			case R.id.imageView_VideoControlActivity_flip://底部菜单选项--翻转  
				imageView_flip.setImageResource(R.drawable.icon_videoctrl_menu_flip_pressed);
				break;
			case R.id.imageView_VideoControlActivity_yuntai://底部菜单选项--云台
				if(right_YunTaiControl.equals("1"))
				{
					imageView_yuntai.setImageResource(R.drawable.icon_videoctrl_menu_yuntai_pressed);
				}
				else if(right_YunTaiControl.equals("0"));
				{
					imageView_yuntai.setImageResource(R.drawable.icon_videoctrl_menu_yuntai_nonsupport);
					showToast(R.string.toast_not_authority_yuntai);
				}
				break;
			case R.id.imageView_VideoControlActivity_recordVideo://底部菜单选项--录像 
				imageView_recordVideo.setImageResource(R.drawable.icon_videoctrl_menu_recordvideo_pressed);
				break;
			case R.id.imageView_VideoControlActivity_playback://底部菜单选项--回放
				imageView_playback.setImageResource(R.drawable.icon_videoctrl_menu_playback_pressed);
				break;
			case R.id.imageView_VideoControlActivity_screenshot://底部菜单选项--截图
				imageView_screenshot.setImageResource(R.drawable.icon_videoctrl_menu_screenshot_pressed);
				break;
			case R.id.imageView_VideoControlActivity_photo://底部菜单选项--照片
				imageView_photo.setImageResource(R.drawable.icon_videoctrl_menu_photo_pressed);
				break;
			case R.id.imageView_VideoControlActivity_deployProtection://底部菜单选项--布防
				imageView_deployProtection.setImageResource(R.drawable.icon_videoctrl_menu_bufang_pressed);
				break;
			case R.id.imageView_VideoControlActivity_talk://底部菜单选项--对讲
				if(right_Talk.equals("1"))
				{
					imageView_talk.setImageResource(R.drawable.icon_videoctrl_menu_talk_pressed);
				}
				else if(right_Talk.equals("0"))
				{
					imageView_talk.setImageResource(R.drawable.icon_videoctrl_menu_talk_nonsupport);
					showToast(R.string.toast_not_authority_talk);
				}
				break;
			default:
				break;
		}
	}
	
	/**
	 * 改变ProgressBar的进度值
	 * @param progressBar
	 * @param action
	 */
	private void changeProgressValue(ProgressBar progressBar, String action)
	{
		int progress = progressBar.getProgress();
		if(action.equals("add"))
		{
			progress ++;
			if(progress > 100)
			{
				progress = 100;
			}
		}
		if(action.equals("reduce"))
		{
			progress --;
			if(progress < 0)
			{
				progress = 0;
			}
		}
		progressBar.setProgress(progress);
	}
//	*************************************START：对讲相关部分*************************************
	/**
	 * 按住对讲图标的触摸监听
	 * @author zhengjingzhong
	 *
	 * 2015-2-10 上午10:07:39
	 */
	class ImageViewTalkSwitchOnTouchListener implements OnTouchListener
	{

		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			/**现在对讲改为全双工，1530一请求成功就马上打开录音了，当“按住对讲”按钮松开时将采集
			 * 的音频数据全部改为0（相当于静音）发出，当“按住对讲”按钮按下时将采集的音频数据发
			 * 出（有声音）。
			 * isEncodeAudio在全双工状态下都置为true，这个变量其实可以不用了，但是先保留，为以
			 * 后突然又要改为半双工时使用
			 */
			if (event.getAction() == MotionEvent.ACTION_DOWN)
			{
				System.out.println("TalkImageViewTouchListener==ACTION_DOWN ");
				imageView_talk_switch.setImageResource(R.drawable.icon_talk_volume1);
				saveTempVoiceFile();
			}
			else if (event.getAction() == MotionEvent.ACTION_UP)
			{
				System.out.println("TalkImageViewTouchListener==ACTION_UP");
				imageView_talk_switch.setImageResource(R.drawable.icon_talk_initial);
				stopRecordAudio();
			}
			
			return false;
		}
		
	}
	
	/** 存储语音对讲临时文件 */
	public void saveTempVoiceFile() {
		// 存储对讲临时文件
		File path = new File(AndroidEnv.SDCARD_PATH + "/" + Constants.FOLDER_NAME_ROOT + "/" + Constants.FOLDER_NAME_RECORD_AUDIO + "/");
		if (!path.isDirectory()) {
			path.mkdirs();
		}

		File file = new File(path.getAbsolutePath() + "/temp.amr");
		if (!path.isFile()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (file.isFile()) {
			startRecordAudio(file.getAbsolutePath());
		}
	}
	
	/** 对讲 */
	private static final int POLL_INTERVAL = 300;
	private Handler mHandler = new Handler();

	private Runnable mSleepTask = new Runnable() {
		public void run() {
			stopRecordAudio();
		}
	};

	private Runnable mPollTask = new Runnable() {
		public void run() {
			double amp = soundMeter.getAmplitude();
			updateVolumeDisplay(amp);
			mHandler.postDelayed(mPollTask, POLL_INTERVAL);
		}
	};

	/**
	 * 开始录音
	 * @param name
	 */
	private void startRecordAudio(String name) {
		soundMeter.start(name);
		mHandler.postDelayed(mPollTask, POLL_INTERVAL);
	}

	/**
	 * 结束录音
	 */
	private void stopRecordAudio() {
		mHandler.removeCallbacks(mSleepTask);
		mHandler.removeCallbacks(mPollTask);
		soundMeter.stop();
		imageView_talk_switch.setImageResource(R.drawable.icon_talk_initial);
	}

	private void updateVolumeDisplay(double signalEMA) {

		switch ((int) signalEMA) {
		case 0:
		case 1:
			imageView_talk_switch.setImageResource(R.drawable.icon_talk_volume1);
			break;
		case 2:
		case 3:
			imageView_talk_switch.setImageResource(R.drawable.icon_talk_volume2);
			break;
		case 4:
		case 5:
			imageView_talk_switch.setImageResource(R.drawable.icon_talk_volume3);
			break;
		case 6:
		case 7:
			imageView_talk_switch.setImageResource(R.drawable.icon_talk_volume4);
			break;
		case 8:
		case 9:
			imageView_talk_switch.setImageResource(R.drawable.icon_talk_volume5);
			break;
		case 10:
		case 11:
			imageView_talk_switch.setImageResource(R.drawable.icon_talk_volume5);
			break;
		default:
			break;
		}
	}
//	*************************************END：对讲相关部分*************************************

//	*************************************START:自定义AlertDialog弹框部分*********************************
	/**
	 * 显示自定义的画质选择AlertDialog
	 */
	private void showCustomAlertDialogResolution()
	{
		customAlertDialogResolution = new AlertDialog.Builder(this).create();  
		customAlertDialogResolution.show(); 
		customAlertDialogResolution.setCancelable(false);//让点击对话框外面和按返回键时对话框不会消失
		Window window = customAlertDialogResolution.getWindow();
		window.setContentView(R.layout.custom_alert_dialog_resolution);  
		
		//自定义AlertDialog里面的控件的取得和点击事件的设置需在AlertDialog弹出后才能开始获取和设置，否则会出现NullPointerException异常
		textView_resolution_HD = (TextView) window.findViewById(R.id.textView_VideoControlActivity_resolution_HD);//画质--高清
		textView_resolution_SD = (TextView) window.findViewById(R.id.textView_VideoControlActivity_resolution_SD);//画质--标清
		textView_resolution_LD = (TextView) window.findViewById(R.id.textView_VideoControlActivity_resolution_LD);//画质--流畅
		textView_resolution_cancel = (TextView) window.findViewById(R.id.textView_VideoControlActivity_resolution_cancel);//画质--取消
		
		textView_resolution_HD.setOnClickListener(mainClickListener);
		textView_resolution_SD.setOnClickListener(mainClickListener);
		textView_resolution_LD.setOnClickListener(mainClickListener);
		textView_resolution_cancel.setOnClickListener(mainClickListener);
	}
	
	/**
	 * 显示自定义的回放选择AlertDialog
	 */
	private void showCustomAlertDialogPlayback()
	{
		customAlertDialogPlayback = new AlertDialog.Builder(this).create();  
		customAlertDialogPlayback.show(); 
		customAlertDialogPlayback.setCancelable(false);//让点击对话框外面和按返回键时对话框不会消失
		Window window = customAlertDialogPlayback.getWindow();
		window.setContentView(R.layout.custom_alert_dialog_playback);  
		
		//自定义AlertDialog里面的控件的取得和点击事件的设置需在AlertDialog弹出后才能开始获取和设置，否则会出现NullPointerException异常
		textView_playback_local = (TextView) window.findViewById(R.id.textView_VideoControlActivity_playback_local);//回放--本地
		textView_playback_remote = (TextView) window.findViewById(R.id.textView_VideoControlActivity_playback_remote);//回放--远程
		textView_playback_cancel = (TextView) window.findViewById(R.id.textView_VideoControlActivity_playback_cancel);//回放--取消
		
		textView_playback_local.setOnClickListener(mainClickListener);
		textView_playback_remote.setOnClickListener(mainClickListener);
		textView_playback_cancel.setOnClickListener(mainClickListener);
	}
	
	/**
	 * 显示自定义的截图AlertDialog
	 */
	private void showCustomAlertDialogScreenshot()
	{
		customAlertDialogScreenshot = new AlertDialog.Builder(this).create();  
		customAlertDialogScreenshot.show(); 
		customAlertDialogScreenshot.setCancelable(false);//让点击对话框外面和按返回键时对话框不会消失
		Window window = customAlertDialogScreenshot.getWindow();
		window.setContentView(R.layout.custom_alert_dialog_screenshot);  
		//增加下面这两行设置，否则当点击EditText时自定义AlertDialog无法弹出输入法
		window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM); 
		window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		
		//自定义AlertDialog里面的控件的取得和点击事件的设置需在AlertDialog弹出后才能开始获取和设置，否则会出现NullPointerException异常
		editText_screenshot_name = (EditText) window.findViewById(R.id.editText_custom_alert_dialog_screenshot_name);//截图--命名输入框
		button_screenshot_ignore = (Button) window.findViewById(R.id.button_custom_alert_dialog_screenshot_ignore);//截图--跳过
		button_screenshot_confirm = (Button) window.findViewById(R.id.button_custom_alert_dialog_screenshot_confirm);//截图--确定
		
		String name = editText_screenshot_name.getText().toString();
		if(name.length() > 0)
		{
			button_screenshot_confirm.setTextColor(getResources().getColor(R.color.blue_common));
			button_screenshot_ignore.setTextColor(getResources().getColor(R.color.black));
		}
		else
		{
			button_screenshot_confirm.setTextColor(getResources().getColor(R.color.black));
			button_screenshot_ignore.setTextColor(getResources().getColor(R.color.blue_common));
		}
		
		button_screenshot_ignore.setOnClickListener(mainClickListener);
		button_screenshot_confirm.setOnClickListener(mainClickListener);
		editText_screenshot_name.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				System.out.println("TextChanged==onTextChanged "+s);
				System.out.println("TextChanged==onTextChanged count "+count);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
				System.out.println("TextChanged==beforeTextChanged "+s);
				System.out.println("TextChanged==beforeTextChanged count "+count);
			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
				System.out.println("TextChanged==afterTextChanged "+s);
				
				if(s.length() > 0)
				{
					button_screenshot_confirm.setTextColor(getResources().getColor(R.color.blue_common));
					button_screenshot_ignore.setTextColor(getResources().getColor(R.color.black));
				}
				else
				{
					button_screenshot_confirm.setTextColor(getResources().getColor(R.color.black));
					button_screenshot_ignore.setTextColor(getResources().getColor(R.color.blue_common));
				}
				
			}
		});
	}
	
	/**
	 * 显示自定义的录像AlertDialog
	 */
	private void showCustomAlertDialogRecordvideo()
	{
		customAlertDialogRecordvideo = new AlertDialog.Builder(this).create();  
		customAlertDialogRecordvideo.show(); 
		customAlertDialogRecordvideo.setCancelable(false);//让点击对话框外面和按返回键时对话框不会消失
		Window window = customAlertDialogRecordvideo.getWindow();
		window.setContentView(R.layout.custom_alert_dialog_recordvideo);  
		//增加下面这两行设置，否则当点击EditText时自定义AlertDialog无法弹出输入法
		window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM); 
		window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		
		//自定义AlertDialog里面的控件的取得和点击事件的设置需在AlertDialog弹出后才能开始获取和设置，否则会出现NullPointerException异常
		editText_recordvideo_name = (EditText) window.findViewById(R.id.editText_custom_alert_dialog_recordvideo_name);//录像--命名输入框
		button_recordvideo_ignore = (Button) window.findViewById(R.id.button_custom_alert_dialog_recordvideo_ignore);//录像--跳过
		button_recordvideo_confirm = (Button) window.findViewById(R.id.button_custom_alert_dialog_recordvideo_confirm);//录像--确定
		
		String name = editText_recordvideo_name.getText().toString();
		if(name.length() > 0)
		{
			button_recordvideo_confirm.setTextColor(getResources().getColor(R.color.blue_common));
			button_recordvideo_ignore.setTextColor(getResources().getColor(R.color.black));
		}
		else
		{
			button_recordvideo_confirm.setTextColor(getResources().getColor(R.color.black));
			button_recordvideo_ignore.setTextColor(getResources().getColor(R.color.blue_common));
		}
		
		button_recordvideo_ignore.setOnClickListener(mainClickListener);
		button_recordvideo_confirm.setOnClickListener(mainClickListener);
		editText_recordvideo_name.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				System.out.println("TextChanged==onTextChanged "+s);
				System.out.println("TextChanged==onTextChanged count "+count);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
				System.out.println("TextChanged==beforeTextChanged "+s);
				System.out.println("TextChanged==beforeTextChanged count "+count);
			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
				System.out.println("TextChanged==afterTextChanged "+s);
				
				if(s.length() > 0)
				{
					button_recordvideo_confirm.setTextColor(getResources().getColor(R.color.blue_common));
					button_recordvideo_ignore.setTextColor(getResources().getColor(R.color.black));
				}
				else
				{
					button_recordvideo_confirm.setTextColor(getResources().getColor(R.color.black));
					button_recordvideo_ignore.setTextColor(getResources().getColor(R.color.blue_common));
				}
			}
		});
	}
	
	/**
	 * 显示自定义的翻转AlertDialog
	 */
	private void showCustomAlertDialogFlip()
	{
		customAlertDialogFlip = new AlertDialog.Builder(this).create();  
		customAlertDialogFlip.show(); 
		customAlertDialogFlip.setCancelable(false);//让点击对话框外面和按返回键时对话框不会消失
		Window window = customAlertDialogFlip.getWindow();
		window.setContentView(R.layout.custom_alert_dialog_flip);  
		//增加下面这两行设置，否则当点击EditText时自定义AlertDialog无法弹出输入法
		window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM); 
		window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		
		//自定义AlertDialog里面的控件的取得和点击事件的设置需在AlertDialog弹出后才能开始获取和设置，否则会出现NullPointerException异常
		checkBox_flip_horizontal = (CheckBox) window.findViewById(R.id.checkBox_VideoControlActivity_flip_horizontal);//水平翻转
		checkBox_flip_vertical = (CheckBox) window.findViewById(R.id.checkBox_VideoControlActivity_flip_vertical);//水平翻转
		button_flip_cancel = (Button) window.findViewById(R.id.button_custom_alert_dialog_flip_cancel);//取消
		button_flip_confirm = (Button) window.findViewById(R.id.button_custom_alert_dialog_flip_confirm);//确定
		
		button_flip_cancel.setOnClickListener(mainClickListener);
		button_flip_confirm.setOnClickListener(mainClickListener);
	}
//	*************************************END:自定义AlertDialog弹框部分*********************************
	
//	************************************START:刷新录像时长的实现代码*************************************
	/**
	 * 开启刷新录像时长显示的计时器
	 */
	private void startComputRecordTime()
	{
		myTimer = new Timer();
		myTimer.schedule(new RecordvideoDuration(), 1000, 1000);
	}
	
	/**
	 * 停止刷新录像时长显示的计时器
	 */
	private void stopComputRecordTime()
	{
		if(myTimer != null)
		{
			myTimer.cancel();
		}
	}
	
	class RecordvideoDuration extends TimerTask
	{
		private String timeStr;
		/**录像时长*/
		private int duration;
		private int showRedCircle;
		
		@Override
		public void run()
		{
			duration ++;
			showRedCircle = duration % 2;
			timeStr = convertTimeFormat(duration);
	        
			//第三个参数0是凑数的，无实际用途
	        Message msg = uiHandler.obtainMessage(UIHANDLER_WHAT_REFRESH_RECORD_DURATION, showRedCircle, 0, timeStr);
	        uiHandler.sendMessage(msg);
		}
	}
	
	/**
	 * 转换时间格式为"%02d:%02d:%02d"
	 * @author zhengjingzhong
	 * @param time
	 * @return
	 */
	public String convertTimeFormat(int time)
	{
		int hour = time / 3600;
		int minute = (time % 3600) / 60;
		int second = (time % 3600) % 60;
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
//	************************************END:刷新录像时长的实现代码*************************************
	
	/**
	 * 刷新界面的Handler
	 */
	Handler uiHandler = new Handler()
	{
		public void handleMessage(android.os.Message msg) {
			switch(msg.what)
			{
				case UIHANDLER_WHAT_REFRESH_RECORD_DURATION:
					String time = (String) msg.obj;
					textView_recordvideo_time.setText(time);
					
					int showRedCircle = msg.arg1;
					if(showRedCircle == 0){
						imageView_recordvideo_redcircle.setVisibility(View.GONE);
					}else if(showRedCircle == 1){
						imageView_recordvideo_redcircle.setVisibility(View.VISIBLE);
					}
					break;
			}
		};
	};
	
	private void showToast(int resId)
	{
		viewUtils.showToastCustom(resId);
	}
}
