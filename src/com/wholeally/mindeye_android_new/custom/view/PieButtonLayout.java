package com.wholeally.mindeye_android_new.custom.view;

import java.util.ArrayList;
import java.util.List;

import com.shilian.mindeye.android.R;
import com.wholeally.mindeye_android_new.store.TempValue;
import com.wholeally.mindeye_android_new.utils.ViewUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by su on 2014/12/26.
 */
public class PieButtonLayout extends RelativeLayout {

    public static final int CIRCLE_ANGLE = 360;

    private static final int MAX_LEVEL = 5;
    private static final int DEFAULT_INNER_RADIUS = 38;
    private static final int DEFAULT_RADIUS_INC = 36;

    private Point mCenter;
    /**圆环内圆半径*/
    private int mInnerRadius;
    /**圆环厚度*/
    private int mRadiusInc;
    
    private OnItemClickListener mItemClickListener = null;
    /**mItemList只用来存放4个扇形环*/
    private ArrayList<PieItem> mItemList;
    /**中间那个实心圆*/
    private PieItem itemSolidCircle;
    /**正常情况下的画笔*/
    private Paint mNormalPaint;
    /**按压情况下的画笔*/
    private Paint mPressedPaint;
    /**正常情况下中间实心圆的画笔*/
	private Paint mNormalResetPaint;
	/**按压情况下中间实心圆的画笔*/
	private Paint mPressedResetPaint;

    int mItemSize;
    private Context mContext;

    private PieItem mLastTouchItem = null;
    /**各个PieItem都布局好了吗？true--已布局好，false--还未布局*/
    private boolean mIsLayoutItems = false;

    private boolean mIsTouchMove = false;

	private ViewUtils viewUtils;
    
    
    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public PieButtonLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public PieButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        
        viewUtils = new ViewUtils(context);
        mContext = context;
        mItemSize = (int) context.getResources().getDimension(R.dimen.pie_button_item_size);
        
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
//        DisplayMetrics dm = getResources().getDisplayMetrics();
//		setCenter(dm.widthPixels / 2, dm.heightPixels / 2);
        setCenter(90, 90);
		setLayoutParams(lp);
		addItem(makeItem(R.drawable.icon_yuntai_down));
		addItem(makeItem(R.drawable.icon_yuntai_left));
		addItem(makeItem(R.drawable.icon_yuntai_up));
		addItem(makeItem(R.drawable.icon_yuntai_right));
		addItemSolidCircle(makeItem(R.drawable.icon_yuntai_reset));
    }

    /**
     * @param context
     */
    public PieButtonLayout(final Context context) {
        super(context);
        init(context);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener){
    	mItemClickListener = listener;
    }

    private void init(Context ctx) {
        mItemList = new ArrayList<PieItem>();
        Resources res = ctx.getResources();

        mInnerRadius = DEFAULT_INNER_RADIUS;
        mRadiusInc = DEFAULT_RADIUS_INC;
        setWillNotDraw(false);
        setDrawingCacheEnabled(false);
        mCenter = new Point(0, 0);
        //设置正常情况下扇形的画笔
        mNormalPaint = new Paint();
        mNormalPaint.setColor(res.getColor(R.color.gray_yuntai_button_normal));
        mNormalPaint.setAntiAlias(true);
        //设置按压情况下扇形的画笔
        mPressedPaint = new Paint();
        mPressedPaint.setColor(res.getColor(R.color.blue_yuntai_button_pressed));
        mPressedPaint.setAntiAlias(true);
        //设置正常情况下中间实心圆的画笔
        mNormalResetPaint = new Paint();
        mNormalResetPaint.setColor(res.getColor(R.color.gray_yuntai_reset_normal));
        mNormalResetPaint.setAntiAlias(true);
        //设置按压情况下扇形的画笔
        mPressedResetPaint = new Paint();
        mPressedResetPaint.setColor(res.getColor(R.color.gray_yuntai_reset_pressed));
        mPressedResetPaint.setAntiAlias(true);
    }

    public void addItem(PieItem item) {
        mItemList.add(item);

    }

    public void addItemSolidCircle(PieItem itemSolidCircle)
	{
    	this.itemSolidCircle = itemSolidCircle;
	}
    
    public void setCenter(Point center) {
        mCenter = center;
    }

    public void setCenter(int x, int y) {
        if (mCenter == null) {
            mCenter = new Point(x, y);
        } else {
            mCenter.x = x;
            mCenter.y = y;
        }
    }

    private double angle2arc(int angle) {
        return (angle * Math.PI / 180);
    }

    private int arc2angle(double arc) {
        return (int) (arc * 180 / Math.PI);
    }

    /**
     * @param startAngle 
     * @param endAngle   
     * @param inner      
     * @param outer      
     * @param center     
     * @return
     */
    private Path makePath(int startAngle, int endAngle, int inner, int outer, Point center) {
        Path path = new Path();
        //构造一个与内圆相切的矩形
        RectF innerRect = new RectF(center.x - inner, center.y - inner,
                center.x + inner, center.y + inner);
        //构造一个与外圆相切的矩形
        RectF outerRect = new RectF(center.x - outer, center.y - outer,
                center.x + outer, center.y + outer);
        /*Path的arcTo方法是画一个弧线的路径，
         * 该方法的声明为：void Android.graphics.Path.arcTo(RectF oval, float startAngle, float sweepAngle);
         * 先说一下，这个弧线是怎么来的？是先画一个椭圆，然后再在这个椭圆上面截取一部分部形。这个图形自然
         * 就是一个弧线了。那么这个椭圆是怎么确定的呢？这就是这个RectF参数所起的作用了。给出这个矩形后，
         * 系统就可以算出这个矩形的中心，然后以这个矩开的中心画一个椭圆。得到这个椭圆后，然后就是截取一部分
         * 线了，就得到最终的弧线。这一部分是怎么截取的呢？这就是后面两个参数共同来表达的。startAngle这个参
         * 数说的是开始的角度。这个好理解，但哪里是0度线呢，又是向哪个方向旋转是正角度数呢？
         * 从中心点水平向右侧的延伸线即为0度线，startAngle是开始度数，那sweepAngle是指的什么呢？sweepAngle指
         * 的是旋转的度数，也就是以startAngle开始，旋转多少度，如果sweepAngle是正数，那么就是按顺时针方向旋
         * 转，如果是负数就是按逆时针方向旋转。
         */
        path.arcTo(innerRect, startAngle, endAngle - startAngle, true);//内弧线路径
        path.arcTo(outerRect, endAngle, startAngle - endAngle, false);//外弧线路径
        path.close();
        return path;
    }

    /**
     * 对各个item进行设置
     */
    private void layoutItems() {
    	/**圆环各个扇形之间的间隔角度*/
        int intervalAngle = 1;
        /**圆环内圆半径*/
        int inner = mInnerRadius;
        /**圆环外圆半径*/
        int outer = mInnerRadius + mRadiusInc;
        /**圆环之间的间隙大小*/
        int incInterval = 2;
        /**圆环中的第一个扇形的起始角度*/
        int startAngle = 45;
        /**当前圆环中每个扇形的角度范围*/
        int itemAngle = CIRCLE_ANGLE / mItemList.size() - intervalAngle;
        //下面这个for循环用来对圆环的设置
        for (PieItem item : mItemList) {
        	//对扇形环中的那张图片的设置
            View view = item.getView();//取出每个PieItem中的ImageView控件
            view.measure(view.getLayoutParams().width,
                    view.getLayoutParams().height);
            int w = view.getMeasuredWidth();
            int h = view.getMeasuredHeight();
            //show in center
            int r = inner + (outer - inner) / 2;//圆环的内圆和外圆中间的那个圆的半径
            double arc = angle2arc(startAngle + itemAngle / 2 + intervalAngle / 2);//算出每个扇形环的中间角度
            int x = mCenter.x + (int) (r * Math.cos(arc)) - w / 2;//算出扇形环中那个ImageView的起始点的x坐标
            int y = mCenter.y + (int) (r * Math.sin(arc)) - h / 2;//算出扇形环中那个ImageView的起始点的y坐标
            view.layout(x, y, x + w, y + h);//扇形中那张图片的左、上、右、下
            
            //设置扇形环的内外弧线的路径
            Path path = makePath(startAngle, startAngle + itemAngle, inner, outer, mCenter);
            //将扇形的信息保存到对应的PieItem中
            item.setGeometry(startAngle, itemAngle, inner, outer, path);
            //计算圆环下个扇形的起始角度
            startAngle += itemAngle + intervalAngle;
        }
    }

    private PieItem findItem(float x, float y) {
        // find the matching item:
        x -= mCenter.x;
        y -= mCenter.y;
        int r = (int) Math.sqrt(x * x + y * y);
        int angle = arc2angle(Math.atan2(y, x));
        angle %= 360;
        if (angle < 0) {
            angle += 360;
        }
        for (PieItem item : mItemList) {
            if (isPointInItem(r, angle, item)) {
                return item;
            }
        }
        if(isPointInItemCircle(r, CIRCLE_ANGLE, itemSolidCircle))
        {
        	return itemSolidCircle;
        }
        
        
        
        return null;
    }

    /**
     * is point with radius r and angle angle in item
     *
     * @param r     radius
     * @param angle angle witch is belong [0, 360)
     * @param item
     * @return yes point in the item, otherwise false
     */
    private boolean isPointInItem(int r, int angle, PieItem item) {
        if (item.getInnerRadius() <= r && item.getOuterRadius() >= r) {
            while (angle + CIRCLE_ANGLE <= item.getStartAngle() + item.getSweep()) {
                angle += CIRCLE_ANGLE;
            }
            if (item.getStartAngle() <= angle && item.getStartAngle() + item.getSweep() >= angle) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    private boolean isPointInItemCircle(int r, int angle, PieItem item) {
    	if (r <= item.getOuterRadius()) {
            return true;
        }
        return false;
    }

    private int mLastTouchX;
    private int mLastTouchY;
    
    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        int x = (int) evt.getX();
        int y = (int) evt.getY();
        int action = evt.getActionMasked();
        if (MotionEvent.ACTION_DOWN == action) {
        	mLastTouchX = x;
        	mLastTouchY = y;
        	mIsTouchMove = false;
            mLastTouchItem = findItem(x, y);
            if (mLastTouchItem != null) {
                mLastTouchItem.setPressed(true);
                invalidate();
            } else {
            }
            return true;
        } else if (MotionEvent.ACTION_UP == action) {
            if (mLastTouchItem != null) {
                mLastTouchItem.setPressed(false);
                invalidate();
                
                events(mLastTouchItem);//点击事件，判断按下的是哪个部位
            }
            if(!mIsTouchMove){
            	if(mItemClickListener != null && mLastTouchItem != null){
            		mItemClickListener.onItemClick(mLastTouchItem);
            	}
            }
        } else if (MotionEvent.ACTION_CANCEL == action) {
//        	mIsTouchMove = true;
        } else if (MotionEvent.ACTION_MOVE == action) {
        	if(x != mLastTouchX || y != mLastTouchY){
        		mIsTouchMove = true;
        	}
        }
        return false;
    }

	@Override
    protected void onDraw(Canvas canvas) {
        if(!mIsLayoutItems){
            layoutItems();//对mItemList中的各个PieItem进行设置
            mIsLayoutItems = true;
        }
        int state;
        for (PieItem item : mItemList) {
            Paint p = item.isPressed() ? mPressedPaint : mNormalPaint;
            state = canvas.save();//保存画布的当前状态
            drawPath(canvas, item.getPath(), p);//画扇形环
            canvas.restoreToCount(state);//取出之前保存的画布的状态
            drawItem(canvas, item);
        }
        
        drawSolidCircle(canvas);
    }

    /**
     * 画中间那个实心圆，并且设置实心圆的一些属性
     * @param canvas
     */
	private void drawSolidCircle(Canvas canvas)
	{
		int state;
		View view = itemSolidCircle.getView();
        view.measure(view.getLayoutParams().width, view.getLayoutParams().height);
        int w = view.getMeasuredWidth();
        int h = view.getMeasuredHeight();
        int x = mCenter.x  - w / 2;
        int y = mCenter.y  - h / 2;
        view.layout(x, y, x + w, y + h);
        
        Paint p = itemSolidCircle.isPressed() ? mPressedResetPaint : mNormalResetPaint;
        state = canvas.save();//保存画布的当前状态
        canvas.drawCircle(mCenter.x, mCenter.y, mInnerRadius, p);
        canvas.restoreToCount(state);//取出之前保存的画布的状态
        drawItem(canvas, itemSolidCircle);
        itemSolidCircle.setGeometry(0, 360, 0, mInnerRadius, null);
	}

    @SuppressLint("NewApi") 
    private void drawItem(Canvas canvas, PieItem item) {
        View view = item.getView();
        int state = canvas.save();//保存画布的当前状态
        canvas.translate(view.getX(), view.getY());
        view.draw(canvas);
        canvas.restoreToCount(state);//取出之前保存的画布的状态
        state = canvas.save();
        canvas.restoreToCount(state);
    }

    private void drawPath(Canvas canvas, Path path, Paint paint) {
        canvas.drawPath(path, paint);
    }

    public PieItem makeItem(int image) {
        ImageView view = new ImageView(mContext);
        view.setImageResource(image);
        view.setMinimumWidth(mItemSize);
        view.setMinimumHeight(mItemSize);
        view.setScaleType(ImageView.ScaleType.CENTER);
        LayoutParams lp = new LayoutParams(mItemSize, mItemSize);
        view.setLayoutParams(lp);
        return new PieItem(view);
    }
    
    /**
     * 点击事件，判断按下的是哪个部位
     * @param mLastTouchItem
     */
    private void events(PieItem mLastTouchItem)
   	{
    	if(mLastTouchItem == null){
    		return;
    	}
    	
    	if(mLastTouchItem.equals(mItemList.get(0))){
    		System.out.println("events==下");
//    		showToast("下");
    	}else if(mLastTouchItem.equals(mItemList.get(1))){
    		System.out.println("events==左");
//    		showToast("左");
    	}else if(mLastTouchItem.equals(mItemList.get(2))){
    		System.out.println("events==上");
//    		showToast("上");
    	}else if(mLastTouchItem.equals(mItemList.get(3))){
    		System.out.println("events==右");
//    		showToast("右");
    	}else if(mLastTouchItem.equals(itemSolidCircle)){
    		System.out.println("events==归位");
//    		showToast("归位");
    	}
   	}
    
    private void showToast(int resId)
	{
		viewUtils.showToastCustom(resId);
	}
    
    private void showToast(String str)
	{
		viewUtils.showToastCustom(str);
	}
    
    public static interface OnItemClickListener{
    	public void onItemClick(PieItem item);
    }

}
