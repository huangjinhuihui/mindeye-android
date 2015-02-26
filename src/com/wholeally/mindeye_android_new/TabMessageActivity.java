package com.wholeally.mindeye_android_new;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.MindeyeInterface.Init;
import com.wholeally.mindeye_android_new.TabVideoActivity.MyOnPageChangeListener;
import com.wholeally.mindeye_android_new.fragments.FragmentMessageEvent;
import com.wholeally.mindeye_android_new.fragments.FragmentMessageSystem;
import com.wholeally.mindeye_android_new.fragments.FragmentVideoLive;
import com.wholeally.mindeye_android_new.fragments.FragmentVideoLocal;
import com.wholeally.mindeye_android_new.fragments.FragmentVideoRemote;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

public class TabMessageActivity extends FragmentActivity implements TabContentFactory, Init
{

	private TabHost mtabhost;
	private ViewPager mviewPager;
	private TextView tabEvent;
	private TextView tabSystem;
	private ImageView imgIndicate;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_message);
		
		initView();
	}

	@Override
	public View createTabContent(String arg0)
	{
		View view = new View(this);
        view.setMinimumHeight(0);
        view.setMinimumWidth(0);
        return view;
	}

	@Override
	public void initView()
	{
		imgIndicate = (ImageView)findViewById(R.id.imageView_TabMessageActivity_indicate);
		initTab();
        initViewPager();
	}

	@Override
	public void event()
	{
		// TODO Auto-generated method stub
		
	}

	private void initTab()
    {
        mtabhost = (TabHost) findViewById(android.R.id.tabhost);
        mtabhost.setup();
                     
        tabEvent = (TextView) getLayoutInflater().inflate(R.layout.common_tab_sub_indicator, null);
        tabEvent.setText("事件");
        tabSystem = (TextView) getLayoutInflater().inflate(R.layout.common_tab_sub_indicator, null);
        tabSystem.setText("系统");
        
        mtabhost.addTab(mtabhost.newTabSpec("tabLocal").setIndicator(tabEvent).setContent(this));
        mtabhost.addTab(mtabhost.newTabSpec("tabRemote").setIndicator(tabSystem).setContent(this));
        
        mtabhost.setOnTabChangedListener(new OnTabChangeListener()
        {
            @Override
            public void onTabChanged(String tabId)
            {
                int currentTab = mtabhost.getCurrentTab();
                mviewPager.setCurrentItem(currentTab);
            }
        });
    }
	
	private void initViewPager()
    {
        mviewPager = (ViewPager) findViewById(R.id.ViewPager_TabMessageActivity);
        
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int tabOffSet = dm.widthPixels/2;//获取屏幕宽度的1/2
		int bmWith = BitmapFactory.decodeResource(getResources(), R.drawable.tab_indicate_line).getWidth();//获取tab指示线长度
		Matrix matrix = new Matrix();//创建图像矩阵
//		*******************************************************
		/*以下这三行代码的作用是将tab指示线长度根据屏幕宽度进行调整，
		 * 当不需要tab指示线长度根据屏幕宽度进行调整时需将这3行代码注释掉*/
		float scaleWidth = ((float) tabOffSet) / bmWith; //屏幕宽度1/2与tab指示线长度的比值
		matrix.postScale(scaleWidth, 1.0f); //根据比例缩放图片
		bmWith = tabOffSet;//将tab指示线长度改为屏幕宽度1/2
//		*******************************************************
		matrix.postTranslate((tabOffSet - bmWith)/2, 0);//先将指示线段平移到靠左侧位置
		imgIndicate.setImageMatrix(matrix);//设置图像矩阵，根据矩阵绘制新的图像
		int[] positionSet = new int[]{0,tabOffSet};//设置tab指示线的3个滑动起始点的x坐标
        
		mviewPager.setCurrentItem(0);//设置当前ViewPager显示的Item
		tabEvent.setTextColor(getResources().getColor(R.color.blue_common));//设置初始化的时候第一个tab标签被处于选中状态
		mviewPager.setOnPageChangeListener(new MyOnPageChangeListener(positionSet));
        mviewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public int getCount()
            {
                // TODO Auto-generated method stub
                return 2;
            }
            
            @Override
            public Fragment getItem(int arg0)
            {
                switch (arg0)
                {
                    case 0:
                        return new FragmentMessageEvent();
                    case 1:
                        return new FragmentMessageSystem();
                }
                return null;
            }
            
            @Override
            public void destroyItem(ViewGroup container, int position,
                    Object object)
            {
                
            }
        });
    }
	
	class MyOnPageChangeListener implements OnPageChangeListener
	{
		int locationStart;
		int locationNow;
		int[] positionSet;
		public MyOnPageChangeListener(int[] positionSet){
			this.positionSet = positionSet;
			if (positionSet.length>0) {
				locationStart = positionSet[0];
			}
		}
		
		@Override
		public void onPageSelected(int arg0) {
			mtabhost.setCurrentTab(arg0);
			
			Animation animation = null;
			if (positionSet.length > arg0) {
				locationNow = positionSet[arg0];
			}
			animation = new TranslateAnimation(locationStart, locationNow, 0, 0);
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
			imgIndicate.startAnimation(animation);
			mviewPager.setAnimation(AnimationUtils.loadAnimation(TabMessageActivity.this, R.anim.common_animation));
			locationStart = locationNow;
			
			switch (arg0) 
            {
    			case 0:
    				tabEvent.setTextColor(getResources().getColor(R.color.blue_common));
    				tabSystem.setTextColor(getResources().getColor(R.color.gray_common_tab_text)); 
    				break;
    			case 1:
    				tabEvent.setTextColor(getResources().getColor(R.color.gray_common_tab_text));
    				tabSystem.setTextColor(getResources().getColor(R.color.blue_common));
    				break;
			}
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
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
		
	}
}
