package com.wholeally.mindeye_android_new.view;

import java.util.ArrayList;

/**
 * @author huangjh
 * 
 *         2015-2-15 下午4:27:54
 */
public class MonitorDirector {

	/** 监控目录ID */
	private String id;
	/** 监控文件名称 */
	private String monitor_file_name;
	/** 监控文件时间 */
	private String monitor_file_tiime;

	private ArrayList<MonitorDirector> monitorList = new ArrayList<MonitorDirector>();
	
	/**
	 * @return the monitorList
	 */
	public ArrayList<MonitorDirector> getMonitorList() {
		return monitorList;
	}

	/**
	 * @param monitorList the monitorList to set
	 */
	public void setMonitorList(ArrayList<MonitorDirector> monitorList) {
		this.monitorList = monitorList;
	}

	/**
	 * @return the id
	 */
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
	 * 监控文件名称
	 */
	public String getMonitor_file_name() {
		return monitor_file_name;
	}

	/**
	 * 监控文件名称
	 */
	public void setMonitor_file_name(String monitor_file_name) {
		this.monitor_file_name = monitor_file_name;
	}

	/**
	 * 监控文件时间
	 */
	public String getMonitor_file_tiime() {
		return monitor_file_tiime;
	}

	/**
	 * 监控文件时间
	 */
	public void setMonitor_file_tiime(String monitor_file_tiime) {
		this.monitor_file_tiime = monitor_file_tiime;
	}

}
