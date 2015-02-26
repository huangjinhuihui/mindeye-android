/**
 * 
 */
package com.wholeally.mindeye_android_new;

import java.util.ArrayList;
import java.util.List;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.MindeyeInterface.Init;
import com.wholeally.mindeye_android_new.utils.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * @author huangjh
 *
 * 2015-2-3 下午3:43:33
 */
public class WelcomeActivity extends BaseActivity{

	private ViewPager mPager;
	private List<View> views;
	private Context context;
	private boolean isClick = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initView();
		event();
	}
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		super.initView();
		context=  this;
		mPager = (ViewPager) findViewById(R.id.welcome_viewpager);
		views = new ArrayList<View>();
		LayoutInflater layoutInflator = LayoutInflater.from(context);
        View v1 = layoutInflator.inflate(R.layout.welcome_item_image, null);
        ImageView imageView1 = (ImageView) v1.findViewById(R.id.img);
		imageView1.setImageBitmap(Utils.readBitMap(context, R.drawable.bg_welcome_one));
		
        View v2 = layoutInflator.inflate(R.layout.welcome_item_image, null);
        ImageView imageView2 = (ImageView) v2.findViewById(R.id.img);
		imageView2.setImageBitmap(Utils.readBitMap(context, R.drawable.bg_welcome_two));
		
        View v3 = layoutInflator.inflate(R.layout.welcome_item_image, null);
        ImageView imageView3 = (ImageView) v3.findViewById(R.id.img);
		imageView3.setImageBitmap(Utils.readBitMap(context, R.drawable.bg_welcome_three));
		
        View v4 = layoutInflator.inflate(R.layout.welcome_item_image, null);
        ImageView imageView4 = (ImageView) v4.findViewById(R.id.img);
		imageView4.setImageBitmap(Utils.readBitMap(context, R.drawable.bg_welcome_four));
		
		View bt = v4.findViewById(R.id.relative_WelcomeActivity_tiyan);
		bt.setVisibility(View.VISIBLE);
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.bottom_anim_show);
		bt.setAnimation(animation);
        views.add(v1);
        views.add(v2);
        views.add(v3);
        views.add(v4);
        mPager.setAdapter(new MyAdapter());
	}
	
	@Override
	public void myOnClick(View v) {
		switch (v.getId()) {
		case R.id.relative_WelcomeActivity_tiyan:
			if (!isClick) {
				isClick = true;
				startActivity(new Intent(context, LoginActivity.class));
				finish();
			}
			break;
		}
	}
	
	/**
	 * 滑动欢迎页 图片适配器
	 */
	class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return views.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView( views.get(position));
			return  views.get(position);
		}
	}
}

