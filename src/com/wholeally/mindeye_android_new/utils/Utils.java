package com.wholeally.mindeye_android_new.utils;

import java.io.InputStream;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.wholeally.mindeye_android_new.app.MindeyeApplication;

public class Utils {

	/**
	 * 以最省内存的方式读取本地资源的图片
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Bitmap readBitMap(Context context, int resId){  
		context = MindeyeApplication.getContext();
	    BitmapFactory.Options opt = new BitmapFactory.Options();  
	    opt.inPreferredConfig = Bitmap.Config.RGB_565;   
	    opt.inPurgeable = true;  
	    opt.inInputShareable = true;  
	       //获取资源图片  
	    InputStream is = context.getResources().openRawResource(resId);  
	        return BitmapFactory.decodeStream(is,null,opt);  
	}  
	
	/**获取ImageLoader的ImageLoaderConfiguration
	 * @param context
	 * @return
	 */
	public static ImageLoaderConfiguration getImageLoaderConfiguration(Context context){
		ImageLoaderConfiguration config = new ImageLoaderConfiguration  
				.Builder(context) 
//		.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
		.defaultDisplayImageOptions(
				new DisplayImageOptions.Builder()
		.imageScaleType(ImageScaleType.EXACTLY)
		.bitmapConfig(Bitmap.Config.RGB_565).build())
//		.memoryCache(new WeakMemoryCache())
		.memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//	    .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现  
		.memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽  
		.memoryCacheSize(2 * 1024 * 1024)    
		.threadPoolSize(3)//线程池内加载的数量  
		.diskCacheSize(50 * 1024 * 1024)
		.threadPriority(Thread.NORM_PRIORITY - 2)  
		.denyCacheImageMultipleSizesInMemory()  
		.diskCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密  
		.tasksProcessingOrder(QueueProcessingType.LIFO)  
		.diskCacheFileCount(500)//缓存的文件数量  
//		.denyCacheImageMultipleSizesInMemory()
		.diskCache(new UnlimitedDiscCache(StorageUtils.getOwnCacheDirectory(context.getApplicationContext()
				, "imageloader/Cache")))//自定义缓存路径  
		.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间  
//		.writeDebugLogs() // Remove for release app  
		.build();//开始构建  
		return config;
	}
	
	/**
	 * 图片的缩放
	 * 
	 * @param bgimage
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	private static Bitmap zoomImage(Bitmap bgimage, double newWidth,
			double newHeight) {
		float width = bgimage.getWidth();
		float height = bgimage.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
				(int) height, matrix, true);
		return bitmap;
	}
	
	private static int computeInitialSampleSize(BitmapFactory.Options options,
	        int minSideLength, int maxNumOfPixels) {
	    double w = options.outWidth;
	    double h = options.outHeight;

	    int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
	            .sqrt(w * h / maxNumOfPixels));
	    int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math
	            .floor(w / minSideLength), Math.floor(h / minSideLength));

	    if (upperBound < lowerBound) {
	        // return the larger one when there is no overlapping zone.
	        return lowerBound;
	    }

	    if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
	        return 1;
	    } else if (minSideLength == -1) {
	        return lowerBound;
	    } else {
	        return upperBound;
	    }
	}
	
	private static int computeSampleSize(BitmapFactory.Options options,
	        int minSideLength, int maxNumOfPixels) {
	    int initialSize = computeInitialSampleSize(options, minSideLength,
	            maxNumOfPixels);

	    int roundedSize;
	    if (initialSize <= 8) {
	        roundedSize = 1;
	        while (roundedSize < initialSize) {
	            roundedSize <<= 1;
	        }
	    } else {
	        roundedSize = (initialSize + 7) / 8 * 8;
	    }

	    return roundedSize;
	}
	
	/**
	 * 
	 * BitmapFactory加载图片时使用BitmapFactory.Options对相关参数进行配置来减少加载的像素
	 * @param id 资源ID
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap readBitmapFromId(Context context,int id, int width, int height) {
		//if (null != dst && dst.exists()) {
		if (context==null) {
			context = MindeyeApplication.getContext();
		}
		BitmapFactory.Options opts = null;
		if (width > 0 && height > 0) {
			opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;
			BitmapFactory.decodeResource(context.getResources(), id, opts);
			// 计算图片缩放比例
			final int minSideLength = Math.min(width, height);
			opts.inSampleSize = computeSampleSize(opts, minSideLength,
					width * height);
			opts.inJustDecodeBounds = false;
			opts.inInputShareable = true;
			opts.inPurgeable = true;
		}
		try {
			return zoomImage(BitmapFactory.decodeResource(context.getResources(), id, opts), width, height);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
		//}
		return null;
	}
	
	/**
	 * 获取软件版本号
	 * 
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context)
	{
		if (context==null) {
  		  context = MindeyeApplication.getContext();
		}
	    int versionCode = 0;
	    try
	    {
	        // 获取软件版本号，
	        versionCode = context.getPackageManager().getPackageInfo("com.wholeally.mindeye_android_new.app", 0).versionCode;
	    } catch (NameNotFoundException e)
	    {
	        e.printStackTrace();
	    }
	    return versionCode;
	}
}
