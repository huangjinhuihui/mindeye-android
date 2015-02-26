/**
 * 
 */
package com.wholeally.mindeye_android_new.utils;

import java.lang.reflect.Field;

/**
 * @author huangjh
 *
 * 2015-1-30 上午9:48:08
 */
public class ResourcesImageLoaderConfiguration {

	private int defaultFailImage = -1;
	private boolean isCacheInMemory = false;
	
	public ResourcesImageLoaderConfiguration(int defaultFailImage,
			boolean isCacheInMemory) {
		super();
		this.defaultFailImage = defaultFailImage;
		this.isCacheInMemory = isCacheInMemory;
	}
	public ResourcesImageLoaderConfiguration() {
		super();
	}

	/**是否缓存到内存
	 * @return
	 */
	public boolean getIsCacheInMemory() {
		return isCacheInMemory;
	}

	/**是否缓存到内存
	 * @param isCacheInMemory
	 * @return
	 */
	public ResourcesImageLoaderConfiguration setCacheInMemory(boolean isCacheInMemory) {
		this.isCacheInMemory = isCacheInMemory;
		return this;
	}

	/**
	 * @return -1代表不设置
	 */
	public int getDefaultFailImage() {
		return defaultFailImage;
	}

	/**无图时显示的图片
	 * -1代表不设置
	 * @param defaultFailImage
	 * @return
	 */
	public ResourcesImageLoaderConfiguration setDefaultFailImage(int defaultFailImage) {
		this.defaultFailImage = defaultFailImage;
		return this;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Class<?> cla = getClass();
		Field[] fields = cla.getDeclaredFields();
		StringBuffer buffer = new StringBuffer();
		for (Field f : fields) {
			try {
				f.setAccessible(true);//在类的外面获取此类的私有成员变量的value时 ,必须进行此操作
				buffer.append(f.getName()+":"+String.valueOf(f.get(cla))+"、");
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}
	
}
