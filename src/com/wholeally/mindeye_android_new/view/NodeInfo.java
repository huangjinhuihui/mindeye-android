/**
 * 
 */
package com.wholeally.mindeye_android_new.view;

/**
 * <p>组织架构和虚拟组织架构的节点信息封装</p>
 * @author chenshaorong
 */
public class NodeInfo {

	/** 节点类型 */
	private int type;
	
	/** 节点ID */
	private String id;
	
	/** 节点名称 */
	private String name;
	
	/** 下级节点个数 */
	private int subs;
	
	/** 经纬度信息 */
	private String[] gpsInfo;
	
	/** 异步获取 */
	private int pid;
	
	
	/** 节点设备ID */
	private String deviceID;
	
	/** 过期时间，格式yyyy-MM-dd hh:mm:ss */
	private String effective;
	
	/** 在线标识，0不在线，1在线 */
	private int online;
	
	
	/** 通道号 */
	private int channelNo;
	
	/** 云台标识 */
	private int ptzFlag;
	
	/** 动态报警标识 */
	private int moveAlarmFlag;
	
	/** 伴音开关 */
	private int audioFlag;
	
	/** 分辨率 */
	private String dpi;
	
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the subs
	 */
	public int getSubs() {
		return subs;
	}

	/**
	 * @param subs the subs to set
	 */
	public void setSubs(int subs) {
		this.subs = subs;
	}

	/**
	 * @return the gpsInfo
	 */
	public String[] getGpsInfo() {
		return gpsInfo;
	}

	/**
	 * @param gpsInfo the gpsInfo to set
	 */
	public void setGpsInfo(String[] gpsInfo) {
		this.gpsInfo = gpsInfo;
	}

	/**
	 * @return the deviceID
	 */
	public String getDeviceID() {
		return deviceID;
	}

	/**
	 * @param deviceID the deviceID to set
	 */
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	/**
	 * @return the effective
	 */
	public String getEffective() {
		return effective;
	}

	/**
	 * @param effective the effective to set
	 */
	public void setEffective(String effective) {
		this.effective = effective;
	}

	/**
	 * @return the online
	 */
	public int getOnline() {
		return online;
	}

	/**
	 * @param online the online to set
	 */
	public void setOnline(int online) {
		this.online = online;
	}

	/**
	 * @return the channelNo
	 */
	public int getChannelNo() {
		return channelNo;
	}

	/**
	 * @param channelNo the channelNo to set
	 */
	public void setChannelNo(int channelNo) {
		this.channelNo = channelNo;
	}

	/**
	 * @return the ptzFlag
	 */
	public int getPtzFlag() {
		return ptzFlag;
	}

	/**
	 * @param ptzFlag the ptzFlag to set
	 */
	public void setPtzFlag(int ptzFlag) {
		this.ptzFlag = ptzFlag;
	}

	/**
	 * @return the moveAlarmFlag
	 */
	public int getMoveAlarmFlag() {
		return moveAlarmFlag;
	}

	/**
	 * @param moveAlarmFlag the moveAlarmFlag to set
	 */
	public void setMoveAlarmFlag(int moveAlarmFlag) {
		this.moveAlarmFlag = moveAlarmFlag;
	}

	/**
	 * @return the audioFlag
	 */
	public int getAudioFlag() {
		return audioFlag;
	}

	/**
	 * @param audioFlag the audioFlag to set
	 */
	public void setAudioFlag(int audioFlag) {
		this.audioFlag = audioFlag;
	}

	/**
	 * @return the pid
	 */
	public int getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getDpi()
	{
		return dpi;
	}

	public void setDpi(String dpi)
	{
		this.dpi = dpi;
	}
	
	
}
