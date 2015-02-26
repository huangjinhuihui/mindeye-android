/**
 * 
 */
package com.wholeally.mindeye_android_new.view;

import java.util.ArrayList;

/**
 * @author huangjh
 * 设备项
 * 2015-2-6 下午4:01:23
 */
public class DeviceInfo {

	/** 设备序号 */
	private String id;
	
	/** 设备号 */
	private String deviceID;
	
	/** 设备类型 0:摄像头 1:视频头 2:默认头*/
	private String deviceType;
	
	/** 在线标识，0不在线,1在线 */
	private int online;
	
	/** 设备名称 */
	private String deviceName;

	/** 激活状态 0未激活,1已激活*/
	private int activation;
	
	private ArrayList<DeviceInfo> deviceList = new ArrayList<DeviceInfo>();
	
	/**
	 * @return the deviceList
	 */
	public ArrayList<DeviceInfo> getDeviceList() {
		return deviceList;
	}
	/**
	 * @param deviceList the deviceList to set
	 */
	public void setDeviceList(ArrayList<DeviceInfo> deviceList) {
		this.deviceList = deviceList;
	}
	
	/** 激活状态 0未激活,1已激活*/
	public int getActivation() {
		return activation;
	}
	/**
	 * 激活状态 0未激活,1已激活
	 */
	public void setActivation(int activation) {
		this.activation = activation;
	}
	
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 设备号
	 */
	public String getDeviceID() {
		return deviceID;
	}
	/**
	 * 设备号
	 */
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	/**
	 * 设备类型 0:摄像头 1:视频头 2:默认头
	 */
	public String getDeviceType() {
		return deviceType;
	}
	/**
	 * 设备类型 0:摄像头 1:视频头 2:默认头
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	/**
	 *  在线标识，0不在线，1在线 
	 */
	public int getOnline() {
		return online;
	}
	/**
	 * 在线标识，0不在线，1在线 
	 */
	public void setOnline(int online) {
		this.online = online;
	}
	/**
	 * 设备名称
	 */
	public String getDeviceName() {
		return deviceName;
	}
	/**
	 * 设备名称
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	
}
