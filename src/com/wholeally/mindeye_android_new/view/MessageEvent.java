/**
 * 
 */
package com.wholeally.mindeye_android_new.view;

import java.util.ArrayList;

/**
 * @author huangjh
 * 
 *         2015-2-16 下午4:46:19
 */
public class MessageEvent {
	/** 消息id */
	private String message_id;
	/** 消息时间 */
	private String message_time;
	/** 消息是否已读 */
	private String message_tag_read;
	/** 消息来源 */
	private String message_source;
	/** 消息类型 */
	private String message_type;
	/** 消息图片 */
	private String message_image;
	
	private ArrayList<MessageEvent> messageList=new ArrayList<MessageEvent>();
	
	/**
	 * @return the messageList
	 */
	public ArrayList<MessageEvent> getMessageList() {
		return messageList;
	}

	/**
	 * @param messageList the messageList to set
	 */
	public void setMessageList(ArrayList<MessageEvent> messageList) {
		this.messageList = messageList;
	}

	/**
	 * @return the message_id
	 */
	public String getMessage_id() {
		return message_id;
	}

	/**
	 * @param message_id
	 *            the message_id to set
	 */
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	/**
	 *获取消息时间
	 */
	public String getMessage_time() {
		return message_time;
	}

	/**
	 *设置消息时间
	 */
	public void setMessage_time(String message_time) {
		this.message_time = message_time;
	}

	/**
	 * 获取消息是否已读
	 */
	public String getMessage_tag_read() {
		return message_tag_read;
	}

	/**
	 * 设置消息是否已读
	 */
	public void setMessage_tag_read(String message_tag_read) {
		this.message_tag_read = message_tag_read;
	}

	/**
	 * 获取消息来源
	 */
	public String getMessage_source() {
		return message_source;
	}

	/**
	 * 设置消息来源
	 */
	public void setMessage_source(String message_source) {
		this.message_source = message_source;
	}

	/**
	 * 获取消息类型
	 */
	public String getMessage_type() {
		return message_type;
	}

	/**
	 * 设置消息类型
	 */
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	/**
	 * 获取消息图片
	 */
	public String getMessage_image() {
		return message_image;
	}

	/**
	 * 设置消息图片
	 */
	public void setMessage_image(String message_image) {
		this.message_image = message_image;
	}

}
