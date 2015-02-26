/**
 * 
 */
package com.wholeally.mindeye_android_new.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Hashtable;

import com.wholeally.mindeye_android_new.app.MindeyeApplication;

import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

/**
 * @author huangjh
 *
 * 2015-1-30 上午9:45:58
 */
public class ResourcesImageLoader {

	private static ResourcesImageLoader imageLoader = null;
	private ResourcesImageLoaderConfiguration p_configuration = new ResourcesImageLoaderConfiguration();
	private Hashtable hashtable;// 用于Cache内容的存储      
	private ReferenceQueue q;// 垃圾Reference的队列      
	
	
	public static ResourcesImageLoader getInstance(){
		if (imageLoader==null) {
			imageLoader = new ResourcesImageLoader();
		}
		return imageLoader;
	}
	public ResourcesImageLoader() {
		hashtable = new Hashtable();      
	       q = new ReferenceQueue();      
	}
	/**获取配置对象
	 * @return
	 */
	public ResourcesImageLoaderConfiguration getConfiguration() {
		return p_configuration;
	}
	
	/**指定像素加载
	 * 采用init配置
	 * @param id
	 * @param imageView
	 * @param width_px
	 * @param height_px
	 */
	public void loaderImage(int id, ImageView imageView, int width_px,
			int height_px) {
		Bitmap bitmap = null;
		if (p_configuration!=null&&p_configuration.getIsCacheInMemory()) {
			bitmap = getObject(id+"");
			if (bitmap!=null) {
				imageView.setImageBitmap(bitmap);
			}else {
				bitmap = loadImage(id);
				if (bitmap!=null) {
					imageView.setImageBitmap(bitmap);
					cacheObject(bitmap, id+"");
				}else {
					if (p_configuration!=null&&p_configuration.getDefaultFailImage()!=-1) {
						bitmap =readBitmapFromId(p_configuration.getDefaultFailImage(), width_px, height_px);
						if (bitmap!=null) {
							imageView.setImageBitmap(bitmap);
						}
					}
				}
			}
		}else {
			try {
				Bitmap bitmapold = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
			} catch (Exception e) {
				e.printStackTrace();
			}
			bitmap =readBitmapFromId(id, width_px, height_px);
			if (bitmap!=null) {
				imageView.setImageBitmap(bitmap);
			}else {
				if (p_configuration!=null&&p_configuration.getDefaultFailImage()!=-1) {
					bitmap =readBitmapFromId(p_configuration.getDefaultFailImage(), width_px, height_px);
					if (bitmap!=null) {
						imageView.setImageBitmap(bitmap);
					}
				}
			}
		}
	}
	/**原图大小加载
	 * 采用init配置
	 * @param id
	 * @param imageView
	 */
	public void loaderImage(int id, ImageView imageView) {
		Bitmap bitmap = null;
		if (p_configuration!=null&&p_configuration.getIsCacheInMemory()) {
			bitmap = getObject(id+"");
			if (bitmap!=null) {
				imageView.setImageBitmap(bitmap);
			}else {
				bitmap = loadImage(id);
				if (bitmap!=null) {
					imageView.setImageBitmap(bitmap);
					cacheObject(bitmap, id+"");
				}else {
					if (p_configuration!=null&&p_configuration.getDefaultFailImage()!=-1) {
						bitmap =loadImage(p_configuration.getDefaultFailImage());
						if (bitmap!=null) {
							imageView.setImageBitmap(bitmap);
						}
					}
				}
			}
		}else {
			try {
				Bitmap bitmapold = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
			} catch (Exception e) {
				e.printStackTrace();
			}
			bitmap =loadImage(id);
			if (bitmap!=null) {
				imageView.setImageBitmap(bitmap);
			}else {
				if (p_configuration!=null&&p_configuration.getDefaultFailImage()!=-1) {
					bitmap =loadImage(p_configuration.getDefaultFailImage());
					if (bitmap!=null) {
						imageView.setImageBitmap(bitmap);
					}
				}
			}
		}
	}
	
	/**指定像素
	 * @param id
	 * @param imageView
	 * @param width_px
	 * @param height_px
	 * @param configuration   可null代表默认设置
	 */
	public void loaderImage(int id,ImageView imageView,int width_px,int height_px,ResourcesImageLoaderConfiguration configuration){
		Bitmap bitmap = null;
		if (configuration!=null&&configuration.getIsCacheInMemory()) {
			bitmap = getObject(id+"");
			if (bitmap!=null) {
				imageView.setImageBitmap(bitmap);
			}else {
				bitmap = loadImage(id);
				if (bitmap!=null) {
					imageView.setImageBitmap(bitmap);
					cacheObject(bitmap, id+"");
				}else {
					if (configuration!=null&&configuration.getDefaultFailImage()!=-1) {
						bitmap =readBitmapFromId(configuration.getDefaultFailImage(), width_px, height_px);
						if (bitmap!=null) {
							imageView.setImageBitmap(bitmap);
						}
					}
				}
			}
		}else {
			try {
				Bitmap bitmapold = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
			} catch (Exception e) {
				e.printStackTrace();
			}
			bitmap =readBitmapFromId(id, width_px, height_px);
			if (bitmap!=null) {
				imageView.setImageBitmap(bitmap);
			}else {
				if (configuration!=null&&configuration.getDefaultFailImage()!=-1) {
					bitmap =readBitmapFromId(configuration.getDefaultFailImage(), width_px, height_px);
					if (bitmap!=null) {
						imageView.setImageBitmap(bitmap);
					}
				}
			}
		}
	}
	/**原图大小
	 * @param id
	 * @param imageView
	 * @param configuration   可null代表默认设置
	 */
//	public void loaderImage(int id,ImageView imageView,ResourcesImageLoaderConfiguration configuration){
//		
//		Bitmap bitmap = null;
//		if (configuration!=null&&configuration.getIsCacheInMemory()) {
//			bitmap = getObject(id+"");
//			if (bitmap!=null) {
//				imageView.setImageBitmap(bitmap);
//			}else {
//				bitmap = loadImage(id);
//				if (bitmap!=null) {
//					imageView.setImageBitmap(bitmap);
//					cacheObject(bitmap, id+"");
//				}else {
//					if (configuration!=null&&configuration.getDefaultFailImage()!=-1) {
//						bitmap =loadImage(configuration.getDefaultFailImage());
//						if (bitmap!=null) {
//							imageView.setImageBitmap(bitmap);
//						}
//					}
//				}
//			}
//		}else {
//			try {
//				Bitmap bitmapold = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
//				Logger.recycleDebugLoggger(bitmapold, "ResourcesImageLoader");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			bitmap =loadImage(id);
//			if (bitmap!=null) {
//				imageView.setImageBitmap(bitmap);
//			}else {
//				if (configuration!=null&&configuration.getDefaultFailImage()!=-1) {
//					bitmap =loadImage(configuration.getDefaultFailImage());
//					if (bitmap!=null) {
//						imageView.setImageBitmap(bitmap);
//					}
//				}
//			}
//		}
//	}
	/**设置配置（影响【采用init配置】的函数）:
	 * 如不调用该方法 将采用上次设置（如果是首次使用 将采用默认设置）;
	 * 如果想在原设置基础上修改 可调用getConfiguration();
	 * 设置为 空null：恢复默认设置
	 * @param configuration
	 */
	public void init(ResourcesImageLoaderConfiguration configuration){
		if (configuration!=null) {
			this.p_configuration = configuration;
		}else {
			this.p_configuration = new ResourcesImageLoaderConfiguration();
		}
	}
	
	private  Bitmap loadImage(int resId){ 
		InputStream is = null;
	    try {
			BitmapFactory.Options opt = new BitmapFactory.Options();  
			opt.inPreferredConfig = Bitmap.Config.RGB_565;   
			opt.inPurgeable = true;  
			opt.inInputShareable = true;  
			   //获取资源图片  
			is = MindeyeApplication.getContext().getResources().openRawResource(resId);  
			Bitmap bitmap  =BitmapFactory.decodeStream(is,null,opt);
			return bitmap;
		} catch (NotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}  
	    return null;
	}  
	private  Bitmap readBitmapFromId(int id, int width, int height) {
		BitmapFactory.Options opts = null;
		if (width > 0 && height > 0) {
			opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;
			BitmapFactory.decodeResource(MindeyeApplication.getContext().getResources(), id, opts);
			// 计算图片缩放比例
			final int minSideLength = Math.min(width, height);
			opts.inSampleSize = computeSampleSize(opts, minSideLength,
					width * height);
			opts.inJustDecodeBounds = false;
			opts.inInputShareable = true;
			opts.inPurgeable = true;
		}
		try {
			return BitmapFactory.decodeResource(MindeyeApplication.getContext().getResources(), id, opts);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		}
		return null;
	}
	private  int computeSampleSize(BitmapFactory.Options options,
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
	private  int computeInitialSampleSize(BitmapFactory.Options options,
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
	
	
	
	
	
	/*-------------------------缓存------------------------------*/
	
	private class  MyWeakReference<T> extends WeakReference<T> {      

		private String _key = "";      
	       
	       public MyWeakReference(T t, ReferenceQueue<? super T> q,String key) {      
	           super(t, q);      
	           _key = key;      
	       }      
	    }
	/**缓存对象
     * @param t 带缓存对象
     * @param key
     */
    @SuppressWarnings("unchecked")
	public  <T> void cacheObject(T t,String key) {      
       cleanCache();// 清除垃圾引用      
       MyWeakReference<T> ref =  new MyWeakReference<T>(t, q, key);    
       hashtable.put(ref._key, ref);
       t = null;
    }      
       
    // 依据所指定的ID号，重新获取相应Object对象的实例      
    /**获取缓存
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
	public  <T> T getObject(String key) {      
       // 缓存中是否有该Object实例的软引用，如果有，从软引用中取得。      
    	T t = null;
       if (hashtable.containsKey(key)) {      
    	   MyWeakReference<T> ref = (MyWeakReference<T>) hashtable.get(key);      
           try {
			t = (T)ref.get();
		} catch (Exception e) {
			e.printStackTrace();
		}      
       }      
       return t;      
    }      
       
    // 清除那些所软引用的Object对象已经被回收的ObjectRef对象      
    @SuppressWarnings("rawtypes")
	private void cleanCache() {    
    	MyWeakReference ref = null;      
       while ((ref = (MyWeakReference) q.poll()) != null) {      
           hashtable.remove(ref._key);      
       }      
    }      
       
    /**
     * 清除Cache内存的全部内容      
     */
    public void clearCache() {      
       cleanCache();      
       hashtable.clear();      
       System.gc();      
       System.runFinalization();      
    }      
    /**
     * 清除特定cache
     * @param key
     */
    public void deleteCache(String key) {  
    	hashtable.remove(key);      
    	cleanCache();      
    	System.gc();      
    	System.runFinalization();      
    }
	
}
